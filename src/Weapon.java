public class Weapon {
    private String weaponName;
    private int attack;
    private int dodge;

    public Weapon(String weaponName, int attack, int dodge) {
        this.attack = attack;
        this.dodge = dodge;
        this.weaponName = weaponName;
    }

    public int getAttack() {
        return attack;
    }

    public int getDodge() {
        return dodge;
    }

    public String getWeaponName() {
        return weaponName;
    }
}
