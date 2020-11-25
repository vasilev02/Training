package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.PassengerDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final static String PASSENGERS_PATH = "src/main/resources/files/json/passengers.json";

    private final PassengerRepository passengerRepository;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper, TownRepository townRepository) {
        this.passengerRepository = passengerRepository;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {

        Path path = Paths.get(PASSENGERS_PATH);
        return Files.readAllLines(path).toString();
    }

    @Override
    public String importPassengers() throws IOException {

        StringBuilder sb = new StringBuilder();

        PassengerDto[] data = this.gson.fromJson(new FileReader(PASSENGERS_PATH), PassengerDto[].class);

        for (PassengerDto current : data) {

            if(validationUtil.isValid(current)){

                Passenger passenger = this.modelMapper.map(current,Passenger.class);

                Town town = this.townRepository.findByName(current.getTown());

                if(town!=null){
                    passenger.setTown(town);
                }

                sb.append(String.format("Successfully imported Passenger %s - %s",passenger.getLastName(), passenger.getPhoneNumber()));

                this.passengerRepository.saveAndFlush(passenger);

            }else{

                sb.append("Invalid passenger");

            }
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {

        StringBuilder sb = new StringBuilder();

        List<Passenger> data = this.passengerRepository.findPassengerByTicketsSize();

        for (Passenger current : data) {

            sb.append(String.format("Passenger %s %s",current.getFirstName(),current.getLastName()));
            sb.append(System.lineSeparator());

            sb.append(String.format("Email - %s",current.getEmail()));
            sb.append(System.lineSeparator());

            sb.append(String.format("Phone - %s",current.getPhoneNumber()));
            sb.append(System.lineSeparator());

            sb.append(String.format("Number of tickets - %d",current.getTickets().size()));
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}
