public class Player {
    private String name;
    private int health;

    public Player(String name) {
        this.name = name;
        health = 250;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
}
