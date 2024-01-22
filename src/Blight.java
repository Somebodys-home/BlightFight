import java.util.SortedMap;

public class Blight {
    private Player player;

    public Blight(Player player) {
        this.player = player;
    }

    private int blightHealth = 180;

    public void setBlightHealth(int blightHealth) {
        this.blightHealth = blightHealth;
    }
    public int getBlightHealth() {
        return blightHealth;
    }
    private int blightDodge = 2;
    public int getBlightDodge() {
        return blightDodge;
    }

    public void setBlightDodge(int blightDodge) {
        this.blightDodge = blightDodge;
    }

    Spell[] spellList = {new Spell("Poison Spray", 2, 6),
            new Spell("Thunderwave", 1, 10, "Paralysis"),
            new Spell("Ray of Waste", 2, 4, "Weakness"),
            new Spell("Call lightning", "Passive damage"),
            new Spell("Sleet storm", "Blindness"),
            new Spell("Cure wounds" , "Heal"),
            null,
            null,
            null};

    private int spellDamage;
    public int getSpellDamage() {
        return spellDamage;
    }

    public void castSpell1(int whichSpell, int dodge) {
        spellDamage = 0;

         if (spellList[whichSpell].getNumOfDice() > 0) { // damaging spell
             if ((int) (Math.random() * 100) + 1 <= dodge) { // chance for player to dodge
                 System.out.println("The blight casts " + spellList[whichSpell].getName() + ", but thankfullly you dodge out of the way!");
                 return;
             }

            for (int i = 0; i < spellList[whichSpell].getNumOfDice(); i++) { // rolling damage
                spellDamage += (int) (Math.random() * spellList[whichSpell].getDiceType()) + (spellList[whichSpell].getDiceType() / 2) + 1;
            }
            System.out.println("The blight casts " + spellList[whichSpell].getName() + " which hits you for " + spellDamage + " damage!");
            player.subtractHealth(spellDamage);

             if (spellList[whichSpell].getEffect() != null) { // if it has an effect
                 if ((int) (Math.random() * 100) + 1 <= 50) { // 50% chance to apply the effect
                     if (player.wheresCondition(spellList[whichSpell].getEffect()) != -1) { // if the condition isnt already there
                         System.out.println("The spell would apply the " + spellList[whichSpell].getEffect() + " condition onto you, but you already have it.");
                     } else {
                         System.out.println("The spell applies the " + spellList[whichSpell].getEffect() + " condition onto you!");
                         player.setCondition(spellList[whichSpell].getEffect());
                     }
                 }
             }
        } else { // effect spell
             if (player.wheresCondition(spellList[whichSpell].getEffect()) == -1) { // if the condition isnt already there
                 System.out.println("The blight casts " + spellList[whichSpell].getName() + ", applying the " + spellList[whichSpell].getEffect() + " condition onto you!");
                 player.setCondition(spellList[whichSpell].getEffect());
             } else {
                 System.out.println("The blight casts " + spellList[whichSpell].getName() + ", which would apply the " + spellList[whichSpell].getEffect() + " condition onto you, but you already have it.");
             }

        }
    }
    public void castspell2(int whichSpell, int dodge) {
        spellDamage = 0;

        if (spellList[whichSpell].getNumOfDice() > 0) { // damaging spell
            if ((int) (Math.random() * 100) + 1 > dodge && player.wheresCondition("Blindness") == -1) { // chance for player to dodge if they arent blinded
                System.out.println("The blight casts " + spellList[whichSpell].getName() + ", but thankfullly you dodge out of the way!");
                return;
            }

            for (int i = 0; i < spellList[whichSpell].getNumOfDice(); i++) { // rolling damage
                spellDamage += (int) (Math.random() * spellList[whichSpell].getDiceType()) + (spellList[whichSpell].getDiceType() / 2) + 1;
            }
            if (player.wheresCondition("Blindnesss") != 1) {
                System.out.println("A previous spell blocks your vision, allowing you to get hit by the blight's " + spellList[whichSpell].getName() + " for " + spellDamage + " damage!");
            } else {
                System.out.println("The blight casts " + spellList[whichSpell].getName() + " which hits you for " + spellDamage + " damage!");
                player.subtractHealth(spellDamage);
            }

            if (spellList[whichSpell].getEffect() != null) { // if it has an effect
                if ((int) (Math.random() * 100) + 1 <= 50) { // 50% chance to apply the effect
                    if (player.wheresCondition(spellList[whichSpell].getEffect()) == -1) { // if the condition isnt already there
                        System.out.println("The spell applies the " + spellList[whichSpell].getEffect() + " condition onto you!");
                        player.setCondition(spellList[whichSpell].getEffect());
                    } else {
                        System.out.println("The spell would apply the " + spellList[whichSpell].getEffect() + " condition onto you, but you already have it.");
                    }
                }
            }
        } else { // effect spell
            if (!spellList[whichSpell].getName().equals("Cure wounds")){ // normal effect spells
                System.out.println("The blight casts " + spellList[whichSpell].getName() + ", applying the " + spellList[whichSpell].getEffect() + " condition onto you!");
                if (player.wheresCondition(spellList[whichSpell].getEffect()) != -1) { // if the condition isnt already there
                    if (spellList[whichSpell].getName().equals("Sleet storm")) {
                        player.setCondition("Blindness");
                    } else {
                        player.setCondition(spellList[whichSpell].getEffect());
                    }
                } else {
                    System.out.println("The blight casts " + spellList[whichSpell].getName() + ", which would apply the " + spellList[whichSpell].getEffect() + " condition onto you, but you already have it.");
                }

            } else { // cure wounds
                System.out.println("The blight casts Cure wounds, healing itself for 30 health!");
            }
        }
    }
}
