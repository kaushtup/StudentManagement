package com.accomodation.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accomodation.model.RoomTypeModel;
import com.accomodation.roomdao.RoomTypeDao;

public class DeleteRoomTypeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int roomid = Integer.parseInt(request.getParameter("id"));
		if (RoomTypeDao.deleteRoomtype(roomid)) {
			response.sendRedirect("welcomeadmin");
		} else {
			response.sendRedirect("welcomeadmin");
		}
	}
}
