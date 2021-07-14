/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accommodation.dataparser;

import com.accomodation.model.RoomTypeModel;
import com.accomodation.roomdao.RoomTypeDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Bishodeep
 */
public class AccommodationTypeEditParserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RoomTypeModel roomModel = RoomTypeDao.getRoomtypeId(id);
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", roomModel.getId());
            obj.put("type", roomModel.getType());
            obj.put("available", roomModel.getAvailable());
        } catch (JSONException ex) {
            Logger.getLogger(AccommodationTypeParserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.getWriter().write(obj.toString());
    }
}
