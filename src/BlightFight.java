import java.util.Scanner;

public class BlightFight {
    private Scanner scan;
    private Player player; // player variables
    private String name = "";
    private int currentPlayerHealth = 150;
    private Weapon playersWeapon;
    private String[] currentConditions;
    private String[] previousConditions;
    private int turnCount;
    private Blight blight; // blight variables
    private int currentBlightHealth;
    private boolean blightHasAttacked = false;
    private Memorial memorial; // memorial variables
    private Lilies lilies; // lilies
    private Mushrooms mushrooms;
    private int leafShieldCount = -1;
    private int stage = "1";
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
        memorial = new Memorial(player, blight, lilies, mushrooms);

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
        System.out.println("Maximum damage: 30");
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
            System.out.println("Maximum damage: 30");
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
            System.out.println("Maximum damage: 30");
            System.out.println("Number of attacks: 1");
            System.out.println("Dodge chance: 10%" + Colors.RESET);

            weaponChoice = scan.nextLine();
        }
        if (weaponChoice.equals("sword")) {
            playersWeapon = new Weapon("Longsword", 10, 15, 2, 15, player);
        } else if (weaponChoice.equals("chakrams")) {
            playersWeapon = new Weapon("Dual chakrams", 5, 10, 3, 20, player);
        } else if (weaponChoice.equals("scythe")) {
            playersWeapon = new Weapon("Great scythe", 25, 30, 1, 10, player);
        }
        turnCount = 2;
        lore();
    }

    public void lore() {
        System.out.println("Context: What you're about to face is what's called a blight: (Press enter to continue)");
        String a = scan.nextLine();
        System.out.println("A druidic lich, essentially. (Press enter to continue)");
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
        System.out.print("Are you ready to face the embodiment of nature itself? (Spoiler Alert: Your answer doesn't matter, free choice is a myth.)");
        a = scan.nextLine();

        lilies = new Lilies(blight, memorial);
        mushrooms = new Mushrooms(blight, memorial);
        memorial = new Memorial(player, blight, lilies, mushrooms);

        stage1();
    }
    public void stage1() {
        boolean playersTurn = true;
        boolean alreadyHealed = false;

        while (currentPlayerHealth > 0 && currentBlightHealth > 0) {
            if (playersTurn) {
                previousConditions = new String[currentConditions.length];
                for (int i = 0; i < previousConditions.length; i++) {
                    previousConditions[i] = currentConditions[i];
                }
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
                    if (!alreadyHealed) {
                        System.out.println("Using your healing potion will heal you for 50% of your maximum health, and you can only use it once a fight.");
                        System.out.println("Are you sure you want to use this? (y / n)");

                        String confirmation = scan.nextLine(); // confirmation
                        if (!confirmation.equals("n")) {
                            currentPlayerHealth += 75;
                            System.out.println(Colors.GREEN + "You heal yourself for 75 health!" + Colors.RESET);
                        }
                        if (currentPlayerHealth > 150) {
                            currentPlayerHealth = 150;
                        }
                        alreadyHealed = true;
                    } else {
                        System.out.println("No, you already healed!");
                        playersTurn = !playersTurn;
                    }
                } else if (userChoice.equals("ps")) { // player stats
                    System.out.println(playerStats());
                    playersTurn = !playersTurn;
                } else if (userChoice.equals("bs")) { // blight stats
                    System.out.println(blightStats());
                    playersTurn = !playersTurn;
                } else if (userChoice.equals("/kill blight")) {
                    currentBlightHealth -= 500;
                } else {
                    System.out.println("How's that hand-eye coordination, huh?");
                    playersTurn = !playersTurn;
                }
            } else { // blight's turn
                player.updateConditions();
                player.updateConditionArray();
                blightAttack1();
                if (turnCount % 4 == 0) {
                    applyMemorialEffects();
                    turnCount = 0;
                }
                if (leafShieldCount > 0) {
                    leafShieldCount--;

                    if (leafShieldCount == 0) {
                        System.out.println(Colors.YELLOW + "The blight's shield falls!" + Colors.RESET);
                        blight.revertBlightDodge();
                        leafShieldCount = -1;
                    }
                }
                player.failCase();
                turnCount++;
            }
            playersTurn = !playersTurn;
            blightHasAttacked = false;
        }
        if (currentPlayerHealth <= 0) {
            System.out.println(Colors.RED + "You've succumbed to the druidic attacks, becoming one with the blight and it's homogenous memorial.");
            return;
        }
        if (currentBlightHealth <= 0) {
            if (leafShieldCount > 0) {
                leafShieldCount--;

                if (leafShieldCount == 0) {
                    System.out.println(Colors.YELLOW + "The blight's shield falls!" + Colors.RESET);
                    blight.revertBlightDodge();
                    leafShieldCount = -1;
                }
            }
            System.out.println(Colors.YELLOW + "You've bested the blight!" + Colors.RESET);
        }
        stage2confirm();
    }

    public void stage2confirm() {
        System.out.println("Whoops, I forgot to tell you a bunch. (Press enter to continue.)");
        String confirm = scan.nextLine();
        System.out.println("An important thing about blights are their death bloom. (Press enter to continue.)");
        confirm = scan.nextLine();
        System.out.println("A death bloom is a flower that spawns wherever a druid finishes their transformation into a blight.");
        confirm = scan.nextLine();
        System.out.println("It's the start of a memorial's spread, and it's the blight's phylactery; where their soul resides that allows them to perserve their undeath.");
        confirm = scan.nextLine();
        System.out.println("As an immensely powerful magical object, the death bloom's vicinity is where a blight and memorial are strongest...");
        confirm = scan.nextLine();
        System.out.println("...but their power grows from the death bloom up to ten miles away from it.");
        confirm = scan.nextLine();
        System.out.println("What you just fought is the blight when it's over ten miles away from it's death bloom.");
        confirm = scan.nextLine();
        System.out.println("Alright, exposition over.");
        confirm = scan.nextLine();
        System.out.println("As you cut down the blight, it turns into a pile of leaves as it \"dies\".");
        confirm = scan.nextLine();
        System.out.println("And as you venture deeper into the memorial, say for 4 miles or so, the blight reappears in front of you, with a more pronounced magical presence than before.");
        confirm = scan.nextLine();
        System.out.println("However, because the blight hasn't has a good challenge for a couple centuries, they give you a boon!");
        playersWeapon.upgradeWeapon();
        System.out.print("\nPress enter when you're ready to face the blight again.");
        confirm = scan.nextLine();
        blight.setBlightHealth(230);
        currentBlightHealth = blight.getBlightHealth();
        player.reset();
        currentPlayerHealth = 150;
        stage2();
    }

    public void stage2() {
        boolean playersTurn = true;
        boolean alreadyHealed = false;

        while (currentPlayerHealth > 0 && currentBlightHealth > 0) {
            if (playersTurn) {
                previousConditions = new String[currentConditions.length];
                for (int i = 0; i < previousConditions.length; i++) {
                    previousConditions[i] = currentConditions[i];
                }
                System.out.println("\nThe blight challenges you to another bout, now deadlier than the last. (Type either a, pot, ps, bs)");
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
                    if (!alreadyHealed) {
                        System.out.println("Using your healing potion will heal you for 50% of your maximum health, and you can only use it once a fight.");
                        System.out.println("Are you sure you want to use this? (y / n)");

                        String confirmation = scan.nextLine(); // confirmation
                        if (!confirmation.equals("n")) {
                            currentPlayerHealth += 75;
                            System.out.println(Colors.GREEN + "You heal yourself for 75 health!" + Colors.RESET);
                        }
                        if (currentPlayerHealth > 150) {
                            currentPlayerHealth = 150;
                        }
                        alreadyHealed = true;
                    } else {
                        System.out.println("No, you already healed!");
                        playersTurn = !playersTurn;
                    }
                } else if (userChoice.equals("ps")) { // player stats
                    System.out.println(playerStats());
                    playersTurn = !playersTurn;
                } else if (userChoice.equals("bs")) { // blight stats
                    System.out.println(blightStats());
                    playersTurn = !playersTurn;
                } else if (userChoice.equals("/kill blight")) {
                    currentBlightHealth -= 500;
                } else {
                    System.out.println("How's that hand-eye coordination, huh?");
                    playersTurn = !playersTurn;
                }
            } else { // blight's turn
                blightAttack2();
                if (turnCount % 4 == 0) {
                    applyMemorialEffects();
                    turnCount = 0;
                }
                if (leafShieldCount > 0) {
                    leafShieldCount--;

                    if (leafShieldCount == 0) {
                        System.out.println(Colors.YELLOW + "The blight's shield falls!" + Colors.RESET);
                        blight.revertBlightDodge();
                        leafShieldCount = -1;
                    }
                }
                player.updateConditions();
                player.updateConditionArray();
                turnCount++;
            }
            playersTurn = !playersTurn;
            blightHasAttacked = false;
        }
        player.failCase();
        if (currentPlayerHealth <= 0) {
            System.out.println(Colors.RED + "You've succumbed to the druidic attacks, becoming one with the blight and it's homogenous memorial.");
            return;
        }
        if (currentBlightHealth <= 0) {
            if (leafShieldCount > 0) {
                leafShieldCount--;

                if (leafShieldCount == 0) {
                    System.out.println(Colors.YELLOW + "The blight's shield falls!" + Colors.RESET);
                    blight.revertBlightDodge();
                    leafShieldCount = -1;
                }
            }
            System.out.println(Colors.YELLOW + "You've bested the blight!" + Colors.RESET);
        }
        stage3confirm();
    }

    public void stage3confirm() {
        blight.setBlightHealth(300);
        currentBlightHealth = blight.getBlightHealth();
        player.reset();
        currentPlayerHealth = 150;
        System.out.println("Just like last time, the blight turns into another pile of leaves as it falls.");
        String confirm = scan.nextLine();
        System.out.println("As soon as it does, a flower blooms under you that replenishes your health and upgrades your weapon.");
        playersWeapon.upgradeWeapon();
        confirm = scan.nextLine();
        System.out.println("As you trek through the memorial, for another five miles to be exact, you encounter a growing figure made of bark, wood, foliage, moss and leaves.");
        confirm = scan.nextLine();
        System.out.println("This time, you notice that the wildlife and plants around you start to become more hostile, even more so than the first two times.");
        confirm = scan.nextLine();
        System.out.println("Small animals like foxes and rabbits appear and begin to hiss and foam at the mouth, bearing surprisingly sharp fangs...");
        confirm = scan.nextLine();
        System.out.println("...the plants turn an autumn red as the air becomes thick and hard to breathe as it becomes smothered in poisonous smoke and thick pollen.");
        confirm = scan.nextLine();
        System.out.println("This is it.");
        confirm = scan.nextLine();
        System.out.println("This is the blight's final stand against you; to protect everything that it has worked for.");
        confirm = scan.nextLine();
        System.out.print("Let's not waste your time further, are you ready?");
        confirm = scan.nextLine();

        stage3();
    }

    public void stage3() {
        stage = "3";
        boolean playersTurn = true;
        boolean alreadyHealed = false;

        while (currentPlayerHealth > 0 && currentBlightHealth > 0) {
            if (playersTurn) {
                previousConditions = new String[currentConditions.length];
                for (int i = 0; i < previousConditions.length; i++) {
                    previousConditions[i] = currentConditions[i];
                }

                System.out.println("\nYou already know what to do.");
                System.out.println("[ Attack ]");
                System.out.println("[ Drink Health Potion ]");
                System.out.println("[ Check Player Stats ]");
                System.out.println("[ Check Blight / Memorial Stats ]");

                String userChoice = scan.nextLine();

                while (userChoice.equals("")) {
                    System.out.println("You can't be serious...");
                    System.out.println("[ Attack ]");
                    System.out.println("[ Drink Health Potion ]");
                    System.out.println("[ Check Player Stats ]");
                    System.out.println("[ Check Blight / Memorial Stats ]");

                    userChoice = scan.nextLine();
                }

                if (userChoice.equals("a")) { // attack
                    if (player.wheresCondition("Paralysis") > -1) {

                    }
                    String memorialTargets = "";
                    if (liliesAlive && mushroomsAlive) { // if lilies / mushrooms are alive
                        System.out.println("What do you want to target? (Type either \"blight\", \"lilies\", or \"mushrooms\")" + Colors.RED + " MISTYPE = TURN SKIP!" + Colors.RESET);
                        System.out.println("[ Blight ]");
                        System.out.println("[ Lilies ]");
                        System.out.println("[ Mushrooms ]");

                        memorialTargets = scan.nextLine();
                    } else if (liliesAlive && !mushroomsAlive) {
                        System.out.println("What do you want to target? (Type either \"blight\" or \"lilies\")" + Colors.RED + " MISTYPE = TURN SKIP!" + Colors.RESET);
                        System.out.println("[ Blight ]");
                        System.out.println("[ Lilies ]");

                        memorialTargets = scan.nextLine();
                    } else if (!liliesAlive && mushroomsAlive) {
                        System.out.println("What do you want to target? (Type either \"blight\" or \"mushrooms\")" + Colors.RED + " MISTYPE = TURN SKIP!" + Colors.RESET);
                        System.out.println("[ Blight ]");
                        System.out.println("[ Mushrooms ]");

                        memorialTargets = scan.nextLine();
                    } else if (!liliesAlive && !mushroomsAlive) {
                        playersWeapon.makeAttack(blight.getBlightDodge());
                        currentBlightHealth -= playersWeapon.getTotalDamage();
                        if (currentBlightHealth <= 0 && blight.isAntilifeShell()) {
                            System.out.println("The blight's antilife shell protects it from death!");
                            currentBlightHealth = 50;
                        }
                    }

                    if (playersWeapon.isAffectedByParalysis()) {
                        System.out.println("You feel your muscles stiffen as you swing, impeding your attack.");
                    } else {
                        if (memorialTargets.equals("lilies")) {
                            damageLillies(playersWeapon.getTotalDamage());
                        } else if (memorialTargets.equals("mushrooms")) {
                            damageMushrooms(playersWeapon.getTotalDamage());
                        } else if (memorialTargets.equals("blight")) {
                            playersWeapon.makeAttack(blight.getBlightDodge());
                            currentBlightHealth -= playersWeapon.getTotalDamage();
                            LSD();
                            if (currentBlightHealth <= 0 && blight.isAntilifeShell()) {
                                System.out.println("The blight's antilife shell protects it from death!");
                                currentBlightHealth = 50;
                            }
                        }
                    }

                } else if (userChoice.equals("pot")) { // heal
                    if (!alreadyHealed) {
                        System.out.println("Using your healing potion will heal you for 50% of your maximum health, and you can only use it once a fight.");
                        System.out.println("Are you sure you want to use this? (y / n)");

                        String confirmation = scan.nextLine(); // confirmation
                        if (!confirmation.equals("n")) {
                            currentPlayerHealth += 75;
                            System.out.println(Colors.GREEN + "You heal yourself for 75 health!" + Colors.RESET);
                        }
                        if (currentPlayerHealth > 150) {
                            currentPlayerHealth = 150;
                        }
                        alreadyHealed = true;
                    } else {
                        System.out.println("No, you already healed!");
                        playersTurn = !playersTurn;
                    }
                } else if (userChoice.equals("ps")) { // player stats
                    System.out.println(playerStats());
                    playersTurn = !playersTurn;
                } else if (userChoice.equals("bs")) { // blight stats
                    System.out.println(blightStats());
                    playersTurn = !playersTurn;
                } else if (userChoice.equals("/kill blight")) {
                    currentBlightHealth -= 500;
                } else {
                    System.out.println("..?");
                    playersTurn = !playersTurn;
                }
            } else { // blight's turn
                blightAttack3();
                if (turnCount % 3 == 0) {
                    applyMemorialEffects();
                    turnCount = 0;
                }
                if (leafShieldCount > 0) {
                    leafShieldCount--;

                    if (leafShieldCount == 0) {
                        System.out.println(Colors.YELLOW + "The blight's shield falls!" + Colors.RESET);
                        blight.revertBlightDodge();
                        leafShieldCount = -1;
                    }
                }
                blight.updateAntilifeCounter();
                lilyHeal();
                checkSummons();
                player.updateConditions();
                player.updateConditionArray();
                turnCount++;
            }
            playersTurn = !playersTurn;
            blightHasAttacked = false;
            player.failCase();
        }
        if (currentPlayerHealth <= 0) {
            System.out.println(Colors.RED + "You've succumbed to the druidic attacks, becoming one with the blight and it's homogenous memorial.");
            return;
        }
        if (currentBlightHealth <= 0) {
            if (leafShieldCount > 0) {
                leafShieldCount--;

                if (leafShieldCount == 0) {
                    System.out.println(Colors.YELLOW + "The blight's shield falls!" + Colors.RESET);
                    blight.revertBlightDodge();
                    leafShieldCount = -1;
                }
            }
            System.out.println(Colors.YELLOW + "You've bested the blight!" + Colors.RESET);
        }
        // insert ending here
    }
// ----------UTILITY METHODS----------
    public void blightAttack1() {
        int percent = (int) (Math.random() * 100) + 1;
        if (!blightHasAttacked) {
            if (percent <= 20) { // 20% to cast call lighting
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

    public void blightAttack3() {
        int percent = (int) (Math.random() * 100) + 1;
        if (!blightHasAttacked) {
            if (percent <= 10) { // 10% eq or als
                int a = (int) (Math.random() * 2) + 7;

                blight.castspell3(a, playersWeapon.getDodge());
                currentPlayerHealth -= blight.getSpellDamage();

            } else if (percent <= 30) { // 20% cl or cw
                if ((int) (Math.random() * 99) + 1 <= 50) { // cl
                    blight.castSpell1(3, playersWeapon.getDodge());
                } else { // cw
                    blight.castspell3(5, playersWeapon.getDodge());
                    currentBlightHealth += 30;
                    if (currentBlightHealth > 300) {
                        currentBlightHealth = 300;
                    }
                }
            } else if (percent <= 50) { // 30% chance to cast orginal 3
                int a = (int) (Math.random() * 3);

                blight.castSpell1(a, playersWeapon.getDodge());
                currentPlayerHealth -= blight.getSpellDamage();
            } else { // 50% chance to cast mb or ss
                if ((int) (Math.random() * 99) + 1 <= 50) { // mb
                    blight.castspell3(6, playersWeapon.getDodge());
                } else { // ss
                    blight.castspell3(4, playersWeapon.getDodge());
                }
            }
        }
    }
    public void applyMemorialEffects() {
        if (stage.equals("1") || stage.equals("2")) {
            int fx = (int) (Math.random() * 1);

            if (fx == 0) { // leaf shield
                System.out.println("You look around as moss and foliage wrap around the blight, increasing it's defences!");
                blight.setBlightDodge(25);
                leafShieldCount = 4;
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
        } else { // stage 3
            int twice = 0;
            String repeat = "";
            boolean rollAgain = false;

            while (twice != 2) {
                int fx = (int) (Math.random() * 3);

                if (fx == 0) { // leaf shield BUT BETTER
                    if (!repeat.equals("leaf")) {
                        System.out.println("You look around as moss and foliage wrap around the blight, increasing it's defences!");
                        blight.setBlightDodge(30);
                        leafShieldCount = 6;
                        repeat = "leaf";
                    } else {
                        fx = 1;
                    }
                } else if (fx == 1) { // spore swarm + blindness
                    int b = (int) (Math.random() * 3) + 1;

                    if (b == 1) {
                        if (!repeat.equals("weakness")) {
                            repeat = "weakness";
                            System.out.println("You notice some mushrooms surround you and release harmful spores, as you now have the Weakness condition!");
                            player.setCondition("Weakness");
                        } else {
                            b = 2;
                        }
                    } else if (b == 2) {
                        if (!repeat.equals("paralysis")) {
                            repeat = "paralysis";
                            System.out.println("You notice some mushrooms surround you and release harmful spores, as you now have the Paralysis condition!");
                            player.setCondition("Paralysis");
                        } else {
                            b = 3;
                        }
                    } else {
                        if (!repeat.equals("blindness")) {
                            repeat = "blindness";
                            System.out.println("You notice some mushrooms surround you and release harmful spores, as you now have the Blindness condition!");
                            player.setCondition("Blindness");
                        } else {
                            repeat = "weakness";
                            System.out.println("You notice some mushrooms surround you and release harmful spores, as you now have the Weakness condition!");
                            player.setCondition("Weakness");
                        }
                    }

                } else if (fx == 2) { // summon lilies / mushrooms
                    if ((int) (Math.random() * 2) == 0) {
                        summonLillies();
                    } else {
                        summonMushrooms();
                    }
                }
                if (rollAgain) {
                    twice--;
                    rollAgain = false;
                } else {
                    twice++;
                }
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
        String e = "";
        if ((stage.equals("1") && currentBlightHealth > 120) || (stage.equals("2") && currentBlightHealth > 150) || (stage.equals("3") && currentBlightHealth > 200)) {
            e = "Blight status: Healthy";
        } else if ((stage.equals("1") && currentBlightHealth > 60) || (stage.equals("2") && currentBlightHealth > 80) || (stage.equals("3") && currentBlightHealth > 100)) {
            e = "Blight status: Faltering";
        } else if ((stage.equals("1") && currentBlightHealth < 60) || (stage.equals("2") && currentBlightHealth < 80) || (stage.equals("3") && currentBlightHealth < 100)) {
            e = "Blight status: Weak";
        }

        if (leafShieldCount > 0) {
            e += Colors.GREEN + " + Shield active!" + Colors.RESET;
        }

        if (stage.equals("1") || stage.equals("2")) {
            e += "\nMemorial Status: Harmful plants begin to surround you with the Blight's presence.";
        } else {
            e += "\nMemorial status: The forest rages against you, with every step you make on its soil a threat to your life.";
            e += Colors.RED + "\nThe memorial now attacks twice!" + Colors.RESET;
        }

        return e;
    }
    // stuff for memorial summons spefically
    // lilies
    public void summonLillies() {
        lilyCount += 5;
        liliesAlive = true;
        System.out.println(Colors.CYAN + "5 lilies appear around the blight!" + Colors.RESET);
    }

    public void damageLillies(int damage) {
        while (lilyCount > 0 && damage >= 5) {
            damage -= 5;
            System.out.println("You kill a lily!");
            lilyCount -= 1;
        }
    }

    public void lilyHeal() {
        if (lilyCount > 0) {
            currentBlightHealth += (int) (lilyCount * .9);
            System.out.println(Colors.GREEN + "The lilies on the battlefield heal the blight for " + (int) (lilyCount * .7) + " health!" + Colors.RESET);
        } else {
            return;
        }
    }

    // shrooms
    public void summonMushrooms() {
        mushroomCount += 2;
        mushroomsAlive = true;
        System.out.println(Colors.PURPLE + "2 mushrooms appear around the blight!" + Colors.RESET);
    }

    public void damageMushrooms(int damage) {
        while (mushroomCount > 0 && damage >= 10) {
            damage -= 10;
            System.out.println("You kill a mushroom!");
            mushroomCount -= 1;
        }
    }

    public void LSD() {
        if (mushroomsAlive && !playersWeapon.isDeflected()) {
            if (playersWeapon.getTotalDamage() < mushroomCount * 3 && playersWeapon.getTotalDamage() > 0) {
                System.out.println(Colors.PURPLE + "The mushrooms shield the blight from " + playersWeapon.getTotalDamage() + " damage!" + Colors.RESET);
                currentBlightHealth += playersWeapon.getTotalDamage();
            } else {
                System.out.println(Colors.PURPLE + "The mushrooms shield the blight from " + mushroomCount * 3 + " damage! " + Colors.RESET);
                currentBlightHealth += mushroomCount * 3;
            }
        } else {
            return;
        }
    }

    public void checkSummons() {
        if (lilyCount <= 0) {
            liliesAlive = false;
            lilyCount = 0;
        }

        if (mushroomCount <= 0) {
            mushroomsAlive = false;
            mushroomCount = 0;
        }
    }
}