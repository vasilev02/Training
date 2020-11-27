package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dto.xml.PictureDto;
import softuni.exam.domain.dto.xml.PictureRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class PictureServiceImpl implements PictureService {

    private final static String PICTURES_PATH = "src/main/resources/files/xml/pictures.xml";

    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
    }

    @Override
    public String importPictures() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        PictureRootDto data = this.xmlParser.importFromXml(PictureRootDto.class, PICTURES_PATH);

        for (PictureDto pictureDto : data.getPictureDtos()) {

            Picture picture = this.pictureRepository.findByUrl(pictureDto.getUrl());

            if (validationUtil.isValid(pictureDto) && picture == null) {

                Picture findPicture = this.modelMapper.map(pictureDto, Picture.class);

                this.pictureRepository.saveAndFlush(findPicture);

                sb.append("Successfully imported picture");

            } else {

                sb.append("Invalid picture");

            }

            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PICTURES_PATH)));
    }


}
