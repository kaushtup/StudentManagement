package com.accomodation.welcom;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accommodation.filter.Filter;
import com.accomodation.model.RoomModel;
import com.accomodation.model.RoomTypeModel;
import com.accomodation.model.StudentRoomViewModel;
import com.accomodation.roomdao.RoomDao;
import com.accomodation.roomdao.RoomTypeDao;
import java.util.ArrayList;

public class WelcomeAdminServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		
		List<RoomTypeModel> listRoomType=RoomTypeDao.getRoomtype();
		request.setAttribute("listRoomType", listRoomType);
		
		List<RoomModel> listRooms = RoomDao.getRooms();
		request.setAttribute("listRoom", listRooms);
                
                List<StudentRoomViewModel> studentRooms = RoomDao.getStudentRooms();
		request.setAttribute("listStudentRoom", studentRooms);
		Filter.getPage(request, response,"welcomeadmin").forward(request, response);
	}
}
