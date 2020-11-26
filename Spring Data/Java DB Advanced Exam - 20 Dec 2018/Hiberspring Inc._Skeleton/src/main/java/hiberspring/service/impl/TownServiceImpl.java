package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.json.TownDto;
import hiberspring.domain.entities.Town;
import hiberspring.repository.TownRepository;
import hiberspring.service.TownService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        Path path = Paths.get(GlobalConstants.TOWNS_FILE_PATH);
        return Files.readAllLines(path).toString();
    }

    @Override
    public String importTowns(String townsFileContent) throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        TownDto[] data = this.gson.fromJson(new FileReader(GlobalConstants.TOWNS_FILE_PATH), TownDto[].class);

        for (TownDto current : data) {

            if (validationUtil.isValid(current) && current.getPopulation() != 0) {

                Town town = this.modelMapper.map(current, Town.class);

                this.townRepository.saveAndFlush(town);

                sb.append(String.format(GlobalConstants.SUCCESSFUL_IMPORT_MESSAGE, "Town", town.getName()));

            } else {
                sb.append(GlobalConstants.INCORRECT_DATA_MESSAGE);
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
