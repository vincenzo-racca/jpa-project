package com.vincenzoracca.jpaproject.entities;

import javax.persistence.*;

//@Entity
//@Table(name = "AUTHOR_ARTICLES")
//public class AuthorArticlesEntity implements JpaEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserEntity userEntity;
//
//    @ManyToOne
//    @JoinColumn(name = "article_id")
//    private ArticleEntity articleEntity;
//
//
//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public UserEntity getUserEntity() {
//        return userEntity;
//    }
//
//    public void setUserEntity(UserEntity userEntity) {
//        this.userEntity = userEntity;
//    }
//
//    public ArticleEntity getArticleEntity() {
//        return articleEntity;
//    }
//
//    public void setArticleEntity(ArticleEntity articleEntity) {
//        this.articleEntity = articleEntity;
//    }
//}
