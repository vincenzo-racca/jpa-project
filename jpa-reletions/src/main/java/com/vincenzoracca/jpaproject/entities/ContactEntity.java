package com.vincenzoracca.jpaproject.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CONTACTS")
public class ContactEntity implements JpaEntity {

    @Id
    @Column(name = "user_id")
    private Long id;

    private String city;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private UserEntity userEntity;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "ContactEntity{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactEntity that = (ContactEntity) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(telephoneNumber, that.telephoneNumber) &&
                Objects.equals(userEntity, that.userEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, telephoneNumber, userEntity);
    }
}
