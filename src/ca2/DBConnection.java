package ca2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection sConnection;
    //Establishes a connection to the database
    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        String host, db, user, password;
        
        host = "localhost";
        db = "cycleworld2";
        user = "root";
        password = ""; //Bad idea in a real DB, only for testing
        try {
        if (sConnection == null || sConnection.isClosed()) {
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.cj.jdbc.Driver");
            sConnection = DriverManager.getConnection(url, user, password);
        }
        } catch (SQLException ex){
            if(Meta.debug)
                System.out.println("-Debug- Exception caught in DBConnection: " + ex); //Handled exception if connection can't be established
            System.out.println("Cannot establish a connection to the database.");
        }
        return sConnection;
    }
}
