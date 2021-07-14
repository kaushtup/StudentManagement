package com.accomodation.admin;

import com.accommodation.filter.Filter;
import com.accomodation.model.RoomTypeModel;
import com.accomodation.roomdao.RoomTypeDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditRoomTypeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RoomTypeModel roomTypeModel = RoomTypeDao.getRoomtypeId(id);
        request.setAttribute("Room", roomTypeModel);
        Filter.getPage(request, response, "editroomtype").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomtype = request.getParameter("type");
        int roomavailable = Integer.parseInt(request.getParameter("available"));
        int roomid = Integer.parseInt(request.getParameter("id"));
        RoomTypeModel roomTypeModel = new RoomTypeModel(roomid, roomtype, roomavailable);
        if (RoomTypeDao.inserEditRoomtype(roomTypeModel)) {
            response.sendRedirect("welcomeadmin");
        } else {
            response.sendRedirect("editroomtype");
        }
    }
}
