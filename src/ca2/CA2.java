package ca2;

/**
 *
 * @author N00181859
 */
import utils.DatabasePopulator;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import utils.Rangedrandom;

public class CA2 {

    public static void main(String[] args) {
        //Scanner object for taking input from user
        Scanner in = new Scanner(System.in);
        //Tests for connection to database before continuing
        Model.getInstance();
        int userChoice = -1;
        //Main program i/o loop
        for (int menuControl = 0; menuControl != -1;) {
            //MAIN MENU:
            System.out.print("\n1. Create bicycle object\n"
                    + "2. Create bicycle accessory object\n"
                    + "3. Read stored bicycle object\n"
                    + "4. Read stored bicycle accessory object\n"
                    + "5. Read product by store ID\n"
                    + "15. Toggle debug mode\n"
                    + "\n0. Exit\n"
                    + "Enter selection: ");
            try {
                userChoice = in.nextInt();
            } catch (Exception e1) {
                in.nextLine(); //Prevents infinite menu looping on exception
            }
            //Switch statement checks user choice against different valid entries
            switch (userChoice) {
                case 1:
                    //Starts form for creating bicycle object
                    createObject(in, 1);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 15:
                    Meta.toggleDebug();
                    System.out.println("Debug " + ((Meta.getDebugValue()) ? "on" : "off"));
                    break;
                default:
                    System.out.println("Please enter a listed number.");
                    break;
            }
            //Exit check
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

    } //End of main

    //Forms for creating objects issued to user by this function
    public static void createObject(Scanner in, int stream) {
        //Stream variable directs which object caller wants to create
        if (stream == 1) { //For bicycle
            //Controls menu loop
            String creationLoop = "";
            //Variables for bicycle object
            Bicycle bo;
            int gearCount, modelNo, strID;
            double price, weight;
            String brand, colour, productName;
            while (!(creationLoop.equalsIgnoreCase("n"))) {
                try {
                    System.out.println("Enter gear count");
                    gearCount = in.nextInt();
                    in.nextLine(); //Catch newline
                    System.out.println("Enter model number");
                    modelNo = in.nextInt();
                    in.nextLine(); //Catch newline
                    System.out.println("Enter store ID");
                    strID = in.nextInt();
                    in.nextLine(); //Catch newline
                    //Checks if entered store ID exists
                    if(!(Model.getInstance().checkStoreID(strID))){
                        //If entered store ID doesn't exist:
                        System.out.println("Entered store ID doesn't exist - aborting");
                        break;
                    }
                    System.out.println("Enter price");
                    price = in.nextDouble();
                    System.out.println("Enter weight");
                    weight = in.nextDouble();
                    in.nextLine(); //Catch newline
                    System.out.println("Enter brand");
                    brand = in.nextLine();
                    System.out.println("Enter colour");
                    colour = in.nextLine();
                    System.out.println("Enter product name");
                    productName = in.nextLine();
                    //Create bicycle object with entered data
                    bo = new Bicycle(price, colour, productName, gearCount, modelNo, weight, brand, strID);
                    try {
                        System.out.println(bo);
                        //Send bicycle object to model for entry into the database
                        Model.getInstance().addBicycle(bo);
                    } catch (Exception fta) {
                        if (Meta.debug) {
                            System.out.println("--DEBUG-- Failed to add to DB in createObject function, except: " + fta);
                        }
                    }
                    System.out.println("Completed form - Add another?\nY/y = YES\nN/n = NO");
                    creationLoop = in.nextLine();
                } catch (Exception badIn) {
                    System.out.println("Bad input to form, please enter in correct datatypes");
                    if (Meta.debug) {
                        System.out.println("--DEBUG-- Bad input in createObject function, except: " + badIn);
                    }
                    break;
                }

            }
        } else if (stream == 2) { //For bicycle accessory

        }
    }

}
