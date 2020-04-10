package ca2;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() { //Manages the singleton instance, only allows one instance to be created
        if (instance != null && Meta.debug) {
            System.out.println("-Debug- Singleton model already instantiated.");
        }
        if (instance == null) {
            if (Meta.debug) {
                System.out.println("-Debug- Singleton model instantiated.");
            }
            instance = new Model();
        }
        return instance;
    }

    private MainTableGateway gateway;

    private Model() { //Establishes connection to database
        try {
            this.gateway = new MainTableGateway(DBConnection.getInstance());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex); //Logs SQLException with additional information
        }
    }

    //Handles data coming from form, sends it to gateway for insertion to database
    public boolean addBicycle(Bicycle bo) {
        Bicycle boWithId = null;
        try {
            //Passed object data is unpacked into the gateway here
            boWithId = this.gateway.insertBicycle(
                    bo.getGearCount(), bo.getModelNo(), bo.getWeight(), bo.getBrand(),
                    bo.getColour(), bo.getPrice(), bo.getProductName(), bo.getStoreID()
            );
        } catch (SQLException ex) {
            if (Meta.debug) {
                System.out.println("-DEBUG- SQLException in addBicycle function, except: " + ex);
            }
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boWithId != null;
    }

    public Bicycle readBicycleObj(int bID) {
        Bicycle bo = null;
        try {
            //Checks if bicycle exists first, then tries read the data
            if (this.gateway.checkProductExist(bID)) {
                bo = this.gateway.readBicycle(bID);
            }
        } catch (SQLException sqle) {
            if (Meta.debug) {
                System.out.println("-DEBUG- SQLException in readBicycleObj function, except: " + sqle);
            }
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return bo;
    }

    //For one to many read by ID
    public Store getStoresProducts(int dID) {
        Store str = null;
        try {
            str = this.gateway.allStoreProducts(dID);
        } catch (SQLException sqle) {
            if (Meta.debug) {
                System.out.println("-DEBUG- SQLException in getStoresProducts function, except: " + sqle);
            }
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return str;
    }

    //Returns true if store with supplied ID exists
    public boolean checkStoreID(int sID) {
        return this.gateway.checkStoreExist(sID);
    }

    //Returns true if product with supplied ID exists
    public boolean checkProductID(int pID) {
        return this.gateway.checkProductExist(pID);
    }
}
