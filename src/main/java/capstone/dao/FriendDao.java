package capstone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import capstone.model.Friend;
import capstone.model.Login;
import capstone.model.User;

public class FriendDao {
	
	//return type is boolean
	//returns if current logged in user is friends with another user
	public static boolean isFriends(int userId, int friendId) {
		boolean validate = false;
		ResultSet result;
		
		try {
			//get connection to db
			Connection connection = DBConnection.getConnection();
	
			//select sql query
			String selectUserSQL = "SELECT * FROM friends WHERE user_id = ? and friend_id = ?";
			
			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(selectUserSQL);
			statement.setInt(1, userId);
			statement.setInt(2, friendId);
			
			result = statement.executeQuery();
			
			//resultset object initially points above first row, use next() method to move to next row. if the user
			// exists, than it will return true, if there are no rows, it will return false, hence user doesn't exist.
			validate = result.next();
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return validate;
	}
	
	public static int getStatus(int userId, int friendId) {
		ResultSet result;
		int status = 0;
		
		try {

			//get connection to db
			Connection connection = DBConnection.getConnection();
	
			//select sql query
			String selectUserSQL = "SELECT * FROM friends WHERE user_id = ? and friend_id = ?";
			
			//setting parameters with PreparedStatement
			PreparedStatement statement = connection.prepareStatement(selectUserSQL);
			statement.setInt(1, userId);
			statement.setInt(2, friendId);
			
			result = statement.executeQuery();
			
			//resultset object initially points above first row, use next() method to move to next row. if the user
			// exists, than it will return true, if there are no rows, it will return false, hence user doesn't exist.
			status = readFriendConnection(result);
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
		
	}
	
	private static int readFriendConnection(ResultSet result) throws SQLException {
		Friend friend = new Friend();
		
		while(result.next() == true) {
			friend.setID(result.getInt("friendsID"));
			friend.setUser_id(result.getInt("user_id"));
			friend.setFriend_id(result.getInt("friend_id"));
			friend.setStatus(result.getInt("status"));
		}
		return friend.getStatus();
	}

}
