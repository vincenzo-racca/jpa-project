package com.vincenzoracca.jpaproject.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ARTICLES")
public class ArticleEntity implements JpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;

    @ManyToMany
    @JoinTable(name = "AUTHOR_ARTICLES", joinColumns = {
            @JoinColumn(name = "article_id")
    }, inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<UserEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<UserEntity> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleEntity that = (ArticleEntity) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
