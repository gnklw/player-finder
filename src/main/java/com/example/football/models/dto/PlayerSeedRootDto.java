package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedRootDto {

    @XmlElement(name = "player")
    private Collection<PlayerSeedDto> players;

    public Collection<PlayerSeedDto> getPlayers() {
        return players;
    }

    public void setPlayers(Collection<PlayerSeedDto> players) {
        this.players = players;
    }
}
