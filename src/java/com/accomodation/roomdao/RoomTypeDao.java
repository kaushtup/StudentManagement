package com.accomodation.roomdao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import com.accomodation.dataconncetion.DatabaseConnection;
import com.accomodation.model.RegistrationModel;
import com.accomodation.model.RoomTypeModel;

public class RoomTypeDao {
	public static boolean addRoomtype(RoomTypeModel roomtypeModel) throws ServletException, IOException {
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			PreparedStatement queryRoom = con.prepareStatement("insert into room_type values(?,?,?)");
			queryRoom.setString(1, null);
			queryRoom.setString(2, roomtypeModel.getType());
			queryRoom.setInt(3, roomtypeModel.getAvailable());
			queryRoom.execute();
			queryRoom.close();
			con.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static List<RoomTypeModel> getRoomtype() throws ServletException, IOException {
		try {
			List<RoomTypeModel> listroomTypeModel = new ArrayList<>();
			Connection con = DatabaseConnection.initializeDatabase();
			PreparedStatement queryRoomGet = con.prepareStatement("select * from room_type");
			ResultSet rooms = queryRoomGet.executeQuery();
			while (rooms.next()) {
				String typename = rooms.getString("typename");
				int available = rooms.getInt("available");
				int id = rooms.getInt("id");
				RoomTypeModel roomTypeModel = new RoomTypeModel(id, typename, available);
				listroomTypeModel.add(roomTypeModel);
			}
			rooms.close();
			con.close();
			return listroomTypeModel;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static RoomTypeModel getRoomtypeId(int id) throws ServletException, IOException {
		try {
			RoomTypeModel roomTypeModel = null;
			Connection con = DatabaseConnection.initializeDatabase();
			PreparedStatement queryRoomGet = con.prepareStatement("select * from room_type where id=?");
			queryRoomGet.setInt(1, id);
			ResultSet room = queryRoomGet.executeQuery();
			if (room.next()) {
				String typename = room.getString("typename");
				int available = room.getInt("available");
				int idroom = room.getInt("id");
				roomTypeModel = new RoomTypeModel(idroom, typename, available);
			}
			return roomTypeModel;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public static boolean inserEditRoomtype(RoomTypeModel roomTypeModel) throws ServletException, IOException {

		try {
			Connection con = DatabaseConnection.initializeDatabase();
			PreparedStatement queryRoom = con
					.prepareStatement("update room_type set typename=?, available=? where id=?");
			queryRoom.setInt(3, roomTypeModel.getId());
			queryRoom.setString(1, roomTypeModel.getType());
			queryRoom.setInt(2, roomTypeModel.getAvailable());
			boolean rowUpdated = queryRoom.executeUpdate() > 0;
			queryRoom.close();
			con.close();
			return rowUpdated;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static boolean deleteRoomtype(int id) throws ServletException, IOException {
		try {
			Connection con = DatabaseConnection.initializeDatabase();
			PreparedStatement queryRoom = con.prepareStatement("delete from room_type  where id=?");
			queryRoom.setInt(1, id);
			boolean deleted = queryRoom.executeUpdate() > 0;
			return deleted;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
