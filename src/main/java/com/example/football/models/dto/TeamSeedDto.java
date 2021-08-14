package com.example.football.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TeamSeedDto {

    @Expose
    private String name;

    @Expose
    private String stadiumName;

    @Expose
    private Integer fanBase;

    @Expose
    private String history;

    @Expose
    private String townName;

    @Size(min = 3)
    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(min = 3)
    @NotNull
    public String getStadiumName() {
        return this.stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    @Min(1000)
    @NotNull
    public Integer getFanBase() {
        return this.fanBase;
    }

    public void setFanBase(Integer fanBase) {
        this.fanBase = fanBase;
    }

    @Size(min = 10)
    @NotNull
    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @Size(min = 2)
    @NotNull
    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
