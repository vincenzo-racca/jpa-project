package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.AstrattaDao;
import com.vincenzoracca.jpaproject.entities.AstrattaEntity;

public class AstrattaDaoImpl extends JpaDaoImpl<AstrattaEntity, Long> implements AstrattaDao {

    private static AstrattaDao astrattaDao;

    private AstrattaDaoImpl() {}

    public static AstrattaDao getInstance(){
        if(astrattaDao == null) {
            astrattaDao = new AstrattaDaoImpl();
        }
        return astrattaDao;
    }
}
