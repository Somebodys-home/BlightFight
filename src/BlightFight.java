import java.util.Scanner;
public class BlightFight {
    private Scanner scan;
    Player player = null;
    private int currentPlayerHealth = 250;
    private Weapon playersWeapon = null;
    Blight blight = new Blight();
    private int currentBlightHealth = blight.getBlightHealth();
    private String stage = "1";
    private int passiveDamageCount;
    private int paralysisCount;
    private boolean hasWeakness;
    private boolean blightHasAttacked = false;
    private String[] currentConditions = player.getConditions();
    public BlightFight() {}

    public void start() { // name and weapon choice
        scan = new Scanner(System.in);

        System.out.print("What's your name? ");
        String name = scan.nextLine();
        player = new Player(name);

        System.out.println("Select your weapon out of these three: (type \"sword\", \"chakrams\", or \"scythe\", you will get sword if you input nothing)");
        System.out.println("-----LONGSWORD------");
        System.out.println("Minimum damage: 30");
        System.out.println("Maximum damage: 40");
        System.out.println("Number of attacks: 2");
        System.out.println("Dodge chance: 30%");
        System.out.println();
        System.out.println(Colors.YELLOW + "-----DUAL CHAKRAMS------");
        System.out.println("Minimum damage: 10");
        System.out.println("Maximum damage: 20");
        System.out.println("Number of attacks: 3");
        System.out.println("Dodge chance: 60%");
        System.out.println();
        System.out.println(Colors.RED + "-----GREAT SCYTHE-----");
        System.out.println("Minium damage: 75");
        System.out.println("Maximum damage: 100");
        System.out.println("Number of attacks: 1");
        System.out.println("Dodge chance: 10%" + Colors.RESET);

        String weaponChoice = scan.nextLine();
        if (weaponChoice.equals("sword")) {
            playersWeapon = new Weapon("Longsword", 30, 40, 2, 30);
        } else if (weaponChoice.equals("chakrams")) {
            playersWeapon = new Weapon("Dual chakrams", 10, 20, 3, 60);
        } else if (weaponChoice.equals("scythe")) {
            playersWeapon = new Weapon("Great scythe", 75, 100, 1, 10);
        } else {
            playersWeapon = new Weapon("Longsword", 30, 40, 2, 30);
        }
        stage1();
    }

    public void stage1() {
        boolean playersTurn = true;

        while (currentPlayerHealth > 0 && currentBlightHealth > 0) {
            if (playersTurn) {
                System.out.println("\nWhat would you like to do? (Type either a, pot, ps, bs)");
                System.out.println("[ Attack ]");
                System.out.println("[ Drink Health Potion ]");
                System.out.println("[ Check Player Stats ]");
                System.out.println("[ Check Blight / Memorial Stats ]");

                String userChoice = scan.nextLine();
                if (userChoice.equals("a")) { // attack
                    playersWeapon.makeAttack(blight.getBlightDodge());
                } else if (userChoice.equals("pot")) { // heal
                    System.out.println("Using your healing potion will heal you for 75% of your maximum health, and you can only use it once a fight.");
                    System.out.println("Are you sure you want to use this? (y / n)");

                    String confirmation = scan.nextLine(); // confirmation
                    if (confirmation.equals("y")) {
                        currentPlayerHealth += ((currentPlayerHealth / 4) * 3);
                    }
                } else if (userChoice.equals("ps")) { // player stats
                    System.out.println(playerStats());
                    playersTurn = !playersTurn;
                } else if (userChoice.equals("bs")) { // blight stats
                    System.out.println(blightStats());
                    playersTurn = !playersTurn;
                }
            } else { // blight's turn
                blightAttack();
                updateConditions();
            }
            playersTurn = !playersTurn;
            blightHasAttacked = false;
        }
    }

    // stage 2 & 3

public void blightAttack() {
int percent = 100;//(int) (Math.random() * 100) + 1;
    System.out.println(percent);
    if (!blightHasAttacked && stage.equals("1")) {
        if (percent <= 80) {
            blight.castSpell((int) (Math.random() * 3));
            currentPlayerHealth -= blight.getSpellDamage();
        } else {
            blight.castSpell(3);
        }

        blightHasAttacked = true;
    }
}

    public void updateConditions() {
        if (passiveDamageCount > 0) { // passive damage
            System.out.println("The blight's lingering spell hurts you for 5 damage!");
            currentPlayerHealth -= 5;
            passiveDamageCount--;

            if (passiveDamageCount == 0) { // passive damage ends
                System.out.println("The blight's lingering spell ends!");
            }
        }
        if (paralysisCount > 0) { // paralysis
            paralysisCount--;

            if (paralysisCount == 0) { // paralysis ends
                System.out.println("The paralysis afflicting you ends!");
            }
        }
    }



    public String playerStats() {
        StringBuilder conditionsString = new StringBuilder();
        for (String condition : player.getConditions()) {
            if (condition != null) {
                conditionsString.append(condition).append(", ");
            }
        }
        String conditionsOutput = !conditionsString.isEmpty() ?
                conditionsString.substring(0, conditionsString.length() - 2) : "None";

        return "\nYour current health: " + currentPlayerHealth + " / 250\n" +
                "Current conditions: " + (conditionsOutput.equals("None") ? "None" : conditionsOutput) + "\n" +
                "--------Weapon Stats------ \n" +
                "Name: " + playersWeapon.getWeaponName() + "\n" +
                "Minimum Attack: " + playersWeapon.getMinimumDamage() + "\n" +
                "Maximum Attack: " + playersWeapon.getMaximumDamage() + "\n" +
                "Number of attacks: " + playersWeapon.getNumberOfAttacks() + "\n" +
                "Dodge Chance: " + playersWeapon.getDodge() + "\n";
    }

    public String blightStats() {
        String e = "\nBlight's health: " + currentBlightHealth + "\n";

        if (stage.equals("1") || stage.equals("2")) {
            e += "Memorial Status: Harmful plants begin to surround you with the Blight's presence.";
        } else {
            e += "Memorial Status: The forest rages against you, with every step you make on its soil a threat to your life.";
        }

        return e + "\n";
    }
}


