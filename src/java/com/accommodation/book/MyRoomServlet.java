package com.accommodation.book;

import com.accommodation.filter.Filter;
import com.accomodation.model.RoomModel;
import com.accomodation.roomdao.RoomDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyRoomServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessionuser = request.getSession(false);
        int studentid = (int) sessionuser.getAttribute("userid");
        RoomModel roomModel = RoomDao.getUserRoom(studentid);
        request.setAttribute("room", roomModel);
        Filter.getPage(request, response, "myroom").forward(request, response);
    }
}
