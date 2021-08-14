package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stats")
public class Stat extends BaseEntity {

    private Double shooting;
    private Double passing;
    private Double endurance;

    public Stat() {
    }

    @Column
    public Double getShooting() {
        return this.shooting;
    }

    public void setShooting(Double shooting) {
        this.shooting = shooting;
    }

    @Column
    public Double getPassing() {
        return this.passing;
    }

    public void setPassing(Double passing) {
        this.passing = passing;
    }

    @Column
    public Double getEndurance() {
        return this.endurance;
    }

    public void setEndurance(Double endurance) {
        this.endurance = endurance;
    }
}
