package com.parking.model.business.impl;

import com.parking.model.entities.Color;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii on 18.01.2017.
 */
public class ColorSQLDAO extends SQLDAO<Color>{

    public ColorSQLDAO() {
        super("color");
        adder = new SQLDAO.Action() {
            Object[] values=null;
            public Object act(Statement statement) throws SQLException {
                Iterable<Color> items=(Iterable<Color>)values[0];
                for (Color color : items) {

                    String sql = sqlWizard.insert(tableName);
                    sql = sqlWizard.insValues(sql,new Object[]{
                            "NULL",
                            1,
                            color.getName(),
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
                Color color=(Color)values[0];

                Object [][] c = {
                        {"name", color.getName()},
                        {"enabled", color.getEnabled()},

                };
                for (Object [] cc:c) {
                    sql=sqlWizard.addIfNotNull(sql, cc[0], cc[1]);
                }
                sql=sql.replace("set ,", "set ");
                sql=sqlWizard.addWhereId(sql, color.getId());

                return statement.executeUpdate(sql);
            }

            public void setValues(Object... values) {
                this.values=values;
            }

        };
    }

    @Override
    protected List<Color> get(ResultSet rs) {
        List<Color> colors=new ArrayList<>();

        try {
            while (rs.next()){
                Color color = new Color(rs.getLong("id"),rs.getString("name"),rs.getInt("enabled"));
                colors.add(color);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return colors;
    }

}
