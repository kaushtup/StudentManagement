/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accommodation.dataparser;

import com.accomodation.model.RoomTypeModel;
import com.accomodation.roomdao.RoomTypeDao;
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
public class AccommodationTypeParserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<RoomTypeModel> listRoomType = RoomTypeDao.getRoomtype();
        JSONArray json = new JSONArray();
        for (RoomTypeModel roomtype : listRoomType) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("id", roomtype.getId());
                obj.put("type", roomtype.getType());
                obj.put("available", roomtype.getAvailable());
                json.put(obj);
            } catch (JSONException ex) {
                Logger.getLogger(AccommodationTypeParserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        response.getWriter().write(json.toString());
    }
}
