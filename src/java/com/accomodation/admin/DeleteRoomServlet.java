package com.accomodation.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accomodation.roomdao.RoomDao;
import com.accomodation.roomdao.RoomTypeDao;

public class DeleteRoomServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int roomid = Integer.parseInt(request.getParameter("id"));
		if (RoomDao.deleteRoom(roomid)) {
			response.sendRedirect("welcomeadmin");
		} else {
			response.sendRedirect("welcomeadmin");
		}
	}
}
