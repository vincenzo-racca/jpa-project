package com.vincenzoracca.jpaproject;

import com.vincenzoracca.jpaproject.config.JpaConfig;
import com.vincenzoracca.jpaproject.daos.CompanyDao;
import com.vincenzoracca.jpaproject.daos.impl.CompanyDaoImpl;
import com.vincenzoracca.jpaproject.entities.Car;
import com.vincenzoracca.jpaproject.entities.Company;
import com.vincenzoracca.jpaproject.entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.*;

@ContextConfiguration(classes = JpaConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JpaFetchTest {

    @Autowired
    private CompanyDao companyDao;

//    @Before
//    public void init() {
//        System.out.println("\nSTART INITIAL LOAD");
//        System.out.println("\n*************************************************");
//        for(int i = 0; i < 1000; i++) {
//            Set<User> users = new HashSet<>();
//            Company company = new Company("COMPANY" + i, "NAME" + i, null);
//            for(int j = 0; j < 500; j++) {
//                Car car = new Car(UUID.randomUUID().toString(), "FIAT" + i + "-" + j);
//                Car carTwo = new Car(UUID.randomUUID().toString(), "TOYOTA" + i + "-" + j);
//                User user = new User("name" + i + "-" + j, "surname" + i + "-" + j, "code" + i + "-" + j, company,
//                        Collections.singleton(car));
//                car.setUser(user); carTwo.setUser(user);
//                users.add(user);
//            }
//            company.setUsers(users);
//            companyDao.save(company);
//        }
//        Assert.assertEquals(1000, companyDao.count());
//        System.out.println("\nEND INITIAL LOAD");
//        System.out.println("\n*************************************************");
//    }

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
        Assert.assertNotNull(entity);
        System.out.println("\nSTOP FIND BY ID");
        System.out.println("\n*************************************************");
    }

    @Test
    public void findByIdWithUsersTest() {
        System.out.println("\nSTART FIND BY ID WITH USERS");
        System.out.println("\n*************************************************");
        Company entity = companyDao.findByIdWithUsers("COMPANY1");
        Assert.assertNotNull(entity);
        System.out.println("STAMPA" + entity.getUsers());
        System.out.println("\nSTOP FIND BY WITH USERS");
        System.out.println("\n*************************************************");
    }

    @Test
    public void findByIdWithUsersFetchTest() {
        System.out.println("\nSTART FIND BY ID WITH USERS FETCH");
        System.out.println("\n*************************************************");
        Company entity = companyDao.findByIdWithUsersWitchFetch("COMPANY1");
        Assert.assertNotNull(entity);
        System.out.println("STAMPA" + entity.getUsers());
        System.out.println("\nSTOP FIND BY WITH USERS FETCH");
        System.out.println("\n*************************************************");
    }

    @Test
    public void findByIdWithUsersGraphTest() {
        System.out.println("\nSTART FIND BY ID WITH USERS GRAPH");
        System.out.println("\n*************************************************");
        Company entity = companyDao.findById("COMPANY1", Company.CompanyFetch.USERS);
        Assert.assertNotNull(entity);
        System.out.println("STAMPA" + entity.getUsers());
        System.out.println("\nSTOP FIND BY WITH USERS GRAPH");
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
