package com.accomodation.dataconncetion;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
public class DatabaseConnection {
	public static Connection initializeDatabase() throws SQLException,ClassNotFoundException{
		String dbDriver="com.mysql.jdbc.Driver"; 
        String dbURL = "jdbc:mysql://localhost:3306/";
        
        String dbName = "accommodation"; 
        String dbUsername = "root"; 
        String dbPassword = ""; 
        Class.forName(dbDriver); 
        Connection connection = DriverManager.getConnection(dbURL + dbName, 
                                                     dbUsername,  
                                                     dbPassword); 
        return connection; 
	}

}
