package com.accomodation.roomdao;

import com.accomodation.dataconncetion.DatabaseConnection;
import com.accomodation.model.RoomModel;
import com.accomodation.model.StudentRoomViewModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;

public class RoomDao {

    public static boolean addRoom(RoomModel roomModel) throws ServletException, IOException {
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement queryRoomGet = con.prepareStatement("select * from room_type where id=?");
            queryRoomGet.setInt(1, roomModel.getTypeID());
            ResultSet room = queryRoomGet.executeQuery();
            if (room.next()) {
                String typename = room.getString("typename");
                PreparedStatement queryRoom = con.prepareStatement("insert into room_details values(?,?,?,?,?,?,?)");
                queryRoom.setString(1, null);
                queryRoom.setInt(2, roomModel.getTypeID());
                queryRoom.setString(3, roomModel.getLocation());
                queryRoom.setInt(4, roomModel.getMonthlyCharge());
                queryRoom.setString(5, typename);
                queryRoom.setBoolean(6, roomModel.getAvailable());
                queryRoom.setString(7, roomModel.getDescription());
                queryRoom.execute();
                queryRoom.close();
            }

            con.close();
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public static List<RoomModel> getRooms() throws ServletException, IOException {
        try {
            List<RoomModel> listroomTypeModel = new ArrayList<>();
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement queryRoomGet = con.prepareStatement("select * from room_details");
            ResultSet rooms = queryRoomGet.executeQuery();
            while (rooms.next()) {
                String location = rooms.getString("room_location");
                String description = rooms.getString("room_description");
                boolean available = rooms.getBoolean("room_available");
                String typename = rooms.getString("roomtype_name");
                int charge = rooms.getInt("monthly_charge");
                int id = rooms.getInt("id");
                int typeid = rooms.getInt("type_id");
//				PreparedStatement queryRoomavailable = con.prepareStatement(
//						"select * from room_details where id=?");
//				queryRoomavailable.setInt(1, id);
//				ResultSet roomavailable = queryRoomavailable.executeQuery();

//				if (roomavailable.next()) {
//					int available = roomavailable.getInt("available");
//					boolean isAvailable = true;
//					if (available <= 0) {
//						isAvailable = false;
//						return false;
//					}
//				}
                RoomModel roomTypeModel = new RoomModel(id, typeid, typename, available, location, charge, description);
                listroomTypeModel.add(roomTypeModel);
            }
            con.close();
            return listroomTypeModel;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public static RoomModel getRoomId(int id) throws ServletException, IOException {
        try {
            RoomModel roomTypeModel = null;
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement queryRoomGet = con.prepareStatement("select * from room_details where id=?");
            queryRoomGet.setInt(1, id);
            ResultSet rooms = queryRoomGet.executeQuery();
            if (rooms.next()) {
                String location = rooms.getString("room_location");
                String description = rooms.getString("room_description");
                boolean available = rooms.getBoolean("room_available");
                String typename = rooms.getString("roomtype_name");
                int charge = rooms.getInt("monthly_charge");
                int idroom = rooms.getInt("id");
                int typeid = rooms.getInt("type_id");
                roomTypeModel = new RoomModel(idroom, typeid, typename, available, location, charge, description);
            }
            return roomTypeModel;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public static RoomModel getUserRoom(int userid) throws ServletException, IOException {
        try {
            RoomModel roomTypeModel = null;
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement queryStudent = con.prepareStatement("select * from student_details where User_id=?");
            queryStudent.setInt(1, userid);
            ResultSet student = queryStudent.executeQuery();
            if (student.next()) {
                PreparedStatement queryStudentRoom = con
                        .prepareStatement("select * from student_room where student_id=?");
                queryStudentRoom.setInt(1, student.getInt("id"));
                ResultSet room = queryStudentRoom.executeQuery();
                if (room.next()) {
                    PreparedStatement queryRoomGet = con.prepareStatement("select * from room_details where id=?");
                    queryRoomGet.setInt(1, room.getInt("room_id"));
                    ResultSet rooms = queryRoomGet.executeQuery();
                    if (rooms.next()) {
                        String location = rooms.getString("room_location");
                        String description = rooms.getString("room_description");
                        boolean available = rooms.getBoolean("room_available");
                        String typename = rooms.getString("roomtype_name");
                        int charge = rooms.getInt("monthly_charge");
                        int idroom = rooms.getInt("id");
                        int typeid = rooms.getInt("type_id");
                        roomTypeModel = new RoomModel(idroom, typeid, typename, available, location, charge,
                                description);
                    }
                }
            }

            return roomTypeModel;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public static boolean changePaymentStaus(int id) throws ServletException, IOException, Exception {
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement queryRoomCheck = con
                    .prepareStatement("select * from student_room where student_id=?");
            queryRoomCheck.setInt(1, id);
            ResultSet roomcheck = queryRoomCheck.executeQuery();
            if (roomcheck.next()) {
                PreparedStatement queryRoom = con.prepareStatement("update accommodation.student_room"
                        + " set payement_status=1 where id=?");
                queryRoom.setInt(1, roomcheck.getInt("id"));
                boolean updated = queryRoom.executeUpdate() > 0;
                return updated;
            }
            return false;
        } catch (Exception ex) {
            throw ex;
        }

    }

    public static List<StudentRoomViewModel> getStudentRooms() throws ServletException, IOException {
        try {
            List<StudentRoomViewModel> studentRooms = new ArrayList<StudentRoomViewModel>();
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement queryRoomGet = con.prepareStatement("select sd.Student_name,rd.roomtype_name,rd.room_description,sr.payement_status from accommodation.student_room as sr\n"
                    + "left join accommodation.room_details as rd on sr.room_id =rd.id\n"
                    + "left join accommodation.student_details as sd on sr.student_id =sd.id");
            ResultSet rooms = queryRoomGet.executeQuery();
            if (rooms.next()) {
                String name = rooms.getString("Student_name");
                String description = rooms.getString("room_description");
                String roomType = rooms.getString("roomtype_name");
                boolean payment = rooms.getBoolean("payement_status");
                StudentRoomViewModel studentRoom = new StudentRoomViewModel(name, description, roomType, payment);
                studentRooms.add(studentRoom);
            }
            return studentRooms;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public static boolean insertEditRoom(RoomModel roomModel) throws ServletException, IOException {

        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement queryRoomGet = con.prepareStatement("select * from room_type where id=?");
            queryRoomGet.setInt(1, roomModel.getTypeID());
            ResultSet room = queryRoomGet.executeQuery();
            if (room.next()) {
                String typename = room.getString("typename");

                PreparedStatement queryRoom = con.prepareStatement(
                        "update room_details set roomtype_name=?, room_available=?,room_location=?,monthly_charge=?,"
                        + "room_description=?,type_id=? where id=?");
                queryRoom.setString(1, typename);
                queryRoom.setBoolean(2, true);
                queryRoom.setString(3, roomModel.getLocation());
                queryRoom.setInt(4, roomModel.getMonthlyCharge());
                queryRoom.setString(5, roomModel.getDescription());
                queryRoom.setInt(6, roomModel.getTypeID());
                queryRoom.setInt(7, roomModel.getId());
                boolean rowUpdated = queryRoom.executeUpdate() > 0;

                queryRoom.close();
                con.close();
                return rowUpdated;
            }
            return false;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public static boolean deleteRoom(int id) throws ServletException, IOException {
        try {
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement queryRoom = con.prepareStatement("delete from room_details  where id=?");
            queryRoom.setInt(1, id);
            boolean deleted = queryRoom.executeUpdate() > 0;
            return deleted;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public static boolean bookRoom(int roomid, int userid) throws ServletException, IOException {
        try {
            Connection con = DatabaseConnection.initializeDatabase();

            PreparedStatement queryStudent = con.prepareStatement("select * from student_details where User_id=?");
            queryStudent.setInt(1, userid);
            ResultSet student = queryStudent.executeQuery();
            if (student.next()) {
                PreparedStatement queryRoomCheck = con
                        .prepareStatement("select * from student_room where student_id=?");
                queryRoomCheck.setInt(1, student.getInt("id"));
                ResultSet roomcheck = queryRoomCheck.executeQuery();
                if (roomcheck.next()) {
                    return false;
                }
                PreparedStatement queryRoomavailable = con.prepareStatement(
                        "select d.type_id,d.room_location,t.available " + "from accommodation.room_details as d\r\n"
                        + "left join accommodation.room_type as t on d.id =t.id\r\n" + "where d.id=?");
                queryRoomavailable.setInt(1, roomid);
                ResultSet roomavailable = queryRoomavailable.executeQuery();

                if (roomavailable.next()) {
                    int available = roomavailable.getInt("available");
                    int rommTypeId = roomavailable.getInt("type_id");
                    boolean isAvailable = true;
                    if (available <= 0) {
                        isAvailable = false;
                        return false;
                    }
                    PreparedStatement queryRoom = con
                            .prepareStatement("insert into student_room(id,student_id,room_id) values" + "(?,?,?)");
                    queryRoom.setString(1, null);
                    queryRoom.setInt(3, roomid);
                    queryRoom.setInt(2, student.getInt("id"));
                    queryRoom.execute();
                    PreparedStatement queryRoomdetails = con
                            .prepareStatement("update room_details set room_available=? where id=?");

                    queryRoomdetails.setBoolean(1, isAvailable);
                    queryRoomdetails.setInt(2, roomid);
                    boolean rowUpdated = queryRoomdetails.executeUpdate() > 0;
                    PreparedStatement queryRoomtype = con
                            .prepareStatement("update room_type set available=? where id=?");

                    queryRoomtype.setInt(1, available - 1);
                    queryRoomtype.setInt(2, rommTypeId);
                    boolean rowUpdatedtype = queryRoomtype.executeUpdate() > 0;
                    queryRoomdetails.close();
                    queryRoom.close();
                }
                queryRoomavailable.close();

                return true;
            }

            queryStudent.close();
            con.close();
            return false;

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }
}
