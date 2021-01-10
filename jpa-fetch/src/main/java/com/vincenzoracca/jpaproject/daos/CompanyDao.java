package com.vincenzoracca.jpaproject.daos;

import com.vincenzoracca.jpaproject.entities.Company;

public interface CompanyDao extends JpaDao<Company, String>{

    Company findByIdWithUsers(String id);

    Company findByIdWithUsersWitchFetch(String id);
}
