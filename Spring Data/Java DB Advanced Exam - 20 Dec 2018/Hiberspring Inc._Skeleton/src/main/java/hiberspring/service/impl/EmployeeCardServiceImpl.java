package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.GlobalConstants;
import hiberspring.domain.dto.json.EmployeeCardDto;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.EmployeeCardService;
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
public class EmployeeCardServiceImpl implements EmployeeCardService {

    private final EmployeeCardRepository employeeCardRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final TownRepository townRepository;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, TownRepository townRepository) {
        this.employeeCardRepository = employeeCardRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
    }

    @Override
    public Boolean employeeCardsAreImported() {
        return this.employeeCardRepository.count() > 0;
    }

    @Override
    public String readEmployeeCardsJsonFile() throws IOException {
        Path path = Paths.get(GlobalConstants.EMPLOYEE_CARDS_FILE_PATH);
        return Files.readAllLines(path).toString();
    }

    @Override
    public String importEmployeeCards(String employeeCardsFileContent) throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        EmployeeCardDto[] data = this.gson.fromJson(new FileReader(GlobalConstants.EMPLOYEE_CARDS_FILE_PATH), EmployeeCardDto[].class);

        for (EmployeeCardDto current : data) {

            EmployeeCard findCard = this.employeeCardRepository.findEmployeeCardByNumber(current.getNumber());

            if (validationUtil.isValid(current) && findCard == null){

                EmployeeCard card = this.modelMapper.map(current,EmployeeCard.class);

                this.employeeCardRepository.saveAndFlush(card);

                sb.append(String.format(GlobalConstants.SUCCESSFUL_IMPORT_MESSAGE,"Card", card.getNumber()));

            }else{
                sb.append(GlobalConstants.INCORRECT_DATA_MESSAGE);
            }
            sb.append(System.lineSeparator());
        }


        return sb.toString().trim();
    }
}
