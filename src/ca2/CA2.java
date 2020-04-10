package ca2;

/**
 *
 * @author N00181859
 */

import java.util.Scanner;

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
            System.out.print("\n1. Create bicycle\n"
                    + "2. Create bicycle accessory\n"
                    + "3. Read stored bicycle object\n"
                    + "4. Read stored bicycle accessory object\n"
                    + "5. Read product(s) by store ID\n"
                    + "6. Check existance of product by ID\n"
                    + "7. Check existance of store by ID\n"
                    + "8. Create store\n"
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
                    createObject(in, 2);
                    break;
                case 3:
                    //Starts form for reading bicycle object from database by ID
                    readObject(in, 1);
                    break;
                case 4:
                    //Starts form for reading bicycle accessories object from database by ID
                    readObject(in, 2);
                    break;
                case 5:
                    productsByStoreID(in);
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
                case 8:
                    createObject(in, 3);
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
        /* This used to work but I changed things and haven't updated it yet
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
                            System.out.println("-DEBUG- Failed to add to DB in createObject function, except: " + fta);
                        }
                    }
                    System.out.println("Completed form - Add another?\nY/y = YES\nN/n = NO");
                    creationLoop = in.nextLine();
                } catch (Exception badIn) {
                    System.out.println("Bad input to form, please enter in correct datatypes");
                    if (Meta.debug) {
                        System.out.println("-DEBUG- Bad input in createObject function, except: " + badIn);
                    }
                    break;
                }

            }
        } else if (stream == 2) { //For bicycle accessory
            //Controls menu loop
            String creationLoop = "";
            //Variables for bicycle accessory object
            BicycleAccessories ba;
            int strID;
            double price;
            String colour, productName, baType;
            boolean inStock;
            //Loop creation form until user stops it
            while (!(creationLoop.equalsIgnoreCase("n"))) {
                try {
                    System.out.println("Enter store ID");
                    strID = in.nextInt();
                    in.nextLine(); //Catch newline
                    //Checks if entered store ID exists
                    if (!(Model.getInstance().checkStoreID(strID))) {
                        //If entered store ID doesn't exist:
                        System.out.println("Entered store ID doesn't exist - aborting");
                        break;
                    }
                    System.out.println("Enter product name");
                    productName = in.nextLine();
                    System.out.println("Enter colour");
                    colour = in.nextLine();
                    System.out.println("Enter price");
                    price = in.nextDouble();
                    in.nextLine(); //Catches newline
                    System.out.println("Enter bicycle accessory type");
                    baType = in.nextLine();
                    System.out.println("Is " + productName + " in stock? \n(Y/y = YES N/n = NO)");
                    String tempStockChk = in.nextLine();
                    if (tempStockChk.equalsIgnoreCase("Y")) {
                        inStock = true;
                    } else if (tempStockChk.equalsIgnoreCase("N")) {
                        inStock = false;
                    } else {
                        throw new Exception("Bad input");
                    }
                    //Create bicycle accessory object with entered data
                    ba = new BicycleAccessories(price, colour, productName, baType, inStock, strID);
                    try {
                        //displayAll is a polymorphic example, changes behaviour depending on object
                        System.out.println(ba.displayAll());
                        //Send bicycleaccessories object to model for entry into the database
                        Model.getInstance().addBicycleAccessory(ba);
                    } catch (Exception fta) {
                        if (Meta.debug) {
                            System.out.println("-DEBUG- Failed to add to DB in createObject function, except: " + fta);
                        }
                    }
                    System.out.println("Completed form - Add another?\nY/y = YES\nN/n = NO");
                    creationLoop = in.nextLine();
                } catch (Exception badIn) {
                    System.out.println("Bad input to form, please enter in correct datatypes");
                    if (Meta.debug) {
                        System.out.println("-DEBUG- Bad input in createObject function, except: " + badIn);
                    }
                    break;
                }
            }
        } else if (stream == 3) { //For store creation
            in.nextLine(); //Catches newline
            //Controls menu loop
            String creationLoop = "";
            Store str;
            String storeName, storeAddress;
            //Loop creation form until user stops it
            while (!(creationLoop.equalsIgnoreCase("n"))) {
                try {

                    System.out.println("Enter store name: ");
                    storeName = in.nextLine();
                    System.out.println("Enter store address: ");
                    storeAddress = in.nextLine();

                    //Create store object with entered data
                    str = new Store(storeName, storeAddress);
                    try {
                        //displayAll is a polymorphic example, changes behaviour depending on object
                        System.out.println(str.displayAll());
                        //Send store object to model for entry into the database
                        //Return value from model is never used, checking for success
                        //is instead done in MainTableGateway
                        Model.getInstance().addStore(str);
                    } catch (Exception fta) {
                        if (Meta.debug) {
                            System.out.println("-DEBUG- Failed to add to DB in createObject function, except: " + fta);
                        }
                    }
                    System.out.println("Completed form - Add another?\nY/y = YES\nN/n = NO");
                    creationLoop = in.nextLine();
                } catch (Exception badIn) {
                    System.out.println("Bad input to form, please enter in correct datatypes");
                    if (Meta.debug) {
                        System.out.println("-DEBUG- Bad input in createObject function, except: " + badIn);
                    }
                    break;
                }
            }
        }
    }

    //This function is for retrieving and displaying bicycle object data from database
    public static void readObject(Scanner in, int stream) {
        String readLoop = "";
        int readID;
        if (stream == 1) {
            while (!(readLoop.equalsIgnoreCase("n"))) {
                try {
                    System.out.println("Enter ID to view bicycle data for");
                    readID = in.nextInt();
                    in.nextLine(); //Absorbs newline character
                    Bicycle bo = Model.getInstance().readBicycleObj(readID);
                    //Checks for existance first, then outputs the bicycle data
                    if (bo == null) {
                        System.out.println("Supplied ID was not found in database");
                    } else {
                        //Uses toString which uses displayAll function
                        System.out.println(bo);
                    }
                    //Check if user wants to go again, any input other than n with continue
                    System.out.println("Completed form - Go again?\nY/y = YES\nN/n = NO");
                    readLoop = in.nextLine();
                } catch (Exception badIn) {
                    System.out.println("Bad input! Please enter correct datatypes");
                    if (Meta.debug) {
                        System.out.println("-DEBUG- Bad input in readObject function, except: " + badIn);
                    }
                    break;
                }
            }
        } else if (stream == 2) {
            while (!(readLoop.equalsIgnoreCase("n"))) {
                try {
                    System.out.println("Enter ID to view bicycle accessories data for");
                    readID = in.nextInt();
                    in.nextLine(); //Absorbs newline character
                    BicycleAccessories ba = Model.getInstance().readBAObj(readID);
                    //Checks for existance first, then outputs the bicycle data
                    if (ba == null) {
                        System.out.println("Supplied ID was not found in database");
                    } else {
                        //Uses toString which uses displayAll function
                        System.out.println(ba);
                    }
                    //Check if user wants to go again, any input other than n with continue
                    System.out.println("Completed form - Go again?\nY/y = YES\nN/n = NO");
                    readLoop = in.nextLine();
                } catch (Exception badIn) {
                    System.out.println("Bad input! Please enter correct datatypes");
                    if (Meta.debug) {
                        System.out.println("-DEBUG- Bad input in readObject function, except: " + badIn);
                    }
                    break;
                }
            }
        }
    }

    //Gets the products at store by ID
    public static void productsByStoreID(Scanner in) {
        int dID;
        Store storeObj;
        try {
            System.out.println("Enter store ID to display products of:");
            dID = in.nextInt();
            in.nextLine(); //Catches newline character

            //Checks if that id is a valid store
            if (Model.getInstance().checkStoreID(dID)) {
                storeObj = Model.getInstance().getStoresProducts(dID);
                System.out.println(storeObj.displayAll());
            } else {
                System.out.println("Couldn't find a store with that ID. Aborting");
            }
        } catch (Exception badIn) {
            System.out.println("Bad input! Please enter correct datatypes");
            if (Meta.debug) {
                System.out.println("-DEBUG- Exception in productsByStoreID function: " + badIn);
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
                System.out.println("-DEBUG- Exception in function checkExistForm " + ex);
            }
            System.out.println("Bad input! Ensure datatype correct and retry.");
        }
        return false;
    }

}
