package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.jsonDto.PictureDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@Service
public class PictureServiceImpl implements PictureService {

    private final static String PICTURES_PATH = "src/main/resources/files/pictures.json";

    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PICTURES_PATH)));
    }

    @Override
    public String importPictures() throws IOException {

        StringBuilder sb = new StringBuilder();

        PictureDto[] data = this.gson.fromJson(new FileReader(PICTURES_PATH), PictureDto[].class);

        for (PictureDto current : data) {

            Picture findPicture = this.pictureRepository.findPictureByPathEquals(current.getPath());

            if (validationUtil.isValid(current) && findPicture == null) {

                Picture picture = this.modelMapper.map(current, Picture.class);

                this.pictureRepository.saveAndFlush(picture);

                sb.append(String.format("Successfully imported Picture, with size %.2f", picture.getSize()));

            } else {
                sb.append("Invalid Picture");
            }
            sb.append(System.lineSeparator());

        }
        return sb.toString().trim();
    }

    @Override
    public String exportPictures() {

        StringBuilder sb = new StringBuilder();

        Set<Picture> data = this.pictureRepository.getPicturesExport();

        for (Picture current : data) {

            sb.append(String.format("%.2f – %s",current.getSize(),current.getPath()));
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
