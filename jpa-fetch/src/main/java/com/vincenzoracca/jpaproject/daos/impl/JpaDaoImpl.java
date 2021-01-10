package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.JpaDao;
import com.vincenzoracca.jpaproject.entities.JpaEntity;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;


public abstract class JpaDaoImpl<T extends JpaEntity, ID> implements JpaDao<T, ID> {


    private Class<T> persistentClass;

    private final String FIND_ALL;

    @PersistenceContext
    protected EntityManager entityManager;


    public JpaDaoImpl() {

        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];

        FIND_ALL = "select e from " + persistentClass.getSimpleName() + " e";
    }

    @Override
    public T findById(ID id) {
        T entity = entityManager.find(persistentClass, id);
        return entity;
    }

    @Override
    public T findById(ID id, String entityGraph) {
        T entity = entityManager.createQuery(FIND_ALL + " where " + idField() + " = :id", persistentClass)
                                .setParameter("id", id)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph(entityGraph))
                .getSingleResult();
        return entity;
    }

    @Override
    public Collection<T> findAll() {
        Collection<T> entities = entityManager.createQuery(FIND_ALL, persistentClass).getResultList();
        return entities;
    }


    @Override
    public Collection<T> findAllEager() {
        Collection<T> entities = entityManager.createQuery(FIND_ALL, persistentClass)
                .setHint("javax.persistence.fetchgraph", entityManager.getEntityGraph(persistentClass.getSimpleName() + ".all"))
                .getResultList();
        entityManager.close();
        return entities;
    }

    @Transactional
    @Override
    public T save(T entity) {
        if(getterId(entity) == null) {
            entityManager.persist(entity);
        }
        else {
            entityManager.merge(entity);
        }
        return entity;
    }

    @Transactional
    @Override
    public void delete(T entity) {
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
    }

    @Override
    public long count() {
        return entityManager.createQuery("select count(e) FROM " + persistentClass.getSimpleName() + " e", Long.class)
                .getSingleResult();
    }

    //use a forEach loop and not a delete query because JPA not use the cascade in the query
    @Transactional
    @Override
    public void clear() {
        Collection<T> all = findAll();
        all.forEach(this::delete);
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

    private String idField() {
        try {
            String id = Arrays.stream(persistentClass.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Id.class))
                    .findAny()
                    .map(field -> field.getName())
                    .orElse(null);

            return id;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
