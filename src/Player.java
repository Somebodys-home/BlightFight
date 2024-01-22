public class Player {
    private final String name;
    private int health;

    private String[] conditions = {"-", "-", "-", "-"};
    private int paralysisTimer = -1;
    private int weaknessTimer = -1;
    private int passiveDamageTimer = -1;

    private int blindnessTimer = -1;

    public int getHealth() {
        return health;
    }

    public void subtractHealth(int subtract) {
        health -= subtract;
    }

    public String[] getConditions() {
        return conditions;
    }
    public Player(String name) {
        this.name = name;
        health = 150;
    }
    public void setCondition(String condition) { // sets condition + condition timer
        boolean dupes = false;

        for (int i = 0; i < conditions.length; i++) { // dupe clause
            if (conditions[i].equals(condition)) {
                dupes = true;
                break;
            }
        }
        if (!dupes) {
            for (int i = 0; i < conditions.length; i++) { // implement
                if (conditions[i].equals("-")) {
                    conditions[i] = condition;
                    break;
                }
            }
            if (condition.equals("Paralysis")) { // start timer
                paralysisTimer = 2;
            } else if (condition.equals("Weakness")) {
                weaknessTimer = 3;
            } else if (condition.equals("Passive damage")) {
                passiveDamageTimer = 6;
            } else if (condition.equals("Blindness")) {
                blindnessTimer = 3;
            }
        }
    }

    public int wheresCondition(String condition) {
        for (int i = 0; i < conditions.length; i++) {
            if (condition.equals(conditions[i])) {
                return i; // Return the position if found
            }
        }
        return -1; // Return -1 if the target is not found in the array
    }

    public void updateConditions() { // reduces timers
        if (wheresCondition("Paralysis") > -1) {
            paralysisTimer--;
        } else if (wheresCondition("Weakness") > -1) {
            weaknessTimer--;
        } else if (wheresCondition("Passive damage") > -1) {
            passiveDamageTimer--;
        } else if (wheresCondition("Blindness") > -1) {
            blindnessTimer--;
        }
    }

    public void updateConditionArray() {
        // conditions -> "-"
        if (wheresCondition("Paralysis") > -1) {
            if (paralysisTimer == 0) {
                System.out.println(Colors.YELLOW + "The paralysis afflicting you ends!" + Colors.RESET);
                deleteCondition("Paralysis");
                updateConditionHyphens();

                paralysisTimer = -1;
            }
        }
        if (wheresCondition("Weakness") > -1) {
            if (weaknessTimer == 0) {
                System.out.println(Colors.YELLOW + "The weakness afflicting you ends!" + Colors.RESET);
                deleteCondition("Weakness");
                updateConditionHyphens();
                weaknessTimer = -1;
            }

        }
        if (wheresCondition("Passive damage") > -1) {
            if (passiveDamageTimer != 0) {
                System.out.println("The lingering spell hurts you for 8 health!");
                health -= 8;
                passiveDamageTimer--;
            }
            if (passiveDamageTimer == 0) {
                System.out.println(Colors.YELLOW + "The lingering damage from a previous spell afflicting you ends!" + Colors.RESET);
                deleteCondition("Passive damage");
                updateConditionHyphens();
                passiveDamageTimer = -1;
            }
        }
        if (wheresCondition("Blindness") > -1) {
            if (blindnessTimer == 0) {
                System.out.println(Colors.YELLOW + "Your vision has cleared!" + Colors.RESET);
                deleteCondition("Blindness");
                updateConditionHyphens();
                blindnessTimer = -1;
            }
        }
    }

    public void updateConditionHyphens() {
        for (int i = 1; i < conditions.length - 1; i++) {
            if (conditions[i - 1].equals("-")) {
                conditions[i - 1] = conditions[i];
            }
        }
    }
    public void deleteCondition(String condition) {
        for (int i = 0; i < conditions.length; i++) {
            if (conditions[i].equals(condition)) {
                conditions[i] = "-";
            }
        }
    }
    public void failCase() {
        for (int i = 1; i < conditions.length; i++) {
            String previousCondition = conditions[i - 1];
            if (previousCondition.equals(conditions[i])) {
                conditions[i] = "-";
            }
        }
    }
    public void reset() {
        health = 150;
        weaknessTimer = -1;
        paralysisTimer = -1;
        passiveDamageTimer = -1;
        blindnessTimer = -1;
        updateConditionArray();
        updateConditionHyphens();
        System.out.println("You feel yourself return to your prime!");
    }
}
