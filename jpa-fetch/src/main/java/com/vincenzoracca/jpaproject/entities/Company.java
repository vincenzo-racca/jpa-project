package com.vincenzoracca.jpaproject.entities;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "COMPANIES")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "Company.all", includeAllAttributes = true)
})
public class Company implements JpaEntity {

    @Id
    private String code;

    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @BatchSize(size = 50)
    private Set<User> users;

    public Company() {
    }

    public Company(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Company(String code, String name, Set<User> users) {
        this.code = code;
        this.name = name;
        this.users = users;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Company{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return code.equals(company.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
