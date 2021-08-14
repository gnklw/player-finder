package com.example.football.service.impl;

import com.example.football.models.dto.PlayerSeedRootDto;
import com.example.football.models.entity.Player;
import com.example.football.models.dto.BestPlayerDto;
import com.example.football.repository.PlayerRepository;
import com.example.football.service.PlayerService;
import com.example.football.service.StatService;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Service
public class PlayerServiceImpl implements PlayerService {

    private static final String FILE_PATH = "src/main/resources/files/xml/players.xml";
    private static final LocalDate START_DATE = LocalDate.parse("1995-01-01");
    private static final LocalDate END_DATE = LocalDate.parse("2003-01-01");

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final StatService statService;
    private final TeamService teamService;
    private final TownService townService;

    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, StatService statService, TeamService teamService, TownService townService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.statService = statService;
        this.teamService = teamService;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importPlayers() throws JAXBException, IOException {
        var sb = new StringBuilder();

        xmlParser
                .fromFile(FILE_PATH, PlayerSeedRootDto.class)
                .getPlayers()
                .stream()
                .filter(e -> {
                    boolean isValid = validationUtil.isValid(e)
                            && this.playerRepository.findPlayerByEmail(e.getEmail()).isEmpty();

                    sb.append(isValid
                            ? String.format("Successfully imported Player %s %s - %s",
                            e.getFirstName(), e.getLastName(), e.getPosition().name())
                            : "Invalid player")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(e -> {
                    var player = modelMapper.map(e, Player.class);

                    player.setStat(this.statService.findStatById(e.getStat().getId()));
                    player.setTeam(this.teamService.findTeamByName(e.getTeam().getName()));
                    player.setTown(this.townService.findTownByName(e.getTown().getName()));

                    return player;
                })
                .forEach(this.playerRepository::save);

        return sb.toString().trim();
    }

    @Override
    public String exportBestPlayers() {
        var bestPlayers =
                this.playerRepository.findPlayersOrderByShootingPassingEnduranceAndLastName(START_DATE, END_DATE);
        var sb = new StringBuilder();

        bestPlayers
                .forEach(e -> sb.append(String.format("Player - %s %s", e.getFirstName(), e.getLastName()))
                        .append(System.lineSeparator()).append("Position - ").append(e.getPosition().name())
                        .append(System.lineSeparator()).append("Team - ").append(e.getTeamName())
                        .append(System.lineSeparator()).append("Stadium - ").append(e.getTeamStadiumName())
                        .append(System.lineSeparator()));

        return sb.toString().trim();
    }
}
