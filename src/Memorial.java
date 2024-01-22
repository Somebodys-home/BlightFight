public class Memorial {
    private Player player;
    private Blight blight;
    private String[] memorialEffects = {"Leaf shield", "Spore swarm", "-", "-"};

    public String[] getMemorialEffects() {
        return memorialEffects;
    }

    public Memorial(Player player, Blight blight) {
        this.player = player;
        this.blight = blight;
    }
}
