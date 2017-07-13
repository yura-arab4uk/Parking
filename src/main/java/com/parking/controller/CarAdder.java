package com.parking.controller;

import com.parking.model.business.impl.BrandSQLDAO;
import com.parking.model.business.impl.CarRiderSQLDAO;
import com.parking.model.business.impl.CarSQLDAO;
import com.parking.model.business.impl.ColorSQLDAO;
import com.parking.model.business.impl.singleton.ParkingSingleton;
import com.parking.model.business.impl.singleton.SQLDAOSingleton;
import com.parking.model.business.service.CarDAO;
import com.parking.model.entities.Brand;
import com.parking.model.entities.Car;
import com.parking.model.entities.CarRider;
import com.parking.model.entities.Color;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Yurii on 20.01.2017.
 */
@WebServlet("/addCar")
public class CarAdder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Car car = new Car(null,
                request.getParameter("car_number"),
                new Timestamp(new Date().getTime()),
                null,
                new Long(request.getParameter("parking_time")),
                new Long(request.getParameter("brand_id")),
                new Long(request.getParameter("color_id")),
                1
        );
        System.out.print(car);
        CarRider carRider = new CarRider(null,
                request.getParameter("name"),
                new Integer(request.getParameter("phone_number")),
                1
        );
        SQLDAOSingleton.getInstance(CarSQLDAO.class).add(car);
        SQLDAOSingleton.getInstance(CarRiderSQLDAO.class).add(carRider);
        car = ((CarDAO) SQLDAOSingleton.getInstance(CarSQLDAO.class)).getLast();
        ParkingSingleton.getInstance().addCar(car);
        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = SQLDAOSingleton.getInstance(BrandSQLDAO.class).getAll();
        List<Color> colors = SQLDAOSingleton.getInstance(ColorSQLDAO.class).getAll();
        request.setAttribute("brands",brands);
        request.setAttribute("colors",colors);
        request.getRequestDispatcher("/WEB-INF/views/addcar.jsp").forward(request,response);
    }
}
