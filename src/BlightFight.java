import java.util.Scanner;
public class BlightFight {
    Player player = null;
    Blight blight = new Blight();
    private int currentPlayerHealth = 0;
    private int currentBlightHealth = blight.getBlightHealth();
    private Weapon playersWeapon = null;
    private Scanner scan;
    public BlightFight() {}

    public void start() { // name and weapon choice
        String choice = "";
        scan = new Scanner(System.in);

        System.out.print("What's your name? ");
        String name = scan.nextLine();
        player = new Player(name);
        currentPlayerHealth = player.getHealth();

        while (!choice.equals("sword") && !choice.equals("chakrams") && !choice.equals("scythe")) {
            System.out.println("Select your weapon out of these three: (type \"sword\", \"chakrams\", or \"scythe\")");
            System.out.println("-----LONGSWORD------");
            System.out.println("Minimum damage: 30");
            System.out.println("Maximum damage: 40");
            System.out.println("Number of attacks: 2");
            System.out.println("Dodge chance: 30%");
            System.out.println();
            System.out.println("-----DUAL CHAKRAMS------");
            System.out.println("Minimum damage: 10");
            System.out.println("Maximum damage: 20");
            System.out.println("Number of attacks: 3");
            System.out.println("Dodge chance: 60%");
            System.out.println();
            System.out.println("-----GREAT SCYTHE-----");
            System.out.println("Minium damage: 75");
            System.out.println("Minimum damage: 100");
            System.out.println("Number of attacks: 1");
            System.out.println("Dodge chance: 10%");

            choice = scan.nextLine();
        }

        if (choice.equals("sword")) {
            playersWeapon = new Weapon("Longsword", 30, 40, 2, 30);
        } else if (choice.equals("chakrams")) {
            playersWeapon = new Weapon("Dual chakrams",10,20, 3,60);
        } else if (choice.equals("scythe")) {
            playersWeapon = new Weapon("Great scythe", 75, 100, 1, 10);
        }
        stage1();
    }

    public void stage1() {
        String action = "";
        boolean playersTurn = true;
        while (currentPlayerHealth > 0 && currentBlightHealth > 0) {
            if (playersTurn) {
                System.out.println("What would you like to do?\n");
                System.out.println("[ Attack ]");
                System.out.println("[ WIP ]");
                action = scan.nextLine();

                if (action.equals("attack")) {
                    playersWeapon.makeAttack(blight.getBlightDodge());
                } else {
                    // insert healing code here
                }
            } else {
                // insert code for blight's turn here
            }
        }
        stage2();
    }

    public void stage2() {}

    public void stage3() {}
}
