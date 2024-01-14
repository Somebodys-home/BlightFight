public class Weapon {
    Player player = new Player();
    private String weaponName;
    private int minimumDamage;
    private int maximumDamage;
    private int numberOfAttacks;
    private int dodge;

    public Weapon(String weaponName, int minimumDamage, int maximumDamage, int numberOfAttacks, int dodge) {
        this.weaponName = weaponName;
        this.minimumDamage = minimumDamage;
        this.maximumDamage = maximumDamage;
        this.numberOfAttacks = numberOfAttacks;
        this.dodge = dodge;
        this.weaponName = weaponName;
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


    public void makeAttack(int dodgeAgainst) {
        for (int i = 0; i < numberOfAttacks; i++) {
            if (player.getConditions()[i] != null && player.getConditions()[i].equals("paralysis")) {
                if ((Math.random() * (99) + 1) > 30) {
                    System.out.println("You feel your muscles stiffen as you swing, impeding your attack.");
                }
            } else {
                if ((int) (Math.random() * 100) > dodgeAgainst) {
                    if (player.getConditions()[i] != null && player.getConditions()[i].equals("weakness")) {
                        System.out.println("Despite the fatigue you feel throughout your body, you hit the blight for " + (int) (Math.random() * (maximumDamage - minimumDamage) + minimumDamage) / 2 + " damage." );
                        player.setCondition(null, player.wheresCondition("weakness"));
                        System.out.println("The weakness quickly fades.");
                    } else {
                        System.out.println("You hit the blight for " + (int) (Math.random() * (maximumDamage - minimumDamage) + minimumDamage) + " damage!" );
                    }
                } else {
                    System.out.println("The blight easily deflects your attack.");
                }
            }
        }
    }
}
