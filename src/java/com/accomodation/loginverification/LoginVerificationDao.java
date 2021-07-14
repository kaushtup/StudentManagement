package com.accomodation.loginverification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import com.accommodation.hash.Hash;
import com.accomodation.dataconncetion.DatabaseConnection;
import com.accomodation.model.UserModel;

public class LoginVerificationDao {

	public static UserModel validateUser(String username, String password) throws ServletException, IOException{
		
		UserModel userModel=null;
	try {
		Connection con = DatabaseConnection.initializeDatabase();
		String hashPassword=Hash.hashValue(password);
		PreparedStatement queryUser= con.prepareStatement("select * from user_info where user_name=? and password=? ");
		queryUser.setString(1,username); 
		queryUser.setString(2,hashPassword); 
		ResultSet user=queryUser.executeQuery();
		if(user.next()) {
			String userNameDb=user.getString("user_name");
			int roleIdDb=user.getInt("role_id");
			int userid=user.getInt("id");
			PreparedStatement queryRoles= con.prepareStatement("select * from role where id=? ");
			queryRoles.setInt(1,roleIdDb); 
			ResultSet role=queryRoles.executeQuery();
			if(role.next()) {
				String roleNameDb=role.getString("role_name");
				userModel= new UserModel(userid,userNameDb,roleIdDb,roleNameDb);
			}
		}
	} catch (Exception e) 
	{
		// TODO: handle exception
	}
	return userModel;
	}
}
