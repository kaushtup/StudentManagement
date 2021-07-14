/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accommodation.dataparser;

import com.accomodation.model.RoomModel;
import com.accomodation.roomdao.RoomDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Bishodeep
 */
public class StudentRoomParserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessionuser = request.getSession(false);
        int studentid = (int) sessionuser.getAttribute("userid");
        RoomModel roomModel = RoomDao.getUserRoom(studentid);
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", roomModel.getId());
            obj.put("type", roomModel.getTypeName());
            obj.put("available", roomModel.getAvailable());
            obj.put("location", roomModel.getLocation());
            obj.put("charge", roomModel.getMonthlyCharge());
            obj.put("description", roomModel.getDescription());
        } catch (JSONException ex) {
            Logger.getLogger(AccommodationTypeParserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.getWriter().write(obj.toString());
    }
}
