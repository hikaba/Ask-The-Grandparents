package capstone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
    // Database Schema
    // CREATE DATABASE users DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
//     CREATE TABLE `user` (
//        `id` int(3) NOT NULL AUTO_INCREMENT,
//        `fName` varchar(20) DEFAULT NULL,
//        `lName` varchar(20) DEFAULT NULL,
//        `username` varchar(250) DEFAULT NULL,
//        `password` varchar(20) DEFAULT NULL,
//        `email` varchar(45) DEFAULT NULL,
//        `birthdate` DATE DEFAULT NULL,
//        PRIMARY KEY (`id`)
//      ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
	
//	CREATE TABLE `posts` (
//			`post_id` VARCHAR(36),
//			`title` varchar(100) DEFAULT NULL,
//			`content` varchar(1000) DEFAULT NULL,
//			`created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
//			`updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//			`likes` INT DEFAULT NULL,
//			`user_id` INT NOT NULL,
//			PRIMARY KEY (post_id),
//			FOREIGN KEY(user_id) 
//			REFERENCES user(id)
//			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
//	
//	CREATE TABLE `friends` (
//			`friendsID` int(11) NOT NULL AUTO_INCREMENT,
//			`user_id` INT NOT NULL,
//			`friend_id` INT NOT NULL,
//			`status` int(11) NOT NULL,
//			PRIMARY KEY (friendsID),
//			FOREIGN KEY(user_id) REFERENCES user(id),
//			FOREIGN KEY(friend_id) REFERENCES user(id)
//			)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
    
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String CONN_STRING = "jdbc:mysql://localhost:3306/ATG";
	
	public static Connection getConnection() throws SQLException {
		
		Connection connection = null;
		
		
		try {
			//load the driver class
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver registered");
			
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
		
		} catch (SQLException e) {
			System.out.println("Connection Failed.");
            e.printStackTrace();
		
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			e.printStackTrace();
		}
		
		finally {
			if (connection != null)
				System.out.println("Connection Successful.");
		}
		
		return connection;
		
	}

}
