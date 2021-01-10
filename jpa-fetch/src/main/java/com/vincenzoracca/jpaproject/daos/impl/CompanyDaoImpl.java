package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.CompanyDao;
import com.vincenzoracca.jpaproject.entities.Company;
import com.vincenzoracca.jpaproject.entities.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public class CompanyDaoImpl extends JpaDaoImpl<Company, String >implements CompanyDao {

    @Override
    @Transactional
    public Company findByIdWithUsers(String id) {
        Company entity = entityManager.find(Company.class, id);
        Set<User> users = entity.getUsers();
        System.out.println("SIZE USERS: " + users.size());
        return entity;
    }

    @Override
    public Company findByIdWithUsersWitchFetch(String id) {
        String sql = "SELECT c FROM Company c LEFT JOIN FETCH c.users WHERE c.code = :id";
        Company entity = entityManager.createQuery(sql, Company.class)
                                        .setParameter("id", id)
                                        .getSingleResult();
        Set<User> users = entity.getUsers();
        System.out.println("SIZE USERS: " + users.size());
        return entity;
    }


}
