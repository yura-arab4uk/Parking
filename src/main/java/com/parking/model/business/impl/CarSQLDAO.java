package com.parking.model.business.impl;


import com.parking.model.business.service.CarDAO;
import com.parking.model.entities.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii on 18.01.2017.
 */
public class CarSQLDAO extends SQLDAO<Car> implements CarDAO {


    public CarSQLDAO() {
        super("car");
        adder = new Action() {
            Object[] values=null;
            public Object act(Statement statement) throws SQLException {
                Iterable<Car> items=(Iterable<Car>)values[0];

                for (Car car : items) {



                    String sql = sqlWizard.insert(tableName);
                    sql = sqlWizard.insValues(sql,new Object[]{
                            "NULL",
                            1,
                            car.getCar_number(),
                            car.getCheckin_datetime(),
                            "NULL",
                            car.getParking_time(),
                            car.getBrand_id(),
                            car.getColor_id(),

                    });
                    statement.addBatch(sql);
                }
                return statement.executeBatch();

            }

            public void setValues(Object... values) {
                this.values=values;
            }

        };
        updater = new Action() {
            Object[] values=null;
            public Object act(Statement statement) throws SQLException {
                String sql = sqlWizard.update(tableName);
                Car car=(Car)values[0];



                Object [][] c = {
                        {"car_number", car.getCar_number()},
                        {"checkin_datetime", car.getCheckin_datetime()},
                        {"checkout_datetime", car.getCheckout_datetime()},
                        {"parking_time", car.getParking_time()},
                        {"brand_id", car.getBrand_id()},
                        {"color_id", car.getColor_id()},
                        {"enabled", car.getEnabled()}
                };
                for (Object [] cc:c) {
                    sql=sqlWizard.addIfNotNull(sql, cc[0], cc[1]);
                }
                sql=sql.replace("set ,", "set ");
                sql=sqlWizard.addWhereId(sql, car.getId());

                return statement.executeUpdate(sql);
            }

            public void setValues(Object... values) {
                this.values=values;
            }

        };
    }


    @Override
    protected List<Car> get(ResultSet rs) {
        List<Car> cars=new ArrayList<>();
        SimpleDateFormat ft =
                new SimpleDateFormat ("dd.MM.yyyy HH:mm:ss");
        try {
            while (rs.next()){
            Car car = new Car();
            car.setId(rs.getLong("id"));
            car.setCar_number(rs.getString("car_number"));
                Timestamp checkin_datetime =rs.getTimestamp("checkin_datetime");

            car.setCheckin_datetime(checkin_datetime);
                Timestamp checkout_datetime =rs.getTimestamp("checkout_datetime");

            car.setCheckout_datetime(checkout_datetime);
            car.setParking_time(rs.getLong("parking_time"));
            car.setBrand_id(rs.getLong("brand_id"));
            car.setColor_id(rs.getLong("color_id"));
            car.setEnabled(rs.getInt("enabled"));
            cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

}
