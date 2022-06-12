package capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import capstone.model.Login;
import capstone.model.User;


public class LoginDao {
	
	//return type is boolean
	//returns whether user exists in db
	public static boolean validateUser(Login login) {
		boolean validate = false;
		ResultSet result;
		
		try {
			//get connection to db
			Connection connection = DBConnection.getConnection();
	
			//select sql query
			String selectUserSQL = "SELECT * FROM user WHERE username = ? and password = ?";
			
			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(selectUserSQL);
			statement.setString(1, login.getUsername());
			statement.setString(2, login.getPassword());
			
			result = statement.executeQuery();
			
			//resultset object initially points above first row, use next() method to move to next row. if the user
			// exists, than it will return true, if there are no rows, it will return false, hence user doesn't exist.
			validate = result.next();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return validate;
	}
	
	//return type is a user
	//takes username and password and returns a User object
	public static User getUserFromLogin(Login login) {
		ResultSet result;
		User user = new User();
		try {
			//get connection to database
			Connection connection = DBConnection.getConnection();
			//select sql query
			String selectUserSQL = "SELECT * FROM user WHERE username = ? and password = ?";
			
			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(selectUserSQL);
			statement.setString(1, login.getUsername());
			statement.setString(2, login.getPassword());
			
			result = statement.executeQuery();
			
			user = readUser(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		return user;
	}
	
	private static User readUser(ResultSet result) throws SQLException {
		User user = new User();
		
		while(result.next() == true) {
			user.setID(result.getInt("id"));
			user.setfName(result.getString("fName"));
			user.setlName(result.getString("lName"));
			user.setUsername(result.getString("username"));
			user.setPassword(result.getString("password"));
			user.setEmail(result.getString("email"));
			user.setBirthdate(result.getString("birthdate"));
		}
		return user;	
	}
}
