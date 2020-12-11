package com.vincenzoracca.jpaproject;


import com.vincenzoracca.jpaproject.config.JpaConfig;
import com.vincenzoracca.jpaproject.daos.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {


    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        UserDao bean = ctx.getBean(UserDao.class);
        System.out.println(bean);
    }


}
