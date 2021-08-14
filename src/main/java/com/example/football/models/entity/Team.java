package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    private String name;
    private String stadiumName;
    private Integer fanBase;
    private String history;
    private Town town;

    public Team() {
    }

    @Column(unique = true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "stadium_name", unique = true)
    public String getStadiumName() {
        return this.stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Column(name = "fan_base")
    public Integer getFanBase() {
        return this.fanBase;
    }

    public void setFanBase(Integer fanBase) {
        this.fanBase = fanBase;
    }

    @Column(columnDefinition = "TEXT")
    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @ManyToOne
    public Town getTown() {
        return this.town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
