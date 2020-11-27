package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.jsonImports.CarImportDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.util.ValidationUtil;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class CarServiceImpl implements CarService {

    private final static String CARS_PATH = "src/main/resources/files/json/cars.json";

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(CARS_PATH)));
    }

    @Override
    public String importCars() throws IOException {

        StringBuilder sb = new StringBuilder();

        CarImportDto[] data = this.gson.fromJson(this.readCarsFileContent(), CarImportDto[].class);

        for (CarImportDto current : data) {

            if (this.validationUtil.isValid(current)){

                Car car = this.modelMapper.map(current, Car.class);

                this.carRepository.saveAndFlush(car);

                sb.append(String.format("Successfully imported car - %s - %s",car.getMake(),car.getModel()));

            }else {

                sb.append("Invalid car");

            }
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {

        StringBuilder sb = new StringBuilder();

        Set<Car> cars = this.carRepository.getCars();


        for (Car car : cars) {

            sb.append(String.format("Car make - %s, model - %s",car.getMake(),car.getModel()));
            sb.append(System.lineSeparator());

            sb.append(String.format("\tKilometers - %s",car.getKilometers()));
            sb.append(System.lineSeparator());

            sb.append(String.format("\tRegistered on - %s",car.getRegisteredOn()));
            sb.append(System.lineSeparator());

            sb.append(String.format("\tNumber of pictures - %d",car.getPictures().size()));
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}
