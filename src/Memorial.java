public class Memorial {
    private Player player;
    private Blight blight;
    private Lillies lillies;
    private Mushrooms mushrooms;
    private String[] memorialEffects = {"Leaf shield", "Spore swarm", "-", "-"};

    public String[] getMemorialEffects() {
        return memorialEffects;
    }

    public Memorial(Player player, Blight blight, Lillies lillies, Mushrooms mushrooms) {
        this.player = player;
        this.blight = blight;
        this.lillies = lillies;
        this.mushrooms = mushrooms;
    }

    public void summonLillies() {
        lillies.addLillies(5);
    }

    public void damageLillies(int damage) {
        int lilliesKilled = 0;
        while (lilliesKilled == 5 || damage < 5) {
            damage -= 5;
            lilliesKilled++;
            System.out.println("You kill a lilly!");
        }
        lillies.removeLillies(lilliesKilled);
    }

    public void summonMushrooms() {
        mushrooms.addMushrooms(2);
    }

    public void damageMushrooms(int damage) { // i cant be bothered to change variable names
        int lilliesKilled = 0;
        while (lilliesKilled == 5 || damage < 5) {
            damage -= 5;
            lilliesKilled++;
            System.out.println("You kill a mushroom!");
        }
        mushrooms.removeMushrooms(lilliesKilled);
    }
}
