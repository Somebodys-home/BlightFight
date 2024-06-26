public class Blight extends Entity {
    private int spellDamage;
    private boolean antilifeShell = false;
    private int antilifeCounter = -1;
    private final Paralysis Paralysis = new Paralysis();
    private final Weakness Weakness = new Weakness();
    private final PassiveDamage PassiveDamage = new PassiveDamage();
    private Blindness Blindness = new Blindness();
    Spell[] spellList = {new Spell("Poison Spray", 2, getAttack()),
            new Spell("Thunderwave", 1, getAttack(), Paralysis),
            new Spell("Ray of Waste", 2, getAttack(), Weakness),
            new Spell("Call lightning", PassiveDamage),
            new Spell("Sleet storm", Blindness),
            new Spell("Cure wounds" , "Heal"),
            new Spell("Moonbeam", 3, getAttack()),
            new Spell("Earthquake", 4, getAttack()),
            new Spell("Antilife shell","Antilife shell")};

    public Blight(int health, int attack, int defense, int speed, int dodge) {
        super("Blight", health, attack, defense, speed, dodge);
    }

    public boolean isAntilifeShell() {
        return antilifeShell;
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    public void castSpellPhase1(Player target) {
        Spell spell2Cast = null;
        boolean isADamagingSpell = false;
        int percent = (int) (Math.random() * 100) + 1;
        int conditionPercent = (int) (Math.random() * 100) + 1;

        if (percent > 80) {
            spell2Cast = spellList[(int) (Math.random() * 3)];
            isADamagingSpell = true;
        } else {
            spell2Cast = spellList[3];
        }

        if (isADamagingSpell) {
            target.takeDamage(spell2Cast.calcSpellDamage());
            if (conditionPercent >= 30) {
                if (spell2Cast.getCondition().equals(Paralysis)) {
                    target.giveParalysis();
                }
                if (spell2Cast.getCondition().equals(Weakness)) {
                    target.giveWeakness();
                }
                if (spell2Cast.getCondition().equals(PassiveDamage)) {
                    target.givePassiveDamage();
                }
                if (spell2Cast.getCondition().equals(Blindness)) {
                    target.giveBlindness();
                }
            }
        } else {
            target.givePassiveDamage();
        }
    }

//    public void castspellPhase2(int whichSpell, int dodge, Player player) { // todo: yes
//        spellDamage = 0;
//
//        if (spellList[whichSpell].getDamageMultiple() > 0) { // damaging spell
//            if ((int) (Math.random() * 100) + 1 > dodge && player.wheresCondition("Blindness") == -1) { // chance for player to dodge if they arent blinded
//                System.out.println("The blight casts " + spellList[whichSpell].getName() + ", but thankfullly you dodge out of the way!");
//                return;
//            }
//
//            for (int i = 0; i < spellList[whichSpell].getDamageMultiple(); i++) { // rolling damage
//                spellDamage += (int) (Math.random() * spellList[whichSpell].getDiceType()) + (spellList[whichSpell].getDiceType() / 2) + 1;
//            }
//            if (player.wheresCondition("Blindnesss") != 1) {
//                System.out.println("A previous spell blocks your vision, allowing you to get hit by the blight's " + spellList[whichSpell].getName() + " for " + spellDamage + " damage!");
//            } else {
//                System.out.println("The blight casts " + spellList[whichSpell].getName() + " which hits you for " + spellDamage + " damage!");
//                player.takeDamage(spellDamage);
//            }
//
//            if (spellList[whichSpell].getEffect() != null) { // if it has an effect
//                if ((int) (Math.random() * 100) + 1 <= 33) { // 50% chance to apply the effect
//                    if (player.wheresCondition(spellList[whichSpell].getEffect()) == -1) { // if the condition isnt already there
//                        System.out.println("The spell applies the " + spellList[whichSpell].getEffect() + " condition onto you!");
//                        player.setCondition(spellList[whichSpell].getEffect());
//                    } else {
//                        System.out.println("The spell would apply the " + spellList[whichSpell].getEffect() + " condition onto you, but you already have it.");
//                    }
//                }
//            }
//        } else { // effect spell
//            if (!spellList[whichSpell].getName().equals("Cure wounds")){ // normal effect spells
//                System.out.println("The blight casts " + spellList[whichSpell].getName() + ", applying the " + spellList[whichSpell].getEffect() + " condition onto you!");
//                if (player.wheresCondition(spellList[whichSpell].getEffect()) != -1) { // if the condition isnt already there
//                    if (spellList[whichSpell].getName().equals("Sleet storm")) {
//                        player.setCondition("Blindness");
//                    } else {
//                        player.setCondition(spellList[whichSpell].getEffect());
//                    }
//                } else {
//                    System.out.println("The blight casts " + spellList[whichSpell].getName() + ", which would apply the " + spellList[whichSpell].getEffect() + " condition onto you, but you already have it.");
//                }
//
//            } else { // cure wounds
//                System.out.println("The blight casts Cure wounds, healing itself for 30 health!");
//            }
//        }
//    }
//
//    public void castspellPhase3(int whichSpell, int dodge, Player player) { // todo: oh yeah and this too
//        spellDamage = 0;
//
//        if (spellList[whichSpell].getDamageMultiple() > 0) { // damaging spell
//            if ((int) (Math.random() * 100) + 1 > dodge && player.wheresCondition("Blindness") != -1 && player.wheresCondition("Paralysis") != -1) { // chance for player to dodge if they arent blinded
//                System.out.println("The blight casts " + spellList[whichSpell].getName() + ", but thankfullly you dodge out of the way!");
//                return;
//            }
//
//            for (int i = 0; i < spellList[whichSpell].getDamageMultiple(); i++) { // rolling damage
//                spellDamage += (int) (Math.random() * spellList[whichSpell].getDiceType()) + (spellList[whichSpell].getDiceType() / 2) + 1;
//            }
//            if (player.wheresCondition("Blindness") > -1) {
//                System.out.println("Because you're blinded, you get hit by the blight's " + spellList[whichSpell].getName() + " for " + spellDamage + " damage!");
//            } else {
//                System.out.println("The blight casts " + spellList[whichSpell].getName() + " which hits you for " + spellDamage + " damage!");
//                player.takeDamage(spellDamage);
//            }
//
//            if (spellList[whichSpell].getEffect() != null) { // if it has an effect
//                if ((int) (Math.random() * 100) + 1 <= 33) { // 33% chance to apply the effect
//                    if (player.wheresCondition(spellList[whichSpell].getEffect()) == -1) { // if the condition isnt already there
//                        System.out.println("The spell applies the " + spellList[whichSpell].getEffect() + " condition onto you!");
//                        player.setCondition(spellList[whichSpell].getEffect());
//                    } else {
//                        System.out.println("The spell would apply the " + spellList[whichSpell].getEffect() + " condition onto you, but you already have it.");
//                    }
//                }
//            }
//        } else { // effect spell
//            if (!spellList[whichSpell].getName().equals("Cure wounds") && !spellList[whichSpell].getName().equals("Antilife shell")){ // normal effect spells
//
//                if (player.wheresCondition(spellList[whichSpell].getEffect()) == -1) { // if the condition isn't already there
//                    System.out.println("The blight casts " + spellList[whichSpell].getName() + ", applying the " + spellList[whichSpell].getEffect() + " condition onto you!");
//                    player.setCondition(spellList[whichSpell].getEffect());
//                    return;
//                } else {
//                    System.out.println("The blight casts " + spellList[whichSpell].getName() + ", which would apply the " + spellList[whichSpell].getEffect() + " condition onto you, but you already have it.");
//                }
//
//            } else if (spellList[whichSpell].getName().equals("Cure wounds")) { // cure wounds
//                System.out.println("The blight casts Cure wounds, healing itself for 30 health!");
//            } else if (spellList[whichSpell].getName().equals("Antilife shell")) { // antilife shell
//                System.out.println("The blight casts Antilife shell onto itself!");
//                antilifeShell = true;
//                antilifeCounter = 10;
//            }
//        }
//    }
//
//    public boolean hasSpellDamage(int whichSpell) {
//        if (spellList[whichSpell].getDamageMultiple() > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void updateAntilifeCounter() {
//        if (!antilifeShell) { // if there isnt an antilife shell is up
//            return; // does nothing
//        } else {
//            if (antilifeCounter == 0) { // if the timer = 0, the shell falls
//                antilifeCounter = -1;
//                System.out.println(Colors.YELLOW + "The blight's Antilife shell falls!" + Colors.RESET);
//                antilifeShell = false;
//                return;
//            }
//            antilifeCounter--; // all else, reduce the timer by 1
//        }
//    }
}
