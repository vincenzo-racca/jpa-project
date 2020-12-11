package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.JpaDao;
import com.vincenzoracca.jpaproject.entities.JpaEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;

public class JpaDaoImpl<T extends JpaEntity, ID> implements JpaDao<T, ID> {

    protected static EntityManagerFactory entityManagerFactory;

    private Class<T> persistentClass;

    private final String FIND_ALL;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("TEST_UNIT");
    }


    public JpaDaoImpl() {

        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        FIND_ALL = "select e from " + persistentClass.getSimpleName() + " e";
    }

    @Override
    public T findById(ID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        T entity = entityManager.find(persistentClass, id);
        entityManager.close();
        return entity;
    }

    @Override
    public Collection<T> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Collection<T> entities = entityManager.createQuery(FIND_ALL, persistentClass).getResultList();
        entityManager.close();
        return entities;
    }

    @Override
    public T save(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            if(getterId(entity) == null) {
                entityManager.persist(entity);
            }
            else {
                entityManager.merge(entity);
            }
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();

        return entity;
    }

    @Override
    public void delete(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();

    }

    //use a forEach loop and not a delete query because JPA not use the cascade in the query
    @Override
    public void clear() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Collection<T> all = findAll();
            all.forEach(this::delete);
            entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }

    //get the value of the field annotated with @Id
    private ID getterId(T entity) {
        try {
            Field id = Arrays.stream(persistentClass.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Id.class))
                    .findAny()
                    .orElse(null);

            id.setAccessible(true);

            return (ID) id.get(entity);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
