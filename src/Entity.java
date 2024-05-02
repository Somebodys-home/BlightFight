public class Entity {
    private final String name;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private int dodge;

    public Entity(String name, int health, int attack, int defense, int speed, int dodge) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.dodge = dodge;
    }

    public Entity(String name, int health) { // for memorial summons
        this.name = name;
        this.health = health;
        attack = 0;
        defense = 0;
        speed = -1;
        dodge = 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public void heal(int heal) {
        health += heal;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
