package ca2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainTableGateway {
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
}
