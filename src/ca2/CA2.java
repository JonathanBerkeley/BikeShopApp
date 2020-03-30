package ca2;

/**
 *
 * @author N00181859
 */
import utils.DatabasePopulator;
import java.util.Scanner;

public class CA2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int userChoice = -1;
        for (int menuControl = 0;;) {
            //MAIN MENU:
            System.out.print("\n1. Create bicycle object\n"
                    + "2. Create bicycle accessory object\n"
                    + "3. Read stored bicycle object\n"
                    + "4. Read stored bicycle accessory object\n"
                    + "15. Toggle debug mode\n"
                    + "\n0. Exit\n"
                    + "Enter selection: ");
            try {
                userChoice = in.nextInt();
            } catch (Exception e1) {
                in.nextLine(); //Prevents infinite menu looping on exception
            }
            
            switch (userChoice) {
                case 1:
                    break;
                case 2:
                    break;
                case 15:
                    Meta.toggleDebug();
                    System.out.println("Debug " + ((Meta.getDebugValue()) ? "on":"off"));
                    break;
                default:
                    System.out.println("Please enter a listed number.");
                    break;
            }
            if (userChoice == 0) {
                System.out.println("\n----Exiting.----\n");
                break;
            }
        }
        /*
        DatabasePopulator dbPop = new DatabasePopulator();
        dbPop.printBA(10);
        dbPop.printBikes(20); 
        */
    }
}
