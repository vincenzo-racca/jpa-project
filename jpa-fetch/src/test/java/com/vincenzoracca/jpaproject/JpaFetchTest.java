package com.vincenzoracca.jpaproject;

import com.vincenzoracca.jpaproject.daos.CompanyDao;
import com.vincenzoracca.jpaproject.daos.impl.CompanyDaoImpl;
import com.vincenzoracca.jpaproject.entities.Car;
import com.vincenzoracca.jpaproject.entities.Company;
import com.vincenzoracca.jpaproject.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class JpaFetchTest {

    private CompanyDao companyDao;

    @Before
    public void init() {
        System.out.println("\nSTART INITIAL LOAD");
        System.out.println("\n*************************************************");
        companyDao = CompanyDaoImpl.getInstance();
        for(int i = 0; i < 1000; i++) {
            Car car = new Car(UUID.randomUUID().toString(), "FIAT" + i);
            Car carTwo = new Car(UUID.randomUUID().toString(), "TOYOTA" + i);
            User user = new User("name" + i, "surname" + i, "code" + i, null,
                    Collections.singleton(car));
            User userTwo = new User("Vito" + i, "Corleone" + i, "code" + i, null,
                    Collections.singleton(carTwo));
            car.setUser(user); carTwo.setUser(userTwo);
            Company company = new Company("COMPANY" + i, "NAME" + i,
                    new HashSet<>(Arrays.asList(user, userTwo)));
            user.setCompany(company); userTwo.setCompany(company);
            companyDao.save(company);
        }
        Assert.assertEquals(1000, companyDao.count());
        System.out.println("\nEND INITIAL LOAD");
        System.out.println("\n*************************************************");
    }

//    @After
//    public void destroy() {
//        System.out.println("*************************************************\n");
//        System.out.println("BEGIN destroy");
//        companyDao.clear();
//        System.out.println("END destroy\n");
//    }

    @Test
    public void findByIdTest() {
        System.out.println("\nSTART FIND BY ID");
        System.out.println("\n*************************************************");
        Company entity = companyDao.findById("COMPANY1");
        System.out.println(entity.getUsers());
        Assert.assertNotNull(entity);
        System.out.println("\nSTOP FIND BY ID");
        System.out.println("\n*************************************************");
    }

    @Test
    public void findAllTest() {
        System.out.println("\nSTART FIND ALL");
        System.out.println("\n*************************************************");
        Collection<Company> entities = companyDao.findAll();
        Assert.assertNotNull(entities);
        System.out.println("\nSTOP FIND ALL");
        System.out.println("\n*************************************************");
    }
}
