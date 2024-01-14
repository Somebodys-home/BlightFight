public class Blight {
    Player player = new Player();

    public Blight() {
    }

    private int blightHealth = 180;
    private int blightDodge = 40;
    private final int spellListLength = 9;
    private int spellDamage;
    Spell[] spellList = {new Spell("Poison Spray", 2, 12),
            new Spell("Thunderwave", 1, 10, "Paralysis"),
            new Spell("Ray of Waste", 3, 6, "Weakness"),
            new Spell("Call lightning", "Passive damage"),
            null,
            null,
            null,
            null,
            null};

    public void castSpell(int whichSpell) {
        int percent = (int) (Math.random() * 100) + 1;
        spellDamage = 0;
        if (spellList[whichSpell].getName().equals("Call lightning")) { // call lightning example
            System.out.println("The blight casts " + spellList[whichSpell].getName() + ", applying the " + spellList[whichSpell].getEffect() + " condition onto you!");
            player.setCondition(spellList[whichSpell].getEffect());
            return;
        }

         if (spellList[whichSpell].getNumOfDice() > 0) { // damaging spell
            for (int i = 0; i < spellList[whichSpell].getNumOfDice(); i++) {
                spellDamage += (int) (Math.random() * spellList[whichSpell].getDiceType()) + 1;
            }
            System.out.println("The blight casts " + spellList[whichSpell].getName() + " which hits you for " + spellDamage + " damage!");

            if (spellList[whichSpell].getEffect() != null) { // if it has an effect
                if (percent <= 50) { // 50% chance to apply the effect
                    player.setCondition(spellList[whichSpell].getEffect());
                }
            }
        }
    }

    public int getBlightHealth() {
        return blightHealth;
    }

    public void setBlightHealth(int blightHealth) {
        this.blightHealth = blightHealth;
    }

    public int getBlightDodge() {
        return blightDodge;
    }

    public void setBlightDodge(int blightDodge) {
        this.blightDodge = blightDodge;
    }

    public int getSpellListLength() {
        return spellListLength;
    }

    public int getSpellDamage() {
        return spellDamage;
    }
}
