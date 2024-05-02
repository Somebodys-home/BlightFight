import java.util.ArrayList;

public class Mushrooms extends Entity {
    private Blight blight;
    private Memorial memorial;
    private boolean mushroomsAlive = false;
    private ArrayList<Mushrooms> mushroomsOnTheField;

    public Mushrooms(Blight blight, Memorial memorial) {
        super("Mushrooms", 25);
        this.blight = blight;
        this.memorial = memorial;
        checkIfAMushroomIsAlive();
    }

    public boolean checkIfAMushroomIsAlive() {
        return !mushroomsOnTheField.isEmpty();
    }
    public void addMushroom(Mushrooms mushroom) { // NTS: use this whenever you make a new mushroom object
        mushroomsOnTheField.add(mushroom);
    }

    public void damageMushrooms(int damage) {
        int bodyCount = 0;
        while (damage >= 25) {
            damage--;
            bodyCount++;
        }
        for (int i = bodyCount; i > 0; i--) {
            mushroomsOnTheField.removeFirst();
        }
    }
}