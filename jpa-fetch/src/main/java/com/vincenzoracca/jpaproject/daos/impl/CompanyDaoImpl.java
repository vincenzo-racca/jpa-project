package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.CompanyDao;
import com.vincenzoracca.jpaproject.entities.Company;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class CompanyDaoImpl extends JpaDaoImpl<Company, String >implements CompanyDao {

    @Override
    @Transactional
    public Company findByIdWithUsers(String id) {
        Company entity = entityManager.find(Company.class, id);
        System.out.println(entity.getUsers());
        return entity;
    }


}
