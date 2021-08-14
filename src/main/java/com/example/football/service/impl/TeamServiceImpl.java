package com.example.football.service.impl;

import com.example.football.models.dto.TeamSeedDto;
import com.example.football.models.entity.Team;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.service.TownService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TeamServiceImpl implements TeamService {

    private static final String FILE_PATH = "src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final TownService townService;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, Gson gson, TownService townService) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importTeams() throws IOException {
        var sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readTeamsFileContent(), TeamSeedDto[].class))
                .filter(e -> {
                    boolean isValid = this.validationUtil.isValid(e)
                            && this.teamRepository.findTeamByName(e.getName()).isEmpty();

                    sb.append(isValid
                            ? String.format("Successfully imported %s - %d", e.getName(), e.getFanBase())
                            : "Invalid Team")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(e -> {
                    Team team = this.modelMapper.map(e, Team.class);
                    team.setTown(this.townService.findTownByName(e.getTownName()));
                    return team;
                })
                .forEach(this.teamRepository::save);

        return sb.toString().trim();
    }

    @Override
    public Team findTeamByName(String name) {
        return this.teamRepository.findAll()
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
