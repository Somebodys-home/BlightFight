public class Player {
    private String name;
    private int health;

    private String[] conditions = {"-", "-", "-", "-"};

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public String[] getConditions() {
        return conditions != null ? conditions : new String[]{"No conditions"};
    }

    public void setCondition(String condition) {
        for (int i = 0; i < conditions.length; i++) {
            if (conditions[i] == null || conditions[i].equals("-")) {
                // Check if the newString is not found elsewhere in the array
                boolean isUnique = true;
                for (int j = 0; j < conditions.length; j++) {
                    if (j != i && condition.equals(conditions[j])) {
                        isUnique = false;
                        break;
                    }

                    if (isUnique) {
                        conditions[i] = condition;
                        break;
                    }
                }
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
    public void setCondition(String condition, int position) {
        conditions[position] = (condition);
    }

    public Player() {}

    public Player(String name) {
        this.name = name;
        health = 250;
    }
}
