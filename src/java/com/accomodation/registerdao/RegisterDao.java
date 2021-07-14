package com.accomodation.registerdao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.accommodation.hash.Hash;
import com.accomodation.dataconncetion.DatabaseConnection;
import com.accomodation.model.RegistrationModel;

public class RegisterDao {
	public static boolean registerUser(RegistrationModel registerModel)  throws ServletException, IOException {
	
		// Initialize the database 
        try {
			Connection con = DatabaseConnection.initializeDatabase();
			PreparedStatement queryRoles= con.prepareStatement("select *  from role where role_name=?");
			queryRoles.setString(1,"student"); 
			ResultSet role=queryRoles.executeQuery();
			int userRoleId=0;
			if(role.next())
				userRoleId=role.getInt("id");
			String hashPassword= Hash.hashValue(registerModel.getPassword());
			PreparedStatement queryUseCheck= con.prepareStatement("select *  from user_info where user_name=? and password=?");
			queryUseCheck.setString(1,registerModel.getUsername());  
			queryUseCheck.setString(2,hashPassword);  
			ResultSet userCheck=queryUseCheck.executeQuery();
			if(userCheck.next())
				return false;
			PreparedStatement queryUser = con 
	                   .prepareStatement("insert into user_info(id,user_name,password,role_id) values(?,?, ?,?)"); 
			queryUser.setString(1, null);
			queryUser.setString(2, registerModel.getUsername());
			queryUser.setString(3, hashPassword);
			queryUser.setInt(4,userRoleId); 
			queryUser.executeUpdate();
			PreparedStatement queryUserGet= con.prepareStatement("select *  from user_info where user_name=? and password=?");
			queryUserGet.setString(1,registerModel.getUsername());  
			queryUserGet.setString(2,hashPassword);  
			ResultSet user=queryUserGet.executeQuery();
			if(user.next()) {
				PreparedStatement queryStudent= con.prepareStatement("insert into "
						+ "student_details(id,Student_name,Student_age,Student_address,Student_contact"
						+ ",User_id)"
						+ " values(?,?,?,?,?,?)");
				queryStudent.setString(1, null);
				queryStudent.setString(2,registerModel.getName());  
				queryStudent.setInt(3,registerModel.getAge()); 
				queryStudent.setString(4,registerModel.getAddress()); 
				queryStudent.setInt(5,registerModel.getContact());  
				queryStudent.setInt(6,user.getInt("id")); 
				queryStudent.execute();
				queryStudent.close();
			}
			queryUser.close();
			con.close();
			return true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		} 
	
	}

}
