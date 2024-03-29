package ca2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainTableGateway {

    //Product table:
    private static final String TABLE_PRODUCT = "product";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_COLOUR = "colour";
    private static final String COLUMN_PRODUCT_NAME = "productName";
    private static final String COLUMN_P_STORE_ID = "storeId";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_GEAR_COUNT = "gearCount";
    private static final String COLUMN_MODEL_NO = "modelNo";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_BRAND = "brand";
    private static final String COLUMN_BA_TYPE = "baType";
    private static final String COLUMN_IN_STOCK = "inStock";

    //Store table:
    private static final String TABLE_STORE = "store";
    private static final String COLUMN_STORE_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_STORE_ADDRESS = "storeAddress";
    private static final String COLUMN_PRODUCT_LIST = "productList";
    private final Connection mConnection;

    public MainTableGateway(Connection connection) {
        mConnection = connection;
        if (mConnection == null) {
            if (Meta.debug) {
                System.out.println("-Debug- Failure to establish connection in MainTableGateway.");
            }
            System.exit(1); //Exit with failure code
        }
    }

    public Bicycle insertBicycle(int gc, int mn, double wt, String br, String co, double pr, String pn, int strId) throws SQLException, SQLIntegrityConstraintViolationException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id;
        Bicycle b = null;
        //Preparing statment
        query = "INSERT INTO " + TABLE_PRODUCT + " ("
                + COLUMN_GEAR_COUNT + ", "
                + COLUMN_MODEL_NO + ", "
                + COLUMN_WEIGHT + ", "
                + COLUMN_BRAND + ", "
                + COLUMN_COLOUR + ", "
                + COLUMN_TYPE + ", "
                + COLUMN_PRICE + ", "
                + COLUMN_PRODUCT_NAME + ", "
                + COLUMN_P_STORE_ID
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"; //SQL query built as a string
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        //Values are inserted at "?" positions
        stmt.setInt(1, gc);
        stmt.setInt(2, mn);
        stmt.setDouble(3, wt);
        stmt.setString(4, br);
        stmt.setString(5, co);
        //Sets the enum "type" column in the product table to bicycle
        stmt.setString(6, "bicycle");
        stmt.setDouble(7, pr);
        stmt.setString(8, pn);
        stmt.setInt(9, strId);

        //Executes the prepared statement, this function returns the amount of rows affected
        numRowsAffected = stmt.executeUpdate();

        if (numRowsAffected == 1) {
            System.out.println("Insertion successful.");
            //Gets generated ID from the result set data from the database,
            //which was auto incremented by the database
            ResultSet keys = stmt.getGeneratedKeys();
            //Moves result set virtual cursor forward one row
            keys.next();
            //Gets id from result set
            id = keys.getInt(1);
            //Create bicycle object with the ID that was found in result set data
            b = new Bicycle(id, pr, co, pn, gc, mn, wt, br, strId);
        } else {
            System.out.println("Insertion unsuccessful.");
            if (Meta.debug) {
                System.out.println("-Debug- Number of rows affected by failed bicycle insertion attempt: " + numRowsAffected);
            }
        }
        return b; //Returns the bicycle object to caller
    }

    public BicycleAccessories insertBicycleAccessory(double pr, String co, String pn, String bt, boolean is, int strId) throws SQLException, SQLIntegrityConstraintViolationException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id;
        BicycleAccessories ba = null;
        //Preparing statment
        query = "INSERT INTO " + TABLE_PRODUCT + " ("
                + COLUMN_PRICE + ", "
                + COLUMN_COLOUR + ", "
                + COLUMN_PRODUCT_NAME + ", "
                + COLUMN_BA_TYPE + ", "
                + COLUMN_IN_STOCK + ", "
                + COLUMN_TYPE + ", "
                + COLUMN_P_STORE_ID
                + ") VALUES (?, ?, ?, ?, ?, ?, ?)"; //SQL query built as a string
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        //Values are inserted at "?" positions
        stmt.setDouble(1, pr);
        stmt.setString(2, co);
        stmt.setString(3, pn);
        stmt.setString(4, bt);
        stmt.setBoolean(5, is);
        //Sets the enum "type" column in the product table to bicycle accessory
        stmt.setString(6, "bicycle accessory");
        stmt.setInt(7, strId);

        //Executes the prepared statement, this function returns the amount of rows affected
        numRowsAffected = stmt.executeUpdate();

        if (numRowsAffected == 1) {
            System.out.println("Insertion successful.");
            //Gets generated ID from the result set data from the database,
            //which was auto incremented by the database
            ResultSet keys = stmt.getGeneratedKeys();
            //Moves result set virtual cursor forward one row
            keys.next();
            //Gets id from result set
            id = keys.getInt(1);
            //Create bicycle accessory object with the ID that was found in result set data
            ba = new BicycleAccessories(id, pr, co, pn, bt, is, strId);
        } else {
            System.out.println("Insertion unsuccessful.");
            if (Meta.debug) {
                System.out.println("-Debug- Number of rows affected by failed bicycle accessory insertion attempt: " + numRowsAffected);
            }
        }
        return ba; //Returns the bicycle accessory object to caller
    }

    //Inserts new store into database
    public Store insertStore(String sn, String sa) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected;
        int id;
        Store str = null;
        //Preparing statment
        query = "INSERT INTO " + TABLE_STORE + " ("
                + COLUMN_NAME + ", "
                + COLUMN_STORE_ADDRESS
                + ") VALUES (?, ?)"; //SQL query built as a string
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        //Values are inserted at "?" positions
        stmt.setString(1, sn);
        stmt.setString(2, sa);

        //Executes the prepared statement, this function returns the amount of rows affected
        numRowsAffected = stmt.executeUpdate();

        if (numRowsAffected == 1) {
            System.out.println("Insertion successful.");
            //Gets generated ID from the result set data from the database,
            //which was auto incremented by the database
            ResultSet keys = stmt.getGeneratedKeys();
            //Moves result set virtual cursor forward one row
            keys.next();
            //Gets id from result set
            id = keys.getInt(1);
            //Create store object with the ID that was found in result set data
            str = new Store(id, sn, sa);
        } else {
            System.out.println("Insertion unsuccessful.");
            if (Meta.debug) {
                System.out.println("-Debug- Number of rows affected by failed store insertion attempt: " + numRowsAffected);
            }
        }
        return str;
    }

    //Reads bicycle object data with given ID from database if it exists.
    //Returns null bicycle object if it doesn't
    public Bicycle readBicycle(int bID) throws SQLException {
        Bicycle bo = null;
        String query = "SELECT * FROM " + TABLE_PRODUCT
                + " WHERE " + COLUMN_PRODUCT_ID
                + " = " + bID;
        PreparedStatement stmt = mConnection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        List<Bicycle> bl = formatResultSet(rs);
        return bl.get(0);
    }
    
    //Same as above, but for reading bicycle accessories from database
    public BicycleAccessories readBicycleAccessory(int bID) throws SQLException {
        BicycleAccessories ba = null;
        String query = "SELECT * FROM " + TABLE_PRODUCT
                + " WHERE " + COLUMN_PRODUCT_ID
                + " = " + bID;
        PreparedStatement stmt = mConnection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        List<BicycleAccessories> bl = formatBAResultSet(rs);
        return bl.get(0);
    }

    //Function for getting all products of a store by ID
    public Store allStoreProducts(int dID) throws SQLException {
        List<Product> productList;

        //Query to get the referenced store
        String query = "SELECT * FROM " + TABLE_STORE
                + " WHERE " + COLUMN_STORE_ID
                + " = " + dID;
        Statement stmt = mConnection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery(query);

        //Unpacks result set data into store object
        Store str = formatStoreResultSet(rs);

        //Query to get that stores products
        String query2 = "SELECT * FROM " + TABLE_PRODUCT
                + " WHERE " + COLUMN_P_STORE_ID
                + " = " + dID;
        Statement stmt2 = mConnection.prepareStatement(query2);
        ResultSet rs2 = stmt2.executeQuery(query2);

        //Unpacks the result set data into objects, then adds them to an arraylist
        productList = formatProductResultSet(rs2);

        //Sets product list to that store
        str.setProductList(productList);

        //Returns store to caller
        return str;
    }

    //Function to check if store exists in database
    public boolean checkStoreExist(int sID) {
        PreparedStatement stmt;
        String query;
        ResultSet rs;
        //Basic query, it's irrelevant what the query is, it just uses the supplied ID
        //If the ID doesn't exist, there will be no rows in the result set
        //If the ID does exist, there will be a row in the result set
        query = "SELECT " + COLUMN_STORE_ID + " FROM "
                + TABLE_STORE + " WHERE " + COLUMN_STORE_ID
                + " = " + sID;
        try {
            stmt = mConnection.prepareStatement(query);
            rs = stmt.executeQuery();
            //Move virtual cursor to first row in result set, will fail if ID doesn't exist
            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException sqle) {
            if (Meta.debug) {
                System.out.println("-Debug- Acceptable exception in function checkStoreExist " + sqle);
            }
        }
        return false;
    }

    //Function to check if product exists in database, very similar to store check function
    public boolean checkProductExist(int pID) {
        PreparedStatement stmt;
        String query;
        ResultSet rs;
        //Basic query, it's irrelevant what the query is, it just uses the supplied ID
        //If the ID doesn't exist, there will be no rows in the result set
        //If the ID does exist, there will be a row in the result set
        query = "SELECT " + COLUMN_PRODUCT_ID + " FROM "
                + TABLE_PRODUCT + " WHERE " + COLUMN_PRODUCT_ID
                + " = " + pID;
        try {
            stmt = mConnection.prepareStatement(query);
            rs = stmt.executeQuery();
            //Move virtual cursor to first row in result set, will fail if ID doesn't exist
            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException sqle) {
            if (Meta.debug) {
                System.out.println("-Debug- Acceptable exception in function checkProductExist " + sqle);
            }
        }
        return false;
    }

    //Private functions ::
    //Formats result set data into java objects, code based on my previous C.A code.
    private List<Bicycle> formatResultSet(ResultSet rs) throws SQLException { //Based on sample code, parses resultset data
        List<Bicycle> bicycleList = new ArrayList<>();
        int id, modelNo, gearCount, storeID;
        double weight, price;
        String productName, brand, colour, type;
        Bicycle bo;
        try {
            while (rs.next()) { //Parses resultset data and creates objects on a loop, until resultset is out of rows
                id = rs.getInt(COLUMN_PRODUCT_ID);
                price = rs.getDouble(COLUMN_PRICE);
                colour = rs.getString(COLUMN_COLOUR);
                productName = rs.getString(COLUMN_PRODUCT_NAME);
                storeID = rs.getInt(COLUMN_P_STORE_ID);
                type = rs.getString(COLUMN_TYPE);
                //Checks to make sure id matches the correct object type it's being formatted for
                if (type.equals("bicycle accessory")){
                    throw new Exception("Wrong product type");
                }
                gearCount = rs.getInt(COLUMN_GEAR_COUNT);
                modelNo = rs.getInt(COLUMN_MODEL_NO);
                weight = rs.getDouble(COLUMN_WEIGHT);
                brand = rs.getString(COLUMN_BRAND);
                bo = new Bicycle(id, price, colour, productName, gearCount, modelNo, weight, brand, storeID);
                bicycleList.add(bo);
            }
            return bicycleList; //Return all the objects created
        } catch (Exception ex) {
            if (Meta.debug) {
                System.out.println("-Debug- Exception caught in MainTableGateway.formatResultSet: " + ex);
            }
            System.out.println("Wrong product type!");
        }
        return null;
    }
    
    //Same as above but for bicycle accessory
    private List<BicycleAccessories> formatBAResultSet(ResultSet rs) throws SQLException { //Based on sample code, parses resultset data
        List<BicycleAccessories> baList = new ArrayList<>();
        int id, storeID;
        double  price;
        String productName, colour, type, baType;
        boolean inStock;
        BicycleAccessories ba;
        try {
            while (rs.next()) { //Parses resultset data and creates objects on a loop, until resultset is out of rows
                id = rs.getInt(COLUMN_PRODUCT_ID);
                price = rs.getDouble(COLUMN_PRICE);
                colour = rs.getString(COLUMN_COLOUR);
                productName = rs.getString(COLUMN_PRODUCT_NAME);
                storeID = rs.getInt(COLUMN_P_STORE_ID);
                type = rs.getString(COLUMN_TYPE);
                //Checks to make sure id matches the correct object type it's being formatted for
                if (type.equals("bicycle")){
                    throw new Exception("Wrong product type");
                }
                baType = rs.getString(COLUMN_BA_TYPE);
                inStock = rs.getBoolean(COLUMN_IN_STOCK);
                ba = new BicycleAccessories(id, price, colour, productName, baType, inStock, storeID);
                baList.add(ba);
            }
            return baList; //Return all the objects created
        } catch (Exception ex) {
            if (Meta.debug) {
                System.out.println("-Debug- Exception caught in MainTableGateway.formatBAResultSet: " + ex);
            }
            System.out.println("Wrong product type!");
        }
        return null;
    }

    //Formats store result set into java object
    private Store formatStoreResultSet(ResultSet rs) {
        Store str = null;

        //Variable representation of object data
        int id;
        String storeName, storeAddress;
        List<Product> pl = null;

        try {
            while (rs.next()) { //Parses resultset data and creates objects on a loop, until resultset is out of rows
                id = rs.getInt(COLUMN_STORE_ID);
                storeName = rs.getString(COLUMN_NAME);
                storeAddress = rs.getString(COLUMN_STORE_ADDRESS);
                //Creates store object with unpacked data and empty product arraylist
                str = new Store(id, storeName, storeAddress, pl);
            }
        } catch (SQLException ex) {
            if (Meta.debug) {
                System.out.println("-Debug- Exception caught in MainTableGateway.formatStoreResultSet: " + ex);
            }
            System.out.println("Something went wrong formatting entries from database");
        }
        return str;
    }

    //Formats product data into respective java objects
    private List<Product> formatProductResultSet(ResultSet rs) {
        List<Product> pl = new ArrayList<>();

        //Objects
        Bicycle bo;
        BicycleAccessories ba;

        //This variable is used to determine which subclass it is
        String type;

        //Product variables (Shared by both products)
        int id, storeID;
        double price;
        String colour, productName;

        //Bicycle specific variables
        int gearCount, modelNo;
        double weight;
        String brand;

        //Bicycle Accessory specific variables
        String baType;
        boolean inStock;

        try {
            while (rs.next()) { //Parses resultset data and creates objects on a loop, until resultset is out of rows
                id = rs.getInt(COLUMN_PRODUCT_ID);
                price = rs.getDouble(COLUMN_PRICE);
                colour = rs.getString(COLUMN_COLOUR);
                productName = rs.getString(COLUMN_PRODUCT_NAME);
                storeID = rs.getInt(COLUMN_P_STORE_ID);
                type = rs.getString(COLUMN_TYPE);
                if (type.equals("bicycle")) {
                    gearCount = rs.getInt(COLUMN_GEAR_COUNT);
                    modelNo = rs.getInt(COLUMN_MODEL_NO);
                    weight = rs.getDouble(COLUMN_WEIGHT);
                    brand = rs.getString(COLUMN_BRAND);
                    bo = new Bicycle(id, price, colour, productName, gearCount, modelNo, weight, brand, storeID);
                    pl.add(bo);
                } else if (type.equals("bicycle accessory")) {
                    baType = rs.getString(COLUMN_BA_TYPE);
                    inStock = rs.getBoolean(COLUMN_IN_STOCK);
                    ba = new BicycleAccessories(id, price, colour, productName, baType, inStock, storeID);
                    pl.add(ba);
                }
            }
            return pl; //Return all the objects created in an arraylist
        } catch (SQLException ex) {
            if (Meta.debug) {
                System.out.println("-Debug- Exception caught in MainTableGateway.formatProductResultSet: " + ex);
            }
            System.out.println("Something went wrong formatting entries from database");
        }
        return null;
    }
}
