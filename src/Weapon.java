public class Weapon {
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
            if ((int) (Math.random() * 100) > dodgeAgainst) {
                System.out.println("You hit the blight for " + (int) (Math.random() * (maximumDamage - minimumDamage) + minimumDamage) + " damage!" );
            } else {
                System.out.println("The blight easily deflects your attack.");
            }
        }
    }
}
