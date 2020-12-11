package com.vincenzoracca.jpaproject.daos.impl;

import com.vincenzoracca.jpaproject.daos.ArticleDao;
import com.vincenzoracca.jpaproject.entities.ArticleEntity;
import com.vincenzoracca.jpaproject.entities.UserEntity;

import java.util.Collection;

public class ArticleDaoImpl extends JpaDaoImpl<ArticleEntity, Long> implements ArticleDao {

    private static ArticleDao articleDao;

    private ArticleDaoImpl() {}

    public static ArticleDao getInstance(){
        if(articleDao == null) {
            articleDao = new ArticleDaoImpl();
        }
        return articleDao;
    }

    public Collection<UserEntity> findAuthorsById(Long articleId) {
        ArticleEntity articleEntity = findById(articleId);
        return articleEntity.getAuthors();
    }
}
