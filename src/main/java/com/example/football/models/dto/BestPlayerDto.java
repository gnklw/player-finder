package com.example.football.models.dto;

import com.example.football.models.entity.enums.Position;

public class BestPlayerDto {

    private String firstName;
    private String lastName;
    private Position position;
    private String teamName;
    private String teamStadiumName;

    public BestPlayerDto(String firstName, String lastName, Position position, String teamName, String teamStadiumName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.teamName = teamName;
        this.teamStadiumName = teamStadiumName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamStadiumName() {
        return teamStadiumName;
    }

    public void setTeamStadiumName(String teamStadiumName) {
        this.teamStadiumName = teamStadiumName;
    }
}
