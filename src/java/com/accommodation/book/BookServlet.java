package com.accommodation.book;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.accomodation.roomdao.RoomDao;

public class BookServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int roomid = Integer.parseInt(request.getParameter("id"));
		HttpSession sessionuser = request.getSession(false);
		int studentid=(int) sessionuser.getAttribute("userid");
		if (RoomDao.bookRoom(roomid, studentid)) {
			 RequestDispatcher resDispatcher=request.getRequestDispatcher("welcome");
			 request.setAttribute("message", "Booking successfull check you room details");
			  resDispatcher.forward(request, response);
		} else {
			 RequestDispatcher resDispatcher=request.getRequestDispatcher("welcome");
			 request.setAttribute("message", "You already have a book in your name");
			  resDispatcher.forward(request, response);
		}
	}
}
