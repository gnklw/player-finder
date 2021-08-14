package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    private String name;
    private Integer population;
    private String guide;

    public Town(){
    }

    @Column(unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public Integer getPopulation() {
        return this.population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Column(columnDefinition = "TEXT")
    public String getGuide() {
        return this.guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
