package com.parking.model.business.impl;

import com.parking.model.entities.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yurii on 18.01.2017.
 */
public class BrandSQLDAO extends SQLDAO<Brand> {

    public BrandSQLDAO() {
        super("brand");
        adder = new SQLDAO.Action() {
            Object[] values=null;
            public Object act(Statement statement) throws SQLException {
                Iterable<Brand> items=(Iterable<Brand>)values[0];
                for (Brand brand : items) {

                    String sql = sqlWizard.insert(tableName);
                    sql = sqlWizard.insValues(sql,new Object[]{
                            "NULL",
                            1,
                            brand.getName(),

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
                Brand brand=(Brand)values[0];

                Object [][] c = {
                        {"name", brand.getName()},
                        {"enabled", brand.getEnabled()},

                };
                for (Object [] cc:c) {
                    sql=sqlWizard.addIfNotNull(sql, cc[0], cc[1]);
                }
                sql=sql.replace("set ,", "set ");
                sql=sqlWizard.addWhereId(sql, brand.getId());

                return statement.executeUpdate(sql);
            }

            public void setValues(Object... values) {
                this.values=values;
            }

        };
    }

    @Override
    protected List<Brand> get(ResultSet rs) {
        List<Brand> brands=new ArrayList<>();

        try {
            while (rs.next()){
                Brand brand = new Brand(rs.getLong("id"),rs.getString("name"),rs.getInt("enabled"));
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return brands;
    }

}
