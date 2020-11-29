package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.json.AuthorDto;
import softuni.library.models.entities.Author;
import softuni.library.repositories.AuthorRepository;
import softuni.library.services.AuthorService;
import softuni.library.util.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_PATH = "src/main/resources/files/json/authors.json";

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public String readAuthorsFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(AUTHORS_PATH)));
    }

    @Override
    public String importAuthors() throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        AuthorDto[] data = this.gson.fromJson(new FileReader(AUTHORS_PATH), AuthorDto[].class);

        for (AuthorDto current : data) {

            Author findAuthor = this.authorRepository.findAuthorByFirstNameAndLastName(current.getFirstName(),current.getLastName());

            if(validationUtil.isValid(current) && findAuthor == null){

                Author author = this.modelMapper.map(current, Author.class);

                this.authorRepository.saveAndFlush(author);

                sb.append(String.format("Successfully imported Author: %s %s - %s",author.getFirstName(),author.getLastName(),author.getBirthTown()));

            }else{
                sb.append("Invalid Author");
            }
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();
    }
}
