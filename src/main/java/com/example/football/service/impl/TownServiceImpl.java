package com.example.football.service.impl;

import com.example.football.models.dto.TownSeedDto;
import com.example.football.models.entity.Town;
import com.example.football.repository.TownRepository;
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
public class TownServiceImpl implements TownService {

    private static final String FILE_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        var sb = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readTownsFileContent(), TownSeedDto[].class))
                .filter(e -> {
                    boolean isValid = this.validationUtil.isValid(e)
                            && this.townRepository.findTownByName(e.getName()).isEmpty();

                    sb.append(isValid
                            ? String.format("Successfully imported Town %s - %d", e.getName(), e.getPopulation())
                            : "Invalid Town")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(e -> this.modelMapper.map(e, Town.class))
                .forEach(this.townRepository::save);

        return sb.toString().trim();
    }

    @Override
    public Town findTownByName(String name) {
        return this.townRepository.findAll()
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
