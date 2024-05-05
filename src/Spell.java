public class Spell {
    private String name;
    private int spellDamage;
    private int spellDamageMultiple;
    private Condition condition;
    private String effect;

    public Spell(String name, int spellDamage, int spellDamageMultiple) {
        this.name = name;
        this.spellDamage = spellDamage;
        this.spellDamageMultiple = spellDamageMultiple;
    }

    public Spell(String name, Condition condition) {
        this.name = name;
        this.condition = condition;
    }

    public Spell(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    public Spell(String name, int spellDamage, int spellDamageMultiple, Condition condition) {
        this.name = name;
        this.spellDamage = spellDamage;
        this.spellDamageMultiple = spellDamageMultiple;
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public String getEffect() {
        return effect;
    }

    public String getName() {
        return name;
    }

    public int getSpellDamageMultiple() {
        return spellDamageMultiple;
    }

    public int getSpellDamage() {
        return spellDamage;
    }

    // PRECONDITION: this method will always be used if the spell given does damage
    public int calcSpellDamage() {
        int totalSpellDamage = 0;
        for (int i = 0; i < spellDamageMultiple; i++) { // rolling damage
            totalSpellDamage += (int) (Math.random() * spellDamage) + (spellDamage / 2) + 1;
        }
        return totalSpellDamage;
    }
}
