import java.util.Objects;
import java.util.Scanner;
import java.util.SortedMap;

public class BlightFight {
    private Scanner scan;
    private Player player; // player variables
    private String name = "";
    private int currentPlayerHealth = 150;
    private Weapon playersWeapon;
    private String[] currentConditions;
    private String[] previousConditions;
    private int passiveDamageCount;
    private int paralysisCount;
    private int weaknessCount;
    private int turnCount;
    private Blight blight; // blight variables
    private int currentBlightHealth;
    private String stage = "1";
    private boolean blightHasAttacked = false;
    private Memorial memorial;
    private int leafShieldCount;
    public BlightFight() {}

    public void start() { // name and weapon choice
        scan = new Scanner(System.in);

        System.out.print("What's your name? ");
        name = scan.nextLine();
        player = new Player(name);

        while (name.equals("")) {
            System.out.print("No, I need a name. ");
            name = scan.nextLine();
        }
        currentConditions = player.getConditions();
        blight = new Blight(player);
        currentBlightHealth = blight.getBlightHealth();
        memorial = new Memorial(player, blight);

        System.out.println("Select your weapon out of these three: (type \"sword\", \"chakrams\", or \"scythe\")");
        System.out.println("-----LONGSWORD------");
        System.out.println("Minimum damage: 10");
        System.out.println("Maximum damage: 15");
        System.out.println("Number of attacks: 2");
        System.out.println("Dodge chance: 15%");
        System.out.println();
        System.out.println(Colors.YELLOW + "-----DUAL CHAKRAMS------");
        System.out.println("Minimum damage: 5");
        System.out.println("Maximum damage: 10");
        System.out.println("Number of attacks: 3");
        System.out.println("Dodge chance: 20%");
        System.out.println();
        System.out.println(Colors.RED + "-----GREAT SCYTHE-----");
        System.out.println("Minium damage: 25");
        System.out.println("Maximum damage: 40");
        System.out.println("Number of attacks: 1");
        System.out.println("Dodge chance: 10%" + Colors.RESET);

        String weaponChoice = scan.nextLine();

        while (weaponChoice.equals("")) {
            System.out.print("PARTICIPATE, DAMN IT!");
            System.out.println();
            System.out.println("-----LONGSWORD------");
            System.out.println("Minimum damage: 10");
            System.out.println("Maximum damage: 15");
            System.out.println("Number of attacks: 2");
            System.out.println("Dodge chance: 15%");
            System.out.println();
            System.out.println(Colors.YELLOW + "-----DUAL CHAKRAMS------");
            System.out.println("Minimum damage: 5");
            System.out.println("Maximum damage: 10");
            System.out.println("Number of attacks: 3");
            System.out.println("Dodge chance: 20%");
            System.out.println();
            System.out.println(Colors.RED + "-----GREAT SCYTHE-----");
            System.out.println("Minium damage: 25");
            System.out.println("Maximum damage: 40");
            System.out.println("Number of attacks: 1");
            System.out.println("Dodge chance: 10%" + Colors.RESET);

            weaponChoice = scan.nextLine();
        }
        while (!weaponChoice.equals("sword") && !weaponChoice.equals("chakrams") && !weaponChoice.equals("scythe")) {
            System.out.println("\nMistype?");
            System.out.println("-----LONGSWORD------");
            System.out.println("Minimum damage: 10");
            System.out.println("Maximum damage: 15");
            System.out.println("Number of attacks: 2");
            System.out.println("Dodge chance: 15%");
            System.out.println();
            System.out.println(Colors.YELLOW + "-----DUAL CHAKRAMS------");
            System.out.println("Minimum damage: 5");
            System.out.println("Maximum damage: 10");
            System.out.println("Number of attacks: 3");
            System.out.println("Dodge chance: 20%");
            System.out.println();
            System.out.println(Colors.RED + "-----GREAT SCYTHE-----");
            System.out.println("Minium damage: 25");
            System.out.println("Maximum damage: 40");
            System.out.println("Number of attacks: 1");
            System.out.println("Dodge chance: 10%" + Colors.RESET);

            weaponChoice = scan.nextLine();
        }
        if (weaponChoice.equals("sword")) {
            playersWeapon = new Weapon("Longsword", 10, 15, 2, 15, player);
        } else if (weaponChoice.equals("chakrams")) {
            playersWeapon = new Weapon("Dual chakrams", 5, 10, 3, 20, player);
        } else if (weaponChoice.equals("scythe")) {
            playersWeapon = new Weapon("Great scythe", 25, 40, 1, 10, player);
        }
        turnCount = 2;
        lore();
    }

    public void lore() {
        System.out.println("Context: What you're about to face is what's called a blight: (Press any key to continue)");
        String a = scan.nextLine();
        System.out.println("A druidic lich, essentially. (Press any key to continue)");
        a = scan.nextLine();
        System.out.println("You're an adventurer, one that has proven their bark and bite, as you have risen to enough power to face this undead paragon of nature.");
        a = scan.nextLine();
        System.out.println("Usually blights turn to undeath as a means of protecting their land, called a memorial, from attackers.");
        a = scan.nextLine();
        System.out.println("As such, both and the blight and their memorial have achieved immense power and undeath together, as the memorial can now attack against the unwanted on thier own.");
        a = scan.nextLine();
        System.out.println("The memorial expands on it's own, but instead as a cancerous grove that kills and homogenizes all wildlife around it.");
        a = scan.nextLine();
        System.out.println("A lot of surrounding nations don't like that; that's why you're here.");
        a = scan.nextLine();
        System.out.println("Are you ready to face the embodiment of nature itself?");
        a = scan.nextLine();
        stage1();
    }
    public void stage1() {
        boolean playersTurn = true;
        while (currentPlayerHealth > 0 && currentBlightHealth > 0) {
            if (playersTurn) {
                previousConditions = new String[currentConditions.length];
                for (int i = 0; i < previousConditions.length; i++) {
                    previousConditions[i] = currentConditions[i];
                }
                currentPlayerHealth = player.getHealth();
                System.out.println("\n" + name + ", you stand face-to-face against the blight, what do you do? (Type either a, pot, ps, bs)");
                System.out.println("[ Attack ]");
                System.out.println("[ Drink Health Potion ]");
                System.out.println("[ Check Player Stats ]");
                System.out.println("[ Check Blight / Memorial Stats ]");

                String userChoice = scan.nextLine();

                while (userChoice.equals("")) {
                    System.out.println("STOP TRYING TO DO NOTHING! FIGHT, YOU COWARD!");
                    System.out.println("[ Attack ]");
                    System.out.println("[ Drink Health Potion ]");
                    System.out.println("[ Check Player Stats ]");
                    System.out.println("[ Check Blight / Memorial Stats ]");

                    userChoice = scan.nextLine();
                }

                if (userChoice.equals("a")) { // attack
                    playersWeapon.makeAttack(blight.getBlightDodge());
                    currentBlightHealth -= playersWeapon.getTotalDamage();
                } else if (userChoice.equals("pot")) { // heal
                    System.out.println("Using your healing potion will heal you for 50% of your maximum health, and you can only use it once a fight.");
                    System.out.println("Are you sure you want to use this? (y / n)");

                    String confirmation = scan.nextLine(); // confirmation
                    if (!confirmation.equals("n")) {
                        currentPlayerHealth += 75;
                        System.out.println(Colors.GREEN + "You heal yourself for 75 health!" + Colors.RESET);
                        if (currentPlayerHealth > 150) {
                            currentPlayerHealth = 150;
                        }
                    }
                } else if (userChoice.equals("ps")) { // player stats
                    System.out.println(playerStats());
                    playersTurn = !playersTurn;
                } else if (userChoice.equals("bs")) { // blight stats
                    System.out.println(blightStats());
                    playersTurn = !playersTurn;
                } else {
                    System.out.println("How's that hand-eye coordination, huh?" + Colors.RED + " Note: bug that skips your turn, sorry!" + Colors.RESET);
                    System.out.println("[ Attack ]");
                    System.out.println("[ Drink Health Potion ]");
                    System.out.println("[ Check Player Stats ]");
                    System.out.println("[ Check Blight / Memorial Stats ]");

                    userChoice = scan.nextLine();
                }
            } else { // blight's turn
                player.updateConditions();
                player.updateConditionArray();
                blightAttack1();
                if (turnCount % 3 == 0) {
                    applyMemorialEffects();
                    turnCount = 0;
                }
                if (currentPlayerHealth <= 0) {
                    System.out.println(Colors.RED + "You've succumbed to the druidic attacks, becoming one with the blight and it's homogenous memorial.");
                    return;
                }
                player.failCase();
                turnCount++;
            }
            playersTurn = !playersTurn;
            blightHasAttacked = false;
        }
        if (currentBlightHealth <= 0) {
            System.out.println("\n");
            System.out.println(Colors.YELLOW + "You've bested the blight!" + Colors.RESET);
        }
        stage2confirm();
    }

    public void stage2confirm() {
        playersWeapon.upgradeWeapon();
        blight.setBlightHealth(230);
        currentBlightHealth = blight.getBlightHealth();
        player.reset();
        System.out.println("Press any button when you're ready to face the blight again.");
        String confirm = scan.nextLine();
        stage2();
    }

    public void stage2() {
        boolean playersTurn = true;
        while (currentPlayerHealth > 0 && currentBlightHealth > 0) {
            if (playersTurn) {
                previousConditions = new String[currentConditions.length];
                for (int i = 0; i < previousConditions.length; i++) {
                    previousConditions[i] = currentConditions[i];
                }
                currentPlayerHealth = player.getHealth();
                System.out.println("\nThe blight challenges you to another bout, now deadlier than the last.\"");
                System.out.println("[ Attack ]");
                System.out.println("[ Drink Health Potion ]");
                System.out.println("[ Check Player Stats ]");
                System.out.println("[ Check Blight / Memorial Stats ]");

                String userChoice = scan.nextLine();

                while (userChoice.equals("")) {
                    System.out.println("STOP TRYING TO DO NOTHING! FIGHT, YOU COWARD!");
                    System.out.println("[ Attack ]");
                    System.out.println("[ Drink Health Potion ]");
                    System.out.println("[ Check Player Stats ]");
                    System.out.println("[ Check Blight / Memorial Stats ]");

                    userChoice = scan.nextLine();
                }

                if (userChoice.equals("a")) { // attack
                    playersWeapon.makeAttack(blight.getBlightDodge());
                    currentBlightHealth -= playersWeapon.getTotalDamage();
                } else if (userChoice.equals("pot")) { // heal
                    System.out.println("Using your healing potion will heal you for 50% of your maximum health, and you can only use it once a fight.");
                    System.out.println("Are you sure you want to use this? (y / n)");

                    String confirmation = scan.nextLine(); // confirmation
                    if (!confirmation.equals("n")) {
                        currentPlayerHealth += 75;
                        System.out.println(Colors.GREEN + "You heal yourself for 75 health!" + Colors.RESET);
                        if (currentPlayerHealth > 150) {
                            currentPlayerHealth = 150;
                        }
                    }
                } else if (userChoice.equals("ps")) { // player stats
                    System.out.println(playerStats());
                    playersTurn = !playersTurn;
                } else if (userChoice.equals("bs")) { // blight stats
                    System.out.println(blightStats());
                    playersTurn = !playersTurn;
                } else {
                    System.out.println("How's that hand-eye coordination, huh?");
                    System.out.println("[ Attack ]");
                    System.out.println("[ Drink Health Potion ]");
                    System.out.println("[ Check Player Stats ]");
                    System.out.println("[ Check Blight / Memorial Stats ]");

                    userChoice = scan.nextLine();
                }
            } else { // blight's turn
                blightAttack2();
                if (currentPlayerHealth <= 0) {
                    System.out.println(Colors.RED + "You've succumbed to the druidic attacks, becoming one with the blight and it's homogenous memorial.");
                    return;
                }
                if (turnCount % 3 == 0) {
                    applyMemorialEffects();
                    turnCount = 0;
                }
                player.updateConditions();
                player.updateConditionArray();
                turnCount++;
            }
            playersTurn = !playersTurn;
            blightHasAttacked = false;
        }
        if (currentBlightHealth <= 0) {
            System.out.println(Colors.YELLOW + "Umm, there was originally gonna be a third phase, but I didn't have time to make it.");
            System.out.println("So for now, congrats!");
        }
        player.failCase();
        // stage3confirm();
    }
    // stage 2 & 3


// ----------UTILITY METHODS----------
    public void blightAttack1() {
        int percent = (int) (Math.random() * 100) + 1;
        if (!blightHasAttacked) {
            if (percent <= 20) {
                blight.castSpell1(3, playersWeapon.getDodge());
            } else {
                blight.castSpell1((int) (Math.random() * 3), playersWeapon.getDodge());
                currentPlayerHealth -= blight.getSpellDamage();
            }

            blightHasAttacked = true;
        }
    }
    public void blightAttack2() {
        int percent = (int) (Math.random() * 100) + 1;
        if (!blightHasAttacked) {
            if (percent <= 15) {
                blight.castspell2(5, playersWeapon.getDodge());
                currentBlightHealth += 30;
                if (currentBlightHealth > 230) {
                    currentBlightHealth = 230;
                }
            } else if (percent <= 25) {
                blight.castSpell1((int) (Math.random() * 2) + 3, playersWeapon.getDodge());
            } else {
                blight.castSpell1((int) (Math.random() * 3), playersWeapon.getDodge());
                currentPlayerHealth -= blight.getSpellDamage();
            }

            blightHasAttacked = true;
        }
    }
    public void applyMemorialEffects() {
        int fx = (int) (Math.random() * 3);

        while (memorial.getMemorialEffects()[fx].equals("-")) {
            fx = (int) (Math.random() * 3);
        }
        if (fx == 0) { // leaf shield
            System.out.println("You look around as moss and foliage wrap around the blight, increasing it's defences!");
            blight.setBlightDodge(25);
            leafShieldCount = 2;
        } else if (fx == 1) { // spore swarm
            String a = "";
            if ((int) (Math.random() * 2) + 1 == 2) {
                a = "Weakness";
            } else {
                a = "Paralysis";
            }
            System.out.println("You notice some mushrooms surround you and release harmful spores, as you now have the " + a + " condition!");
            player.setCondition(a);
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

        return "\nYour current health: " + currentPlayerHealth + " / 150\n" +
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


