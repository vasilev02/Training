package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.TownDto;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TownServiceImpl implements TownService {

    private final static String TOWNS_PATH = "src/main/resources/files/json/towns.json";

    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {

        Path path = Paths.get(TOWNS_PATH);
        return Files.readAllLines(path).toString();
    }

    @Override
    public String importTowns() throws IOException {

        StringBuilder sb = new StringBuilder();

        TownDto[] data = this.gson.fromJson(new FileReader(TOWNS_PATH), TownDto[].class);

        for (TownDto current : data) {

            if(validationUtil.isValid(current)){

                Town town = this.modelMapper.map(current,Town.class);

                this.townRepository.saveAndFlush(town);

                sb.append(String.format("Successfully imported Town %s - %s",town.getName(),town.getPopulation()));

            }else{

                sb.append("Invalid town");

            }
            sb.append(System.lineSeparator());

        }


        return sb.toString().trim();
    }
}
