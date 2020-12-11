package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.CarDao;
import com.vincenzoracca.jpaproject.entities.CarEntity;

public class CarDaoImpl extends JpaDaoImpl<CarEntity, Long> implements CarDao {

    private static CarDao carDao;

    private CarDaoImpl(){}

    public static CarDao getInstance() {
        if(carDao == null) {
            carDao = new CarDaoImpl();
        }
        return carDao;
    }
}
