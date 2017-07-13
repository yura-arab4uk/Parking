package com.parking.model.business.impl.singleton;

import com.parking.model.business.impl.*;
import com.parking.model.entities.Item;

/**
 * Created by Yurii on 18.01.2017.
 */
public class SQLDAOSingleton  {

    private static BrandSQLDAO brandSQLDAO = new BrandSQLDAO();
    private static CarRiderSQLDAO carRiderSQLDAO = new CarRiderSQLDAO();
    private static CarSQLDAO carSQLDAO = new CarSQLDAO();
    private static ColorSQLDAO colorSQLDAO = new ColorSQLDAO();

    private SQLDAOSingleton() {
    }

    public static <M extends SQLDAO<T>,T extends Item> SQLDAO<T> getInstance(Class<M> tClass) {
        SQLDAO sqldao = null;
        if (tClass.isInstance(brandSQLDAO)) sqldao=brandSQLDAO;
        else if (tClass.isInstance(carRiderSQLDAO)) sqldao=carRiderSQLDAO;
        else if (tClass.isInstance(carSQLDAO)) sqldao=carSQLDAO;
        else if (tClass.isInstance(colorSQLDAO)) sqldao=colorSQLDAO;
        return sqldao;
    }
}
