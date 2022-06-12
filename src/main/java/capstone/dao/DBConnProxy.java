package capstone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnProxy {
		
    public static Connection getConnectionToDatabase() throws SQLException {
    	
    	return DBConnection.getConnection();
    }

}
