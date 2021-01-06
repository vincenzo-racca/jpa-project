package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.CompanyDao;
import com.vincenzoracca.jpaproject.entities.Company;


public class CompanyDaoImpl extends JpaDaoImpl<Company, String >implements CompanyDao {

    private static CompanyDao companyDao;

    private CompanyDaoImpl(){}

    public static CompanyDao getInstance() {
        if(companyDao == null) {
            companyDao = new CompanyDaoImpl();
        }
        return companyDao;
    }

}
