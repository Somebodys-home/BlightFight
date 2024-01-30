public class Mushrooms {
    private Blight blight;
    private Memorial memorial;
    public Mushrooms(Blight blight, Memorial memorial) {
    this.blight = blight;
    this.memorial = memorial;
    }
    private int mushroomHealth = 25;
    private int mushroomCount = 0;

    public int getMushroomHealth() {
        return mushroomHealth;
    }

    public int getMushroomCount() {
        return mushroomCount;
    }

    public void addMushrooms(int mushroomCount) {
        this.mushroomCount += mushroomCount;
    }
    public void removeMushrooms(int mushroomCount) {
        this.mushroomCount -= mushroomCount;
    }

    public void setMushroomHealth(int mushroomHealth) {
        this.mushroomHealth = mushroomHealth;
    }
}