package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.json.PassengerDto;
import softuni.exam.models.dto.xml.PlaneDto;
import softuni.exam.models.dto.xml.PlaneRootDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final static String PLANES_PATH = "src/main/resources/files/xml/planes.xml";

    private final PlaneRepository planeRepository;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository, ValidationUtil validationUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.planeRepository = planeRepository;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        Path path = Paths.get(PLANES_PATH);
        return Files.readAllLines(path).toString();
    }

    @Override
    public String importPlanes() throws JAXBException {

        StringBuilder sb = new StringBuilder();

        PlaneRootDto data = this.xmlParser.parseXml(PlaneRootDto.class, PLANES_PATH);

        for (PlaneDto current : data.getPlaneDtos()) {

            if(validationUtil.isValid(current)){

                Plane plane = this.modelMapper.map(current,Plane.class);

                this.planeRepository.saveAndFlush(plane);

                sb.append(String.format("Successfully imported Plane %s",plane.getRegisterNumber()));

            }else{

                sb.append("Invalid plane");

            }
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}
