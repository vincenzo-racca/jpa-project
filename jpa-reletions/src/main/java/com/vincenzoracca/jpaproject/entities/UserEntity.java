package com.vincenzoracca.jpaproject.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class UserEntity implements JpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String surname;

    private String code;

    @OneToMany(mappedBy = "userEntity")
    private Set<CarEntity> cars;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL,orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private ContactEntity contactEntity;

    @ManyToMany(mappedBy = "authors")
    private Set<ArticleEntity> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Set<CarEntity> cars) {
        this.cars = cars;
    }

    public ContactEntity getContactEntity() {
        return contactEntity;
    }

    public void setContactEntity(ContactEntity contactEntity) {
        this.contactEntity = contactEntity;
    }

    public Set<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(Set<ArticleEntity> articles) {
        this.articles = articles;
    }

    public void addCar(CarEntity carEntity) {
        carEntity.setUserEntity(this);
        if(this.cars == null) {
            this.cars = new HashSet<>();
        }
        this.cars.add(carEntity);
    }

    public void removeCar(CarEntity carEntity) {
        this.cars.remove(carEntity);
        carEntity.setUserEntity(null);
    }

    public void addArticle(ArticleEntity articleEntity) {
        if(articleEntity.getAuthors() == null) {
            articleEntity.setAuthors(new HashSet<>());
        }

        if(this.articles == null) {
            this.articles = new HashSet<>();
        }

        articleEntity.getAuthors().add(this);
        this.articles.add(articleEntity);
    }

    public void removeArticle(ArticleEntity articleEntity) {
        this.articles.remove(articleEntity);
        articleEntity.getAuthors().remove(this);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", code='" + code + '\'' +
                ", contactEntity=" + contactEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, code);
    }
}
