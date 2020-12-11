package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.CarDao;
import com.vincenzoracca.jpaproject.entities.CarEntity;
import org.springframework.stereotype.Repository;


@Repository
public class CarDaoImpl extends JpaDaoImpl<CarEntity, Long> implements CarDao {

}
