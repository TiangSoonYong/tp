package events;

import players.abilities.Ability;
import players.abilities.AbilityType;
import players.abilities.Crush;
import players.abilities.PowerStrike;
import players.abilities.Whirlwind;
import players.Player;
import equipments.armors.Armor;
import equipments.Equipment;
import equipments.weapons.Weapon;
import equipments.EmptySlot;
import functions.DiceBattleAnimation;
import functions.TypewriterEffect;
import exceptions.RolladieException;
import functions.ui.Narrator;
import functions.ui.BattleDisplay;
import functions.ui.HpBar;


import java.util.List;
import java.util.ArrayList;

import static functions.ui.Narrator.END_DELAY;

public class Battle extends Event {
    private int wave;
    private Player enemy;

    public Battle(Player player, int wave) {
        super(player);
        this.wave = wave;
        this.enemy = generateNewEnemy(wave);
    }

    @Override
    public void run() {
        try {

            startGameLoop(this.player, this.wave);
        } catch (InterruptedException | RolladieException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Main game loop logic
     *
     * @param player  player character
     * @param wave    the number of enemies encountered so far
     * @throws InterruptedException
     */
    public void startGameLoop(Player player, int wave) throws InterruptedException, RolladieException {
        assert player != null: "player cannot be null";
        assert player.isAlive(): "player must be alive";
        assert wave > 0: "Number of enemy encountered must be at least 1";

        System.out.println("🌊 Encounter " + wave + " begins!");

        if (!this.enemy.isAlive()) {
            this.enemy = generateNewEnemy(wave); // todo make tougher per wave
        }

        startBattle(player, enemy);

        if (!player.isAlive()) {
            TypewriterEffect.print("💀 You fell at encounter " + wave, END_DELAY);
            return;
        }

        // Heal partially, recharge power
        System.out.println("🍃 You survived! Regaining strength...");
        player.resetAllCooldowns();
        player.hp = Math.min(player.maxHp, player.hp + 10);
        player.power = Math.min(player.maxPower, player.power + 20);

        if (wave == 3 && !player.hasAbility("Whirlwind")) {
            player.abilities.add(new Whirlwind());
            TypewriterEffect.print("🔥 You’ve learned Whirlwind!", END_DELAY);
        }

        if (wave == 5) {
            player.obtainEquipment(new Weapon("Flame Blade", 5));
            TypewriterEffect.print("🗡️ You obtained the Flame Blade!", 1000);
        }
    }



    /**
     * Creates a new enemy when the previous one is defeated, increasing difficulty as wave progresses
     */
    public static Player generateNewEnemy(int wave) {
        assert wave > 0: "Number of enemy encountered must be at least 1";

        Weapon claws = new Weapon("Claws", 1 + wave / 2);
        Armor hide = new Armor("Hide", 1 + wave / 2);
        List<Equipment> equipmentList = new ArrayList<Equipment>(List.of(hide, new EmptySlot(), claws));
        Player enemy = new Player("Enemy " + wave, 30 + wave * 20, (3 + wave) / 2, 2, equipmentList, false);

        enemy.abilities.add(new PowerStrike());
        if (wave >= 3){
            enemy.abilities.add(new Crush());
        }

        return enemy;
    }

    /**
     * Begins a loop battle scenario with an opponent. Exits when either one is killed
     *
     * @param player1
     * @param player2
     * @throws InterruptedException
     */
    private void startBattle(Player player1, Player player2) throws InterruptedException, RolladieException {
        assert player1 != null: "player1 cannot be null";
        assert player2 != null: "player2 cannot be null";

        int round = 1;

        while (player1.isAlive() && player2.isAlive()) {
            System.out.println("\n================ ROUND " + round + " ================\n");

            BattleDisplay.showPlayerStatus(player1);
            BattleDisplay.showPlayerStatus(player2);

            // Choose Abilities
            Ability p1Ability = player1.chooseAbility();
            if(p1Ability == null) {
                this.isExit = true;
                return;
            }
            if(p1Ability.type.equals(AbilityType.FLEE)) {
                if(tryToFlee(player1, player2)) {
                    System.out.println("[Narrator] You escaped...for now!");
                    hasWon = false;
                    return;
                }
                System.out.println("[Narrator] Fate was not on your side!");
            }
            Ability p2Ability = player2.chooseAbility();
            System.out.println();

            System.out.println("[Narrator] Dice roll determines the fate of this round!");
            Thread.sleep(0);

            // Dice Roll + Animation
            String diceDisplay = DiceBattleAnimation.animateBattle(player1.getDiceRolls(), player2.getDiceRolls());

            // Store HP before damage
            int prevHp1 = player1.hp;
            int prevHp2 = player2.hp;

            // Damage
            int p1Damage = player1.computeDamageTo(player2);
            int p2Damage = 0;
            if (p1Damage < player2.hp) {
                p2Damage = player2.computeDamageTo(player1);
            }

            diceDisplay = player2.applyDamage(p1Damage, player1, diceDisplay);
            Narrator.commentOnMomentum(player1, player2, p1Damage, player2.hp);

            diceDisplay = player1.applyDamage(p2Damage, player2, diceDisplay);
            Narrator.commentOnMomentum(player2, player1, p2Damage, player1.hp);

            // Show result messages
            // System.out.printf("\n%s dealt %d damage to %s\n", player1.name, p1Damage, player2.name);
            // System.out.printf("%s dealt %d damage to %s\n", player2.name, p2Damage, player1.name);

            // Animate HP bars *beneath* dice
            HpBar.animate(player1, player2, prevHp1, prevHp2, diceDisplay);

            Narrator.commentOnHealth(player1);
            Narrator.commentOnHealth(player2);

            round++;
            Thread.sleep(0);

            if (round == 5 && !player1.hasAbility("Whirlwind")) {
                player1.abilities.add(new Whirlwind());
                TypewriterEffect.print("[Narrator] 🔥 " + player1.name +
                        " has unlocked a new ability: Whirlwind!", 1000);
            }
        }
        if (player1.isAlive()) {
            hasWon = true;
        }

        TypewriterEffect.print("\n🏁 " + (player1.isAlive() ? player1.name : player2.name) + " wins the battle!", 1000);
    }

    private boolean tryToFlee(Player player1, Player player2) throws InterruptedException {
        int[] temp = player1.diceRolls;
        player1.diceRolls = new int[player2.diceRolls.length];
        DiceBattleAnimation.animateBattle(player1.getDiceRolls(), player2.getDiceRolls());
        boolean canFlee = player1.totalRoll() > player2.totalRoll();
        player1.diceRolls = temp;
        return canFlee;
    }
}
