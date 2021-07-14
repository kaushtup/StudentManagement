package com.accomodation.seed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.accommodation.hash.Hash;
import com.accomodation.dataconncetion.DatabaseConnection;

public  class SeedDataDao {
	public static void SeeData() throws ClassNotFoundException, SQLException {
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			PreparedStatement queryRolecheck= con.prepareStatement("select *  from role ");
			ResultSet rolecheck=queryRolecheck.executeQuery();
			if(!rolecheck.next()) 
			{
				PreparedStatement queryRoles= con.prepareStatement("insert into  role  values(?,?)");
				queryRoles.setString(1, null); 
				queryRoles.setString(2, "admin"); 
				queryRoles.executeUpdate();
				queryRoles.close();
				PreparedStatement queryRolesstu= con.prepareStatement("insert into  role  values(?,?)");
				queryRolesstu.setInt(1, 2); 
				queryRolesstu.setString(2, "student"); 
				queryRolesstu.executeUpdate();
				queryRolesstu.close();
			}
			PreparedStatement queryUsercheck= con.prepareStatement("select *  from user_info ");
			ResultSet usercheck=queryUsercheck.executeQuery();
			if(!usercheck.next()) 
			{
			String hashPassword= Hash.hashValue("Admin@123");
			PreparedStatement queryUser = con 
	                .prepareStatement("insert into user_info(id,user_name,password,role_id) values(?,?, ?,?)"); 
			queryUser.setString(1, null);
			queryUser.setString(2, "Admin");
			queryUser.setString(3, hashPassword);
			queryUser.setInt(4,1); 
			queryUser.executeUpdate();
			queryUser.close();
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
