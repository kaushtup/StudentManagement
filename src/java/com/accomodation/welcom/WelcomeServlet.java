package com.accomodation.welcom;

import com.accommodation.filter.Filter;
import com.accomodation.model.RoomModel;
import com.accomodation.roomdao.RoomDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RoomModel> listRooms = RoomDao.getRooms();
        request.setAttribute("listRoom", listRooms);

        Filter.getPage(request, response, "welcome").forward(request, response);
    }
}
