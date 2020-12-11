package com.vincenzoracca.jpaproject.daos;


import com.vincenzoracca.jpaproject.entities.ArticleEntity;
import com.vincenzoracca.jpaproject.entities.UserEntity;

import java.util.Collection;

public interface ArticleDao extends JpaDao<ArticleEntity, Long> {

    Collection<UserEntity> findAuthorsById(Long articleId);
}
