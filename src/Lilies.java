import java.util.ArrayList;

public class Lilies extends Entity {
    private Blight blight;
    private Memorial memorial;
    private boolean liliesAlive = false;
    private ArrayList<Lilies> liliesOnTheField = new ArrayList<>();

    public Lilies(Blight blight, Memorial memorial) {
        super("Lily", 10);
        this.blight = blight;
        this.memorial = memorial;
        checkIfALilyIsAlive();
    }

    public boolean checkIfALilyIsAlive() {
        return !liliesOnTheField.isEmpty();
    }

    public void addLily(Lilies lily) { // NTS: use this whenever you make a new lily object
        liliesOnTheField.add(lily);
    }

    public void damageLilies(int damage) {
        int bodyCount = 0;
        while (damage >= 10) {
            damage--;
            bodyCount++;
        }
        for (int i = bodyCount; i > 0; i--) {
            liliesOnTheField.removeFirst();
        }
        checkIfALilyIsAlive();
    }
}
