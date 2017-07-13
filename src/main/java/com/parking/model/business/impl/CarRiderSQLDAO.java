package com.parking.model.business.impl;

import com.parking.model.business.service.CarRiderDAO;
import com.parking.model.entities.CarRider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii on 18.01.2017.
 */
public class CarRiderSQLDAO extends SQLDAO<CarRider> implements CarRiderDAO {


    public CarRiderSQLDAO() {
        super("car_rider");
        adder = new SQLDAO.Action() {
            Object[] values=null;
            public Object act(Statement statement) throws SQLException {
                Iterable<CarRider> items=(Iterable<CarRider>)values[0];
                for (CarRider carRider : items) {

                    String sql = sqlWizard.insert(tableName);
                    sql = sqlWizard.insValues(sql,new Object[]{
                            "NULL",
                            1,
                            carRider.getName(),
                            carRider.getPhone_number(),

                    });
                    statement.addBatch(sql);
                }
                return statement.executeBatch();

            }

            public void setValues(Object... values) {
                this.values=values;
            }

        };
        updater = new SQLDAO.Action() {
            Object[] values=null;
            public Object act(Statement statement) throws SQLException {
                String sql = sqlWizard.update(tableName);
                CarRider carRider=(CarRider)values[0];

                Object [][] c = {
                        {"name", carRider.getName()},
                        {"phone_number", carRider.getPhone_number()},
                        {"enabled", carRider.getEnabled()},

                };
                for (Object [] cc:c) {
                    sql=sqlWizard.addIfNotNull(sql, cc[0], cc[1]);
                }
                sql=sql.replace("set ,", "set ");
                sql=sqlWizard.addWhereId(sql, carRider.getId());

                return statement.executeUpdate(sql);
            }

            public void setValues(Object... values) {
                this.values=values;
            }

        };
    }

    @Override
    protected List<CarRider> get(ResultSet rs) {
        List<CarRider> carRiders=new ArrayList<>();

        try {
            while (rs.next()){
                CarRider carRider = new CarRider(rs.getLong("id"),rs.getString("name"),rs.getInt("phone_number"),rs.getInt("enabled"));
                carRiders.add(carRider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carRiders;
    }

}
