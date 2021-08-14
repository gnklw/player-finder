package com.example.football.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatSeedDto {

    @XmlElement
    private Double passing;

    @XmlElement
    private Double shooting;

    @XmlElement
    private Double endurance;

    @Min(1)
    @NotNull
    public Double getPassing() {
        return this.passing;
    }

    public void setPassing(Double passing) {
        this.passing = passing;
    }

    @Min(1)
    @NotNull
    public Double getShooting() {
        return this.shooting;
    }

    public void setShooting(Double shooting) {
        this.shooting = shooting;
    }

    @Min(1)
    @NotNull
    public Double getEndurance() {
        return this.endurance;
    }

    public void setEndurance(Double endurance) {
        this.endurance = endurance;
    }
}
