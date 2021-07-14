package com.accomodation.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accommodation.filter.Filter;
import com.accomodation.model.RoomModel;
import com.accomodation.model.RoomTypeModel;
import com.accomodation.roomdao.RoomDao;
import com.accomodation.roomdao.RoomTypeDao;

public class AddAccommodationServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoomTypeModel> listRooms = RoomTypeDao.getRoomtype();
		request.setAttribute("listRoom", listRooms);
		Filter.getPage(request, response, "addaccommodation").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typename = Integer.parseInt(request.getParameter("type"));
		String location = request.getParameter("location");
		String description = request.getParameter("description");
		int charge = Integer.parseInt(request.getParameter("charge"));
		RoomModel roomModel = new RoomModel(typename,true,location,charge,description);
		if (RoomDao.addRoom(roomModel)) {
			response.sendRedirect("welcomeadmin");
		} else {

			response.sendRedirect("addaccommodation");
		}
	}
}
