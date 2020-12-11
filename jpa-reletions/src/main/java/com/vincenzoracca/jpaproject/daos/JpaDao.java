package com.vincenzoracca.jpaproject.daos;


import com.vincenzoracca.jpaproject.entities.JpaEntity;

import java.util.Collection;

public interface JpaDao<T extends JpaEntity, ID> {

    T findById(ID id);

    Collection<T> findAll();

    T save(T entity);

    void delete(T entity);

    void clear();
}
