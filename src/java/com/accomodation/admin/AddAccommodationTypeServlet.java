package com.accomodation.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accommodation.filter.Filter;
import com.accomodation.model.RoomTypeModel;
import com.accomodation.registerdao.RegisterDao;
import com.accomodation.roomdao.RoomTypeDao;

public class AddAccommodationTypeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		  
		  Filter.getPage(request, response,"addaccommodationtype").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		String typename=request.getParameter("type");
		int available= Integer.parseInt(request.getParameter("available"));
		RoomTypeModel roomTypeModel= new RoomTypeModel(typename, available);
		if(RoomTypeDao.addRoomtype(roomTypeModel)) {
			response.sendRedirect("welcomeadmin");
		}else {
			
			response.sendRedirect("addaccommodationtype");
		}
	}

}
