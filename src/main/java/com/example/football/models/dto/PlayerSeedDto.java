package com.example.football.models.dto;

import com.example.football.models.entity.enums.Position;
import com.example.football.config.LocalDateAdapter;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedDto {

    @XmlElement(name = "first-name")
    private String firstName;

    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement
    private String email;

//    @XmlElement(name = "birth-date")
//    @XmlJavaTypeAdapter(LocalDateAdapter.class)
//    private LocalDate birthDate;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement
    private Position position;

    @XmlElement
    private TownNameSeedDto town;

    @XmlElement
    private TeamNameSeedDto team;

    @XmlElement
    private StatIdSeedDto stat;

    @Size(min = 3)
    @NotNull
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 3)
    @NotNull
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    @NotNull
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public void setBirthDate(LocalDate birthDate) {
//        this.birthDate = birthDate;
//    }

    @NotNull
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @NotNull
    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @NotNull
    public TownNameSeedDto getTown() {
        return town;
    }

    public void setTown(TownNameSeedDto town) {
        this.town = town;
    }

    @NotNull
    public TeamNameSeedDto getTeam() {
        return team;
    }

    public void setTeam(TeamNameSeedDto team) {
        this.team = team;
    }

    @NotNull
    public StatIdSeedDto getStat() {
        return stat;
    }

    public void setStat(StatIdSeedDto stat) {
        this.stat = stat;
    }
}
