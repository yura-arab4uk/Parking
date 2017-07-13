package com.parking.model.business.impl;

import com.parking.model.business.service.DAO;
import com.parking.model.entities.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yurii on 17.01.2017.
 */
public abstract class SQLDAO<T extends Item> implements DAO<T> {
    public interface Action {
        Object act(Statement statement) throws SQLException;
        void setValues(Object...values);
    }
    SqlWizard sqlWizard =new SqlWizard();
    String tableName;

    protected Action getter = new Action(){

        Object[] values;

        public Object act(Statement statement) throws SQLException{
            String sql=sqlWizard.selectFrom(tableName);
            sql=sqlWizard.addValues(sql,"id",values[0],"enabled","1");
            ResultSet resultSet = statement.executeQuery(sql);
            return get(resultSet).get(0);
        }

        public void setValues(Object...values){
            this.values=values;
        }

    };
    protected Action allGetter = new Action(){

        public Object act(Statement statement) throws SQLException{
            String sql=sqlWizard.selectFrom(tableName);
            sql=sqlWizard.addValues(sql,"enabled","1");
            ResultSet resultSet = statement.executeQuery(sql);
            return get(resultSet);
        }

        public void setValues(Object...values){
            throw new UnsupportedOperationException();
        }

    };
    protected Action updater=null;
    protected Action adder=null;
    public SQLDAO(String tableName) {
        this.tableName=tableName;
    }

    public Object setDB (Action action){
        Connection connection=null;
        Statement statement=null;
        Object result=null;
        try {
            connection = MyDataSource.getConnection();
            statement = connection.createStatement();
            result = action.act(statement);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int[] add(Iterable<T> items) {
        adder.setValues(items);
        int[]x=(int[])setDB(adder);
        return x;
    }

    @Override
    public int[] add(T item) {
        return add(Collections.singletonList(item));
    }

    @Override
    public Integer update(T item){
        updater.setValues(item);
        return (Integer)setDB(updater);
    };



    @Override
    public Integer remove(T item) {
        return (Integer)setDB(new Action(){
            public Object act(Statement statement) throws SQLException{
                return statement.executeUpdate(sqlWizard.remove(tableName, item.getId()));
            };
            public void setValues(Object...values){};
        });


    }



    @Override
    public T get(Long id) {

        getter.setValues(id);
        return (T)setDB(getter);

    }

    protected abstract List<T> get(ResultSet resultSet);

    @Override
    public List<T> getAll() {

        return (List<T>)setDB(allGetter);

    }

    public T getLast(){
        return (T)setDB(new Action() {
            @Override
            public Object act(Statement statement) throws SQLException {
                String sql=sqlWizard.selectFrom(tableName);
                sql=sqlWizard.addValues(sql,"id","(SELECT MAX(id) FROM "+tableName+" WHERE enabled=1)");
                ResultSet rs = statement.executeQuery(sql);
                List<T> list=get(rs);

                return (list==null)? null:list.get(0);
            }

            @Override
            public void setValues(Object... values) {

            }
        });
    };
}
