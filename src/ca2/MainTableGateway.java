package ca2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

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
                System.out.println("-Debug- Acceptable exception in function checkExist " + sqle);
            }
        }
        return false;
    }
}
