package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.ArticleDao;
import com.vincenzoracca.jpaproject.entities.ArticleEntity;
import com.vincenzoracca.jpaproject.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class ArticleDaoImpl extends JpaDaoImpl<ArticleEntity, Long> implements ArticleDao {


    public Collection<UserEntity> findAuthorsById(Long articleId) {
        ArticleEntity articleEntity = findById(articleId);
        return articleEntity.getAuthors();
    }
}
