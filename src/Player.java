import java.util.ArrayList;

public class Player extends Entity {
    private ArrayList<Condition> conditions = new ArrayList<>(4);
    private int numberOfAttacks;
    private boolean isParalyzed;
    private boolean isWeakened;
    private boolean isPassivelyDamaged;
    private boolean isBlinded;
    private boolean usedPotion;

    public Player(String name, int health, int attack, int defense, int speed, int dodge, int numberOfAttacks) {
        super(name, health, attack, defense, speed, dodge);
        this.numberOfAttacks = numberOfAttacks;
        isParalyzed = false;
        isWeakened = false;
        isPassivelyDamaged = false;
        isBlinded = false;
    }

    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }

    // Set condition methods
    public void giveParalysis() {
        isParalyzed = true;
    }

    public boolean isParalyzed() {
        return isParalyzed;
    }

    public void giveWeakness() {
        isWeakened = true;
    }

    public boolean isWeakened() {
        return isWeakened;
    }

    public void givePassiveDamage() {
        isPassivelyDamaged = true;
    }

    public boolean isPassivelyDamaged() {
        return isPassivelyDamaged;
    }

    public void giveBlindness() {
        isBlinded = true;
    }

    public boolean isBlinded() {
        return isBlinded;
    }


    public boolean checkForCondition(String conditionName) {
        for (Condition condition1 : conditions) {
            if (condition1.getConditionName().equals(conditionName)) {
                return true;
            }
        }
        return false;
    }


    public void updateConditionArray() {
        for (int i = 0; i < conditions.size(); i++) {
            if (conditions.get(i).getTimer() == 0) {
                if (conditions.get(i).getConditionName().equals("Paralysis")) {
                    System.out.println(Colors.YELLOW + "The paralysis afflicting you ends!" + Colors.RESET);
                    isParalyzed = false;
                } else if (conditions.get(i).getConditionName().equals("Weakness")) {
                    System.out.println(Colors.YELLOW + "The weakness afflicting you ends!" + Colors.RESET);
                    isWeakened = false;
                } else if (conditions.get(i).getConditionName().equals("Passive Damage")) {
                    System.out.println(Colors.YELLOW + "The lingering damage from a previous spell afflicting you ends!" + Colors.RESET);
                    isPassivelyDamaged = false;
                } else if (conditions.get(i).getConditionName().equals("Blindness")) {
                    System.out.println(Colors.YELLOW + "Your vision has cleared!" + Colors.RESET);
                    isBlinded = false;
                }
            }
            conditions.get(i).resetCondition(conditions.get(i));
        }
    }

    public void resetConditions() {
        for (int i = 0; i < conditions.size(); i++) {
            conditions.get(i).resetCondition(conditions.get(i));
        }
    }

    public void printStats() {
        System.out.println(super.getName() + "'s stats:");
        System.out.println("Health: " + super.getHealth() + " / " + super.getBaselineHealth() + " ❤\uFE0F");
        System.out.println("Attack: " + super.getAttack() + " ⚔\uFE0F");
        System.out.println("Defense: " + super.getDefense() + " \uD83D\uDEE1\uFE0F");
        System.out.println("Dodge: " + super.getDodge() + "% \uD83D\uDCA8");
        System.out.println("Speed: " + super.getSpeed() + " ⏩");
        System.out.println();
        System.out.print("Current conditions:");
        for (int i = 0; i < conditions.size(); i++) {
            boolean comma = i != conditions.size() - 1;

            if (conditions.get(i) instanceof Paralysis) {
                System.out.print("Paralysis");
                if (comma) {
                    System.out.print(",");
                }
                System.out.print(" ");
            }
            if (conditions.get(i) instanceof Weakness) {
                System.out.print("Weakness");
                if (comma) {
                    System.out.print(",");
                }
                System.out.print(" ");
            }
            if (conditions.get(i) instanceof PassiveDamage) {
                System.out.print("Passive Damage");
                if (comma) {
                    System.out.print(",");
                }
                System.out.print(" ");
            }
            if (conditions.get(i) instanceof Blindness) {
                System.out.print("Blindness");
                if (comma) {
                    System.out.print(",");
                }
                System.out.print(" ");
            }
        }
    }
    public boolean useHealingPotion() {
        if (!usedPotion) {
            heal(getHealth() / 2);
            System.out.println(Colors.GREEN + "You heal yourself for " + getHealth() / 2 + " health!" + Colors.RESET);
            return true;
        } else {
            System.out.println("You've already used your healing potion!");
            return false;
        }
    }
}
