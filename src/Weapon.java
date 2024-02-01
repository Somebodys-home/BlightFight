public class Weapon {
    private Player player;
    private String weaponName;
    private int minimumDamage;
    private int maximumDamage;
    private int numberOfAttacks;
    private int dodge;
    private  int damageRoll;
    private int totalDamage;
    private boolean affectedByParalysis = false;
    private boolean affectedByWeakness = false;

    private boolean affectedByBlindness = false;

    public void setAffectedByParalysis(boolean affectedByParalysis) {
        this.affectedByParalysis = affectedByParalysis;
    }

    public void setAffectedByWeakness(boolean affectedByWeakness) {
        this.affectedByWeakness = affectedByWeakness;
    }

    public void setAffectedByBlindness(boolean affectedByBlindness) {
        this.affectedByBlindness = affectedByBlindness;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getMinimumDamage() {
        return minimumDamage;
    }

    public void setMinimumDamage(int minimumDamage) {
        this.minimumDamage = minimumDamage;
    }

    public int getMaximumDamage() {
        return maximumDamage;
    }

    public void setMaximumDamage(int maximumDamage) {
        this.maximumDamage = maximumDamage;
    }

    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public int getTotalDamage() {
        return totalDamage;
    }

    public Weapon(String weaponName, int minimumDamage, int maximumDamage, int numberOfAttacks, int dodge, Player player) {
        this.weaponName = weaponName;
        this.minimumDamage = minimumDamage;
        this.maximumDamage = maximumDamage;
        this.numberOfAttacks = numberOfAttacks;
        this.dodge = dodge;
        this.weaponName = weaponName;
        this.player = player;
    }

    public void makeAttack(int dodgeAgainst) {
        totalDamage = 0;

        if (player.wheresCondition("Weakness") != -1) { // checks if you have weakness
            affectedByWeakness = true;
        }
        if (player.wheresCondition("Paralysis") != -1) { // checks if you have paralysis
            affectedByParalysis = true;
        }
        if (player.wheresCondition("Blindness") != -1) { // checks if you have blindness
            affectedByBlindness = true;
        }

        for (int i = 0; i < numberOfAttacks; i++) { // for each attack
            damageRoll = (int) (Math.random() * (maximumDamage - minimumDamage) + minimumDamage);

            if (player.wheresCondition("Paralysis") == -1) {
                affectedByParalysis = false;
            }
            if (player.wheresCondition("Weakness") == -1) {
                affectedByWeakness = false;
            }
            if (player.wheresCondition("Blindness") == -1) {
                affectedByBlindness = false;
            }

            if (affectedByParalysis) {
                if ((Math.random() * (99) + 1) > 30) {
                    System.out.println("You feel your muscles stiffen as you swing, impeding your attack.");
                }
            } else {
                if ((int) (Math.random() * 100) >= dodgeAgainst) {
                    if (affectedByWeakness) {
                        System.out.println("Despite the fatigue, you hit the blight for " + damageRoll / 2 + " damage." );
                        totalDamage += damageRoll / 2;
                    } else {
                         System.out.println("You hit the blight for " + damageRoll + " damage!" );
                        totalDamage += damageRoll;
                    }
                } else {
                    System.out.println("The blight deflects your attack.");
                }
            }
        }
    }
    public void upgradeWeapon() {
        int upgradeStat = (int) (Math.random() * 3) + 1;
        if (upgradeStat == 1) {
            int upgrademini = (getMinimumDamage() / 5) * 6;
            System.out.println("The blight grants you a boon! Your minimum damage has increased to " + upgrademini + "!");
            setMinimumDamage(upgrademini);
        } else if (upgradeStat == 2) {
            int upgrademax = (getMaximumDamage() / 5) * 6;
            setMaximumDamage(upgrademax);
            System.out.println("The blight grants you a boon! Your maximum damage has increased to " + upgrademax + "!");
        } else if (upgradeStat == 3) {
            int upgradedodge = (int) (Math.random() * 15) + 5;
            setDodge(getDodge() + upgradedodge);
            System.out.println("The blight grants you a boon! Your dodge chance has increased to " + upgradedodge + "!");
        }
    }
}
