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
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return boWithId != null;
    }

    //Returns true if exists
    public boolean checkStoreID(int sID) {
        return this.gateway.checkStoreExist(sID);
    }
}
