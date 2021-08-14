package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatsSeedRootDto {

    @XmlElement(name = "stat")
    private Collection<StatSeedDto> seeds;

    public Collection<StatSeedDto> getSeeds() {
        return seeds;
    }

    public void setSeeds(Collection<StatSeedDto> seeds) {
        this.seeds = seeds;
    }
}
