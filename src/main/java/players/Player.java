package players;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import equipments.boots.Slippers;
import equipments.weapons.Stick;
import players.abilities.*;
import equipments.Equipment;
import equipments.armors.Tshirt;
import equipments.EmptySlot;
import functions.TypewriterEffect;
import functions.UI.UI;
import exceptions.RolladieException;

import static functions.Storage.SAVE_DELIMITER;
import static functions.UI.BattleDisplay.drawPowerBar;


/**
 * Represents player and non-player characters in the game
 */
public class Player {
    public String name;
    public int hp, maxHp, baseAttack;
    public int[] diceRolls;
    public List<Equipment> equipmentList;
    public Ability lastAbilityUsed;
    public boolean isHuman;
    public List<Ability> abilities = new ArrayList<>();
    public int gold;

    public int power = 50;
    public int maxPower = 100;

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Creates a Player object either controlled by a human player or computer
     *
     * @param name          Name of the character
     * @param maxHp         Maximum hitpoints the character can take
     * @param baseAttack    Base damage amount
     * @param numDice       Number of dice to roll during battle encounters
     * @param equipmentList A list to encapsulate all the equipment equipped by a character,
     * @param isHuman       True if creating player-controlled character, false otherwise
     */
    public Player(String name, int maxHp, int baseAttack, int numDice, List<Equipment> equipmentList, boolean isHuman) {
        this.name = name;
        this.hp = this.maxHp = maxHp;
        this.baseAttack = baseAttack;
        this.diceRolls = new int[numDice];
        this.equipmentList = equipmentList;
        this.isHuman = isHuman;
        this.gold = 0;
    }

    public Player(String name, int maxHp, int baseAttack, int numDice, List<Equipment> equipmentList, boolean isHuman, int gold) {
        this.name = name;
        this.hp = this.maxHp = maxHp;
        this.baseAttack = baseAttack;
        this.diceRolls = new int[numDice];
        this.equipmentList = equipmentList;
        this.isHuman = isHuman;
        this.gold = gold;
    }

    public Player(String name, int maxHp, int baseAttack) {
        this.name = name;
        this.hp = this.maxHp = maxHp;
        this.baseAttack = baseAttack;
        this.diceRolls = new int[2];
        this.equipmentList = new ArrayList<>(List.of(new EmptySlot(), new EmptySlot(), new EmptySlot()));
        this.isHuman = true;
        this.gold = 0;
    }

    public static void resetScanner(InputStream in) {
        scanner = new Scanner(in);
    }

    public int getGold() {
        return gold;
    }

    public void spendGold(int amount) throws RolladieException {
        if (gold - amount < 0) {
            throw new RolladieException("not enough gold");
        }
        gold -= amount;
    }

    public void earnGold(int amount) {
        gold += amount;
    }

    public int getPlayerAttack() {
        return equipmentList.stream()
                .filter(e -> e.getId() != -1)
                .mapToInt(Equipment::getAttack)
                .sum();
    }

    public int getPlayerDefense() {
        return equipmentList.stream()
                .filter(e -> e.getId() != -1)
                .mapToInt(Equipment::getDefense)
                .sum();
    }

    public Equipment getEquipment(int equipmentType) {
        Equipment currSlot = equipmentList.get(equipmentType);
        return currSlot;
    }

    public void obtainEquipment(Equipment equipment) throws RolladieException {
        Equipment currSlot = equipmentList.get(equipment.getId());
        if (currSlot.getId() != -1) {
            throw new RolladieException(currSlot.getEquipmentType() + " is already equipped!");
        }
        equipmentList.set(equipment.getId(), equipment);
    }

    public void removeEquipment(int equipmentType) throws RolladieException {
        Equipment equipment = equipmentList.get(equipmentType);
        if (equipment.getId() == -1) {
            throw new RolladieException("No equipment at this slot!");
        }
        equipmentList.set(equipmentType, new EmptySlot());
    }

    public boolean buyEquipment(Equipment equipment) throws RolladieException {
        if (this.gold >= equipment.getValue()) {
            if (equipmentList.get(equipment.getId()).getId() != -1) {
                removeEquipment(equipment.getId());
            }
            obtainEquipment(equipment);
            spendGold(equipment.getValue());
            return true;
        } else {
            return false;
        }
    }

    public void sellEquipment(int equipmentType) throws RolladieException {
        earnGold(getEquipment(equipmentType).getValue() / 2);
        removeEquipment(equipmentType);
    }

    /**
     * Creates a new human player when starting from new game
     *
     * @return Player character
     */
    public static Player createNewPlayer() {
        UI.printMessage("Enter your hero's name: ");
        String name = scanner.nextLine();

        // todo: choose character class to vary these starting stats
        List<Equipment> equipmentList = new ArrayList<Equipment>(List.of(new Tshirt(), new Slippers(), new Stick()));
        Player player = new Player(name, 100, 5, 3, equipmentList, true);
        player.abilities.add(new Flee());
        player.abilities.add(new BasicAttack());
        player.abilities.add(new PowerStrike());
        player.abilities.add(new Heal());

        return player;
    }

    private void rollDice() {
        Random rand = new Random();
        for (int i = 0; i < diceRolls.length; i++) {
            diceRolls[i] = rand.nextInt(6) + 1;
        }
    }

    /**
     * Get hp value of the player
     *
     * @return An integer represent hp value of the player.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Reroll the dice and get results
     *
     * @return Integer array of size equivalent to number of dice Player has
     */
    public int[] getDiceRolls() {
        rollDice();
        return diceRolls;
    }

    /**
     * Adds up the current dice roll results that the Player possesses
     *
     * @return sum of dice rolls
     */
    public int totalRoll() {
        int sum = 0;
        for (int roll : diceRolls) {
            sum += roll;
        }
        return sum;
    }


    // todo: print the compute damage process

    /**
     * Calculates the damage dealt to an opponent, computed as follows:
     * <p>
     * [(dice roll result) + (num of dice) * (weapon bonus)] *
     * [(power) / (max power) * 0.5 * (weapon damage multiplier)] - (opponent armor defense)
     *
     * @param opponent Player that the current Player is battling
     * @return damage dealt to an opponent
     * @throws InterruptedException
     */
    public int computeDamageTo(Player opponent) throws InterruptedException {
        assert opponent.isAlive() : "Opponent must be alive to receive damage";
        int base = totalRoll() + (diceRolls.length * getPlayerAttack());

        // if (powerStrikeActive) base *= 1.5;
        double powerMultiplier = 1.0 + (power / (double) maxPower) * 0.5; // up to +50%
        int rawDamage = (int) (base * powerMultiplier * lastAbilityUsed.damageMult);
        int damage = Math.max(0, rawDamage - opponent.getPlayerDefense());

        return damage;
    }

    /**
     * Updates the hitpoints of current Player based on damage dealt by opponent
     *
     * @param damage   Value of damage dealt
     * @param opponent Player object representing opponent
     * @param text     String to concatenate result to
     * @return String for UI
     * @throws InterruptedException
     */
    public String applyDamage(int damage, Player opponent, String text) throws InterruptedException {
        assert damage > 0 : "damage value must be non-negative";

        this.hp = Math.max(0, this.hp - damage);

        String textToPrint;

        if (damage > 10) {
            textToPrint = "[Narrator] It's a devastating blow from " + opponent.name + " doing " + damage + " damage!";
        } else if (damage == 0) {
            textToPrint = "[Narrator] But the attack glances harmlessly off " + name + "'s armor!";
        } else {
            textToPrint = "[Narrator] " + opponent.name + " hits " + name + " for " + damage + " damage.";
        }

        TypewriterEffect.print(textToPrint, 1000);
        System.out.print("\007");

        updateAbilityCooldown();
        updatePower();
        applyAbilityAdditionalFeatures(lastAbilityUsed);

        return text + textToPrint + "\n";
    }

    /**
     * Recovers Player hitpoints
     *
     * @param amount value of hitpoints to recover
     */
    public void heal(int amount) {
        assert amount >= 0 : "amount to heal must be non-negative";

        this.hp = Math.min(maxHp, this.hp + amount);
    }

    /**
     * Gives control to player or computer to make battle decisions
     *
     * @return an Ability object
     * @throws InterruptedException
     */
    public Ability chooseAbility() throws InterruptedException {
        Ability chosenAbility = null;

        if (isHuman) {
            chosenAbility = showUserMenu();
            if (chosenAbility == null) {
                return null;
            }
        } else {
            chosenAbility = chooseAIAction();
        }

        chosenAbility.startCooldown();
        power = Math.max(0, power - chosenAbility.powerCost); // in case AI has no viable ability and has to revert to basic attack

        TypewriterEffect.print("[Narrator] " + name + " uses " + chosenAbility.name, 800);
        Thread.sleep(0);
        return chosenAbility;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    /**
     * Prints Player options for each specified round of battle
     *
     * @return an Ability object
     * @throws InterruptedException
     */
    private Ability showUserMenu() throws InterruptedException {
        System.out.println("\n" + name + " — choose an action:");
        for (int i = 0; i < abilities.size(); i++) {
            Ability a = abilities.get(i);
            String status = a.isReady(power) ? "" : "(cooldown or insufficient power)";
            System.out.printf("%d. %s %s (%s) %s\n", i + 1, a.icon, a.name, a.tags, status);
        }
        System.out.println("Type exit to return to Main Menu");

        while (true) {
            // Ensure Scanner is valid and handle exceptions
            if (!scanner.hasNextLine()) {
                System.out.println("[DEBUG] Scanner has no next line. Recovering...");
                scanner = new Scanner(System.in); // Reset scanner if needed
            }

            try {
                if (System.in.available() > 0) {
                    scanner.nextLine();  // Consume leftover newline
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            String input = scanner.nextLine().trim();
            if (input.equals("exit")) {
                return null;
            }
            int intInput = -1;
            try {
                intInput = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                Thread.sleep(1000);
            }

            if (intInput == -1) continue;

            if (intInput <= 0 || intInput > abilities.size()) {
                continue;
            }

            // todo update the typed string for unique ability types
            Ability chosenAbility = abilities.get(intInput - 1);
            if (chosenAbility.isReady(power)) {
                //
                lastAbilityUsed = chosenAbility;
                return chosenAbility;
            } else {
                System.out.println("On cooldown. Try another ability");
                Thread.sleep(1000);
                continue;
            }
        }
    }


    // todo: fix the ai
    private Ability chooseAIAction() {
        List<Ability> readyAbilities = abilities.stream()
                .filter(a -> a.isReady(power))
                .collect(Collectors.toList());

        // Prioritize HEALING if low HP
        if (hp < maxHp * 0.4) {
            for (Ability a : readyAbilities) {
                if (a.name.equalsIgnoreCase("Heal")) {
                    lastAbilityUsed = a;
                    return a;
                }
            }
        }

        // If power is high, use a powerful skill if available
        if (power >= 50) {
            for (Ability a : readyAbilities) {
                if (a.powerCost >= 30 && !a.name.equalsIgnoreCase("Heal")) {
                    lastAbilityUsed = a;
                    return a;
                }
            }
        }

        // Fallback to any offensive move
        for (Ability a : readyAbilities) {
            if (!a.name.equalsIgnoreCase("Heal")) {
                lastAbilityUsed = a;
                return a;
            }
        }

        // Last resort: heal if possible
        for (Ability a : readyAbilities) {
            lastAbilityUsed = a;
            return a;
        }

        // No abilities ready → do normal attack
        lastAbilityUsed = new BasicAttack();
        return lastAbilityUsed;
    }

    /**
     * Decrements all Ability cooldowns by 1
     */
    private void updateAbilityCooldown() {
        for (Ability a : abilities) {
            a.tickCooldown();
        }
    }

    /**
     * Checks if the Player has learnt a specific Ability
     *
     * @param name String variable containing Ability name
     * @return true if Ability present, false otherwise
     */
    public boolean hasAbility(String name) {
        assert name != null : "ability to be searched cannot be null";

        for (Ability a : abilities)
            if (a.name.equalsIgnoreCase(name)) return true;
        return false;
    }

    /**
     * Apply special effects of Abilities
     *
     * @param ability
     */
    public void applyAbilityAdditionalFeatures(Ability ability) {
        ability.additionalFeatures(this);
    }

    /**
     * Increment the Power value after each battle round
     */
    public void updatePower() {
        updatePower(10);
    }

    /**
     * Increment the Power value by a variable amount after each battle round
     */
    public void updatePower(int powerVal) {
        assert powerVal >= 0 : "power value must be non-negative";

        power = Math.min(maxPower, power + powerVal);
    }

    /**
     * Reset all Ability cooldowns such that all become usable
     */
    public void resetAllCooldowns() {
        for (Ability a : abilities) {
            a.resetCooldown();
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Add name and player type (Human or AI)
        sb.append("🧍 ").append(name).append(isHuman ? "" : " (AI)").append("\n");
        // Add HP information
        sb.append("HP      : ").append(hp).append("/").append(maxHp).append(" ❤️\n");

        // Add Power bar
        sb.append("Power   : ").append(drawPowerBar(power, maxPower)).append("\n");
        // Add equipment list
        for (Equipment equipment : equipmentList) {
            if (equipment.getId() != -1) {
                sb.append(equipment.toString()).append("\n");
            } else {
                sb.append("Empty slot\n");
            }
        }
        if(isHuman) sb.append("Gold: ").append(gold).append("\n");

        // Add abilities
        sb.append("Abilities:\n");
        for (int i = 0; i < abilities.size(); i++) {
            Ability a = abilities.get(i);
            String status = a.isCDReady() ? "✅ ready" : "⏳ " + a.currentCoolDown + " turn(s)";
            sb.append(String.format(" %d. %s %s %s\n", i + 1, a.icon, a.name, status));
        }


        // Return the final string representation
        return sb.toString();
    }

    /**
     * Overloaded constructor to load player from save file
     */

    public Player(int wave, String name, int hp, int maxHp, int baseAttack, int numDice,
                  List<Equipment> equipmentList,
                  int gold, int power, int maxPower) {
        this.abilities.add(new Flee());
        this.abilities.add(new BasicAttack());
        this.abilities.add(new PowerStrike());
        this.abilities.add(new Heal());
        if (wave > 2) this.abilities.add(new Whirlwind());
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.baseAttack = baseAttack;
        this.diceRolls = new int[numDice];
        this.equipmentList = equipmentList;
        this.gold = gold;
        this.power = power;
        this.maxPower = maxPower;
        this.isHuman = true;
    }

    /**
     * Returns encoded string of player data to be saved
     *
     * @return encoded text
     */

    public String toText() {
        String equipmentsText = "";
        for (Equipment equipment : equipmentList) {
            equipmentsText += equipment.toText() + SAVE_DELIMITER;
        }

        return this.name + SAVE_DELIMITER +
                this.hp + SAVE_DELIMITER +
                this.maxHp + SAVE_DELIMITER +
                this.baseAttack + SAVE_DELIMITER +
                this.diceRolls.length + SAVE_DELIMITER +
                equipmentsText +
                this.gold + SAVE_DELIMITER +
                this.power + SAVE_DELIMITER +
                this.maxPower;
    }
}
