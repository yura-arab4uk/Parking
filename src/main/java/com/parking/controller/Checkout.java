package com.parking.controller;

import com.parking.model.business.impl.CarRiderSQLDAO;
import com.parking.model.business.impl.CarSQLDAO;
import com.parking.model.business.impl.singleton.ParkingSingleton;
import com.parking.model.business.impl.singleton.SQLDAOSingleton;
import com.parking.model.entities.Car;
import com.parking.model.entities.CarRider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Yurii on 21.01.2017.
 */
@WebServlet("/checkout")
public class Checkout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat ft=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Long id=new Long(request.getParameter("id"));
        Car car=new Car(id,null,null,new Timestamp(new Date().getTime()),null,null,null,0);
        CarRider carRider=new CarRider(id,null,null,null);
        SQLDAOSingleton.getInstance(CarSQLDAO.class).update(car);
        SQLDAOSingleton.getInstance(CarRiderSQLDAO.class).remove(carRider);
        ParkingSingleton.getInstance().timedCars.remove(car);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("{\"status\":\"ok\"}");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
