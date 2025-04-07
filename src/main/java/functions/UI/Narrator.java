package functions.UI;

import players.Player;
import functions.TypewriterEffect;
import equipments.Equipment;

public class Narrator {

    public static final int END_DELAY = 800;

    public static void commentOnHealth(Player p) throws InterruptedException {
        double percent = p.hp / (double) p.maxHp;

        if (p.hp <= 0) {
            TypewriterEffect.print("[Narrator] 💀 " + p.name + " has fallen in battle!", END_DELAY);
        } else if (percent <= 0.2) {
            TypewriterEffect.print("[Narrator] ⚠️ " + p.name + " is barely clinging to life!", END_DELAY);
        } else if (percent <= 0.4) {
            TypewriterEffect.print("[Narrator] " + p.name + " is severely wounded!", END_DELAY);
        } else if (percent >= 0.9) {
            TypewriterEffect.print("[Narrator] " + p.name + " is looking nearly untouched.", END_DELAY);
        } else if (percent >= 0.7) {
            TypewriterEffect.print("[Narrator] " + p.name + " is holding up well.", END_DELAY);
        } else {
            // Between 40% and 70%
            TypewriterEffect.print("[Narrator] " + p.name + " is still standing strong.", END_DELAY);
        }
    }

    public static void commentOnMomentum(Player attacker, Player defender, int damageDealt, int previousDefenderHp) throws InterruptedException {
        double hpPercentBefore = previousDefenderHp / (double) defender.maxHp;
        double hpPercentAfter = defender.hp / (double) defender.maxHp;
    
        if (damageDealt >= 30) {
            TypewriterEffect.print("[Narrator] 💥 Massive impact! " + attacker.name + " lands a crushing hit!", END_DELAY);
        } else if (damageDealt >= 15) {
            TypewriterEffect.print("[Narrator] 😲 " + attacker.name + " delivers a powerful strike!", END_DELAY);
        }
        else {
            TypewriterEffect.print("[Narrator] 😲 " + attacker.name + " delivers a mediocre blow.", END_DELAY);
        }
        // Comeback detection
        double attackerHp = attacker.hp / (double) attacker.maxHp;
        double defenderHp = defender.hp / (double) defender.maxHp;
        if (attackerHp < 0.3 && defenderHp > 0.7 && damageDealt > 30) {
            TypewriterEffect.print("[Narrator] 🤯 Unbelievable! " + attacker.name + " fights back against the odds!", END_DELAY);
        }
    
        // Sudden swing (drop below critical)
        if (hpPercentBefore > 0.4 && hpPercentAfter <= 0.2) {
            TypewriterEffect.print("[Narrator] ⚠️ " + defender.name + " is in critical condition after that blow!", END_DELAY);
        }
    
        // Domination
        if ((attacker.hp - defender.hp) > 20) {
            TypewriterEffect.print("[Narrator] " + attacker.name + " is completely dominating the arena!", END_DELAY);
        }
    
        // Clutch survival
        if (defender.hp <= 3 && defender.hp > 0) {
            TypewriterEffect.print("[Narrator] 😬 " + defender.name + " barely survives with a sliver of health!", END_DELAY);
        }
    }

    public static void commentOnLootEntry() {
        TypewriterEffect.print("[Narrator] As you won, here's your loot after the battle!");
    }

    public static void commentOnLootDefeat() {
        TypewriterEffect.print("[Narrator] As you did not win, you don't deserve loot!");
    }

    public static void commentOnShopEntry() {
        TypewriterEffect.print("[Narrator] You enter an Equipment Shop! Upgrade your Stats here!");
    }

    public static void commentOnShopBuy(Player p, Equipment equipment) throws InterruptedException {
        TypewriterEffect.print("[Narrator] You buy " + equipment + " for " + equipment.getValue() + " gold.");
        System.out.println(p);
    }

    public static void commentOnShopSell(Player p, Equipment equipment) throws InterruptedException {
        TypewriterEffect.print("[Narrator] You sell " + equipment + " for " + equipment.getValue()/2 + " gold.");
        System.out.println(p);
    }

    public static void commentOnShopExit() {
        TypewriterEffect.print("[Narrator] You leave with your new gear, feeling stronger than ever!");
    }
    
    public static void newGameSequence() throws InterruptedException {
        TypewriterEffect.print("The tavern was thick with the stench of ale and unwashed ambition. You sat in the corner, nursing a drink, when the murmurs reached your ears.\n" + //
                        "\n" + //
                        "\"They say the Vault of Dusk holds treasures beyond imagining—gold that glows like embers, relics that whisper forgotten spells, and the Crown of the Fallen King, said to grant power over life itself.\"\n" + //
                        "\n" + //
                        "The speaker, a grizzled mercenary with a scar across his nose, scoffed. \"Aye, and it’s also cursed. Dozens have tried. Dozens have died. The vault doesn’t give up its secrets to just anyone.\"\n" + //
                        "\n" + //
                        "The other patrons laughed, but you didn’t join them. Your fingers tightened around your tankard.\n" + //
                        "\n" + //
                        "Foolhardy? Maybe.\n" + //
                        "\n" + //
                        "Determined? Absolutely.\n" + //
                        "\n" + //
                        "You’d spent years scraping by on petty jobs—guarding merchants, hunting bandits, fetching trinkets for nobles who barely glanced at you. But this? This was your chance.\n" + //
                        "\n" + //
                        "The Vault of Dusk wasn’t just a tomb of gold—it was a crucible. Only the boldest, the cleverest, or the luckiest would emerge alive. And you? You planned on being all three.\n" + //
                        "\n" + //
                        "As you stood and tossed a coin onto the table, the mercenary raised an eyebrow. \"You’re not seriously thinking of going, are you?\"\n" + //
                        "\n" + //
                        "You grinned. \"Someone’s got to claim that crown. Might as well be me.\"\n" + //
                        "\n" + //
                        "The laughter died. The room fell silent.\n" + //
                        "\n" + //
                        "And with that, you stepped out into the night, the weight of your destiny—or your doom—settling on your shoulders.", 2000);
    }
}

