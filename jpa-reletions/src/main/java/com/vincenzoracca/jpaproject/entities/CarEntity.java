package com.vincenzoracca.jpaproject.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CARS")
public class CarEntity implements JpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    private String company;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", userEntity=" + userEntity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(company, carEntity.company) &&
                Objects.equals(name, carEntity.name) &&
                Objects.equals(userEntity, carEntity.userEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company, name, userEntity);
    }
}
