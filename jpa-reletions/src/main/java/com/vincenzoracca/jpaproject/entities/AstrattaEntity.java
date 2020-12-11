package com.vincenzoracca.jpaproject.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class AstrattaEntity implements JpaEntity {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
