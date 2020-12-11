package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.UserDao;
import com.vincenzoracca.jpaproject.entities.UserEntity;


public class UserDaoImpl extends JpaDaoImpl<UserEntity, Long> implements UserDao {

    private static UserDaoImpl userDao;

    private UserDaoImpl() {}

    public static UserDao getInstance() {
        if(userDao == null) {
            userDao = new UserDaoImpl();
        }

        return userDao;
    }

}
