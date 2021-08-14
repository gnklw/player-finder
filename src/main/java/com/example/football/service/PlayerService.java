package com.example.football.service;

import com.example.football.models.entity.Player;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface PlayerService {
    boolean areImported();

    String readPlayersFileContent() throws IOException;

    String importPlayers() throws JAXBException, IOException;

    String exportBestPlayers();
}
