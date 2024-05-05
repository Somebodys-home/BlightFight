public class Condition {
    private String conditionName;
    private int timer;

    public Condition(String conditionName, int timer) {
        this.conditionName = conditionName;
        this.timer = timer;
    }

    public Condition() {
        conditionName = "-";
        timer = -1;
    }
    public void applyEffect(Player player) {}

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void resetCondition(Condition condition) {
        condition = new Condition();
    }

    public String getConditionName() {
        return conditionName;
    }

    public void updateTimer(Condition condition) {
        if (condition.getTimer() > 0) {
            condition.setTimer(condition.getTimer() - 1);
        }
        if (condition.getTimer() == 0) {
            condition.resetCondition(condition);
        }
    }
}
