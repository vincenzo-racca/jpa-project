package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.UserDao;
import com.vincenzoracca.jpaproject.entities.UserEntity;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl extends JpaDaoImpl<UserEntity, Long> implements UserDao {

}
