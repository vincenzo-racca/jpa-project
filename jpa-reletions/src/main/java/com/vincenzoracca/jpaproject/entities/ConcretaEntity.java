package com.vincenzoracca.jpaproject.entities;

import javax.persistence.Entity;

@Entity
public class ConcretaEntity extends AstrattaEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
