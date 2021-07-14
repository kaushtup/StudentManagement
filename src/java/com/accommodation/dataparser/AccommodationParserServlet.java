/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accommodation.dataparser;

import com.accomodation.model.RoomModel;
import com.accomodation.roomdao.RoomDao;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Bishodeep
 */
public class AccommodationParserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<RoomModel> listRooms = RoomDao.getRooms();
        JSONArray json = new JSONArray();
        for (RoomModel roomtype : listRooms) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", roomtype.getId());
                obj.put("type", roomtype.getTypeName());
                obj.put("available", roomtype.getAvailable());
                obj.put("description", roomtype.getDescription());
                obj.put("location", roomtype.getLocation());
                obj.put("monthlycharge", roomtype.getMonthlyCharge());
                json.put(obj);
            } catch (JSONException ex) {
                Logger.getLogger(AccommodationTypeParserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.getWriter().write(json.toString());
    }
}
