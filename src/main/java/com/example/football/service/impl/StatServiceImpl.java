package com.example.football.service.impl;

import com.example.football.models.dto.StatsSeedRootDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class StatServiceImpl implements StatService {

    private static final String FILE_PATH = "src/main/resources/files/xml/stats.xml";

    private final StatRepository statRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    public StatServiceImpl(StatRepository statRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.statRepository = statRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {
        var sb = new StringBuilder();

        xmlParser
                .fromFile(FILE_PATH, StatsSeedRootDto.class)
                .getSeeds()
                .stream()
                .filter(e -> {
                    boolean isValid = validationUtil.isValid(e)
                            && this.statRepository.findStatByEnduranceAndPassingAndShooting(
                                    e.getEndurance(), e.getPassing(), e.getShooting())
                            .isEmpty();
                    sb
                            .append(isValid
                            ? String.format("Successfully imported Stat - %.2f - %.2f - %.2f",
                            e.getPassing(), e.getShooting(), e.getEndurance())
                            : "Invalid stats")
                            .append(System.lineSeparator());

                    return isValid;
                })
                .map(e -> modelMapper.map(e, Stat.class))
                .forEach(this.statRepository::save);

        return sb.toString().trim();
    }

    @Override
    public Stat findStatById(Long id) {
        return this.statRepository.findById(id).orElse(null);
    }
}
