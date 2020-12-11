package com.vincenzoracca.jpaproject;

import com.vincenzoracca.jpaproject.daos.ArticleDao;
import com.vincenzoracca.jpaproject.daos.AstrattaDao;
import com.vincenzoracca.jpaproject.daos.CarDao;
import com.vincenzoracca.jpaproject.daos.UserDao;
import com.vincenzoracca.jpaproject.daos.impl.ArticleDaoImpl;
import com.vincenzoracca.jpaproject.daos.impl.AstrattaDaoImpl;
import com.vincenzoracca.jpaproject.daos.impl.CarDaoImpl;
import com.vincenzoracca.jpaproject.daos.impl.UserDaoImpl;
import com.vincenzoracca.jpaproject.entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


public class JpaRelationsTest {

    private UserDao userDao;
    private CarDao carDao;
    private ArticleDao articleDao;
    private AstrattaDao astrattaDao;

    @Before
    public void init() {
        userDao = UserDaoImpl.getInstance();
        carDao = CarDaoImpl.getInstance();
        articleDao = ArticleDaoImpl.getInstance();
        astrattaDao = AstrattaDaoImpl.getInstance();
        System.out.println("\n*************************************************");
    }

    @After
    public void destroy(){
        System.out.println("*************************************************\n");
        System.out.println("BEGIN destroy");
        astrattaDao.clear();
        articleDao.clear();
        carDao.clear();
        userDao.clear();
        System.out.println("END destroy\n");
    }

    @Test
    public void testa() {
        AstrattaEntity concretaEntity = new ConcretaEntity();
        concretaEntity.setId(1L);
        ((ConcretaEntity) concretaEntity).setName("ENZO");
        astrattaDao.save(concretaEntity);

        Collection<AstrattaEntity> all = astrattaDao.findAll();
        System.out.println(all);
    }

    @Test
    public void oneToManyTest() {
        System.out.println("BEGIN oneToManyTest");
        assertEquals(0, userDao.findAll().size());
        UserEntity userEntity = createUser();
        CarEntity carEntity = createCar();
        userEntity.addCar(carEntity);

        userEntity = userDao.save(userEntity);
        carEntity = carDao.save(carEntity);

        CarEntity retrievedCar = carDao.findById(carEntity.getId());
        System.out.println(retrievedCar);
        assertEquals(carEntity, retrievedCar);
        assertEquals(userEntity, retrievedCar.getUserEntity());
        System.out.println("END oneToManyTest");
    }

    @Test
    public void oneToOneTest() {
        System.out.println("BEGIN oneToOneTest");
        assertEquals(0, userDao.findAll().size());
        UserEntity userEntity = createUser();
        ContactEntity contactEntity = createContact(userEntity);

        userDao.save(userEntity);
        UserEntity retrieved = userDao.findById(userEntity.getId());
        System.out.println(retrieved);
        assertEquals(userEntity, retrieved);
        assertEquals(contactEntity, userEntity.getContactEntity());
        System.out.println("END oneToOneTest");
    }

    @Test
    public void manyToManyTest() {
        System.out.println("BEGIN manyToManyTest");
        assertEquals(0, userDao.findAll().size());
        UserEntity userEntity = createUser();
        UserEntity anotherUser = createAnotherUser();
        ArticleEntity articleEntity = createArticle(userEntity, anotherUser);
        userDao.save(anotherUser);
        userDao.save(userEntity);
        articleDao.save(articleEntity);

        ArticleEntity retrieved = articleDao.findById(articleEntity.getId());
        System.out.println(retrieved);
        assertEquals(articleEntity, retrieved);
        System.out.println("END manyToManyTest");
    }

    private UserEntity createUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setCode("1");
        userEntity.setName("Vincenzo");
        userEntity.setSurname("Racca");
        return userEntity;
    }

    private CarEntity createCar() {
        CarEntity carEntity = new CarEntity();
        carEntity.setName("Clio");
        carEntity.setCompany("Renault");
        return carEntity;
    }

    private ContactEntity createContact(UserEntity userEntity) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setCity("Naples");
        contactEntity.setTelephoneNumber("333333333");
        contactEntity.setUserEntity(userEntity);
        userEntity.setContactEntity(contactEntity);
        return contactEntity;
    }

    private UserEntity createAnotherUser() {
        UserEntity anotherUser = new UserEntity();
        anotherUser.setName("Mario");
        anotherUser.setSurname("Rossi");
        return anotherUser;
    }

    private ArticleEntity createArticle(UserEntity... users) {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle("BLOG");
        Arrays.stream(users).forEach(userEntity -> userEntity.addArticle(articleEntity));
        return articleEntity;
    }

}
