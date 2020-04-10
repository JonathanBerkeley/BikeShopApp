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
                    + "6. Check existance of product by ID\n"
                    + "7. Check existance of store by ID\n"
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
                case 0:
                    break;
                case 1:
                    //Starts form for creating bicycle object
                    createObject(in, 1);
                    break;
                case 2:
                    break;
                case 3:
                    //Starts form for reading bicycle object from database by ID
                    readBicycleObject(in);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println(
                            (checkExistForm(in, 2)) ? "Product exists" : "Product doesn't exist"
                    );
                    break;
                case 7:
                    System.out.println(
                            (checkExistForm(in, 1)) ? "Store exists" : "Store doesn't exist"
                    );
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
            //Loop creation form until user stops it
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
                    if (!(Model.getInstance().checkStoreID(strID))) {
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
                        //displayAll is a polymorphic example, changes behaviour depending on object
                        System.out.println(bo.displayAll());
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

    //This function is for retrieving and displaying bicycle object data from database
    public static void readBicycleObject(Scanner in) {
        String readLoop = "";
        int readID;
        while (!(readLoop.equalsIgnoreCase("n"))) {
            try {
                System.out.println("Enter ID of bicycle object to view data for");
                readID = in.nextInt();
                in.nextLine(); //Absorbs newline character
                Bicycle bo = Model.getInstance().readBicycleObj(readID);
                //Checks for existance first, then outputs the bicycle data
                if (bo == null) {
                    System.out.println("Supplied ID was not found in database");
                } else {
                    System.out.println(bo);
                }
                //Check if user wants to go again, any input other than n with continue
                System.out.println("Completed form - Go again?\nY/y = YES\nN/n = NO");
                readLoop = in.nextLine();
            } catch (Exception badIn) {
                System.out.println("Bad input! Please enter correct datatypes");
                if (Meta.debug) {
                    System.out.println("--DEBUG-- Bad input in readObject function, except: " + badIn);
                }
                break;
            }

        }
    }

    //Returns true if ID exists in database, false if not
    public static boolean checkExistForm(Scanner in, int stream) {
        try {
            //Streams function into two channels to avoid needing to rewrite more code
            if (stream == 1) {
                System.out.println("Enter ID to check if a store exists with that ID:");
                return Model.getInstance().checkStoreID(in.nextInt());
            } else if (stream == 2) {
                System.out.println("Enter ID to check if a product exists with that ID:");
                return Model.getInstance().checkProductID(in.nextInt());
            }
        } catch (Exception ex) {
            if (Meta.debug) {
                System.out.println("-Debug- Exception in function checkExistForm " + ex);
            }
            System.out.println("Bad input! Ensure datatype correct and retry.");
        }
        return false;
    }

}
