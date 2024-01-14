public class Spell {
    private String name;
    private int numOfDice;
    private int diceType;
    private String effect;

    public String getEffect() {
        return effect;
    }

    public String getName() {
        return name;
    }

    public int getDiceType() {
        return diceType;
    }

    public int getNumOfDice() {
        return numOfDice;
    }

    public Spell(String name, int numOfDice, int diceType) {
        this.name = name;
        this.numOfDice = numOfDice;
        this.diceType = diceType;
    }
    public Spell(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }
    public Spell(String name, int numOfDice, int diceType, String effect) {
        this.name = name;
        this.numOfDice = numOfDice;
        this.diceType = diceType;
        this.effect = effect;
    }
}
