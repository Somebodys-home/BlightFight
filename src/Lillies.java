public class Lillies {
    private Blight blight;
    private Memorial memorial;
    public Lillies(Blight blight, Memorial memorial) {
        this.blight = blight;
        this.memorial = memorial;
    }
    private int lilyHealth = 5;
    private int lilyCount = 0;

    public int getLilyCount() {
        return lilyCount;
    }

    public int getLilyHealth() {
        return lilyHealth;
    }

    public void addLillies(int lilyCount) {
        this.lilyCount += lilyCount;
    }
    public void removeLillies(int lilyCount) {
        this.lilyCount -= lilyCount;
    }

    public void setLilyHealth(int lilyHealth) {
        this.lilyHealth = lilyHealth;
    }
}
