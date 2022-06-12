package capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import capstone.model.Login;
import capstone.model.User;


public class UserDao {

	public int registerUser(User user) {
		
		int rowsAffected = 0;
		
		try {
			//get connection to database
			Connection connection = DBConnection.getConnection();
			
			//insert sql query, the order is (id, fName, lName, username, password, email, birthdate)
			String insertUserSQL = "INSERT INTO user"
					+ " (fName, lName, username, password, email, birthdate)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";

			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(insertUserSQL);
			statement.setString(1, user.getfName());
			statement.setString(2, user.getlName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getBirthdate());
			
			//execute query
			rowsAffected = statement.executeUpdate();
			//close connection affect completed
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		return rowsAffected;
	}
	
	
	public int updateUser(User user) {
		
		int rowsAffected = 0;
		
		try {
			//get connection to database
			Connection connection = DBConnection.getConnection();
			
			//update sql query
			String updateUserSQL = "UPDATE user"
					+ " SET (fName = ?, lName = ?, username = ?, password = ?, email = ?, birthdate = ?)"
					+ " WHERE (id = ?)";

			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(updateUserSQL);
			statement.setString(1, user.getfName());
			statement.setString(2, user.getlName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getBirthdate());
			statement.setInt(7, user.getID());
			
			//execute query
			rowsAffected = statement.executeUpdate();
			//close connection affect completed
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		return rowsAffected;
	}
	
	
	public int deleteUser(User user) {
		
		int rowsAffected = 0;
		
		try {
			//get connection to database
			Connection connection = DBConnection.getConnection();
			
			//delete sql query
			String deleteUserSQL = "DELETE FROM user WHERE (id = ?)";

			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(deleteUserSQL);
			statement.setInt(1, user.getID());
			
			//execute query
			rowsAffected = statement.executeUpdate();
			//close connection affect completed
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			}
		
		return rowsAffected;
	}
	
	//checks if a user with that email already exits
	//return true if email exists in db, and false otherwise
	public boolean doesEmailExist( User user) {
		boolean validate = false;
		ResultSet result;
		
		try {
			//get connection to db
			Connection connection = DBConnection.getConnection();
	
			//select sql query
			String selectUserSQL = "SELECT * FROM user WHERE email = ?";
			
			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(selectUserSQL);
			statement.setString(1, user.getEmail());
			
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
	
	public boolean doesUsernameExist( User user) {
		boolean validate = false;
		ResultSet result;
		
		try {
			//get connection to db
			Connection connection = DBConnection.getConnection();
	
			//select sql query
			String selectUserSQL = "SELECT * FROM user WHERE username = ?";
			
			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(selectUserSQL);
			statement.setString(1, user.getUsername());
			
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
	
	public boolean doesUsernameExist(String username) {
		boolean validate = false;
		ResultSet result;
		
		try {
			//get connection to db
			Connection connection = DBConnection.getConnection();
	
			//select sql query
			String selectUserSQL = "SELECT * FROM user WHERE username = ?";
			
			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(selectUserSQL);
			statement.setString(1, username);
			
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
		//takes username and returns a User object
		public User getUserFromUsername(String username) {
			ResultSet result;
			User user = new User();
			try {
				//get connection to database
				Connection connection = DBConnection.getConnection();
				//select sql query
				String selectUserSQL = "SELECT * FROM user WHERE username = ?";
				
				//setting parameters with PreparedStatement
				PreparedStatement statement = connection.prepareStatement(selectUserSQL);
				statement.setString(1, username);
				
				result = statement.executeQuery();
				
				user = readUser(result);
				
			} catch (SQLException e) {
				e.printStackTrace();
				}
			return user;
		}
		
		private User readUser(ResultSet result) throws SQLException {
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
