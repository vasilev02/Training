package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.xml.CharacterRootDto;
import softuni.library.models.dto.xml.CharacterXmlDto;
import softuni.library.models.entities.Book;
import softuni.library.models.entities.Character;
import softuni.library.repositories.BookRepository;
import softuni.library.repositories.CharacterRepository;
import softuni.library.services.CharacterService;
import softuni.library.util.ValidationUtil;
import softuni.library.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final static String CHARACTERS_PATH = "src/main/resources/files/xml/characters.xml";

    private final CharacterRepository characterRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final BookRepository bookRepository;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, BookRepository bookRepository) {
        this.characterRepository = characterRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean areImported() {
        return this.characterRepository.count() > 0;
    }

    @Override
    public String readCharactersFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(CHARACTERS_PATH)));
    }

    @Override
    public String importCharacters() throws JAXBException {

        StringBuilder sb = new StringBuilder();

        CharacterRootDto data = this.xmlParser.parseXml(CharacterRootDto.class, CHARACTERS_PATH);

        for (CharacterXmlDto current : data.getCharacterXmlDtos()) {


            if (validationUtil.isValid(current)) {

                Character character = this.modelMapper.map(current, Character.class);

                Book book = this.bookRepository.findBookById(current.getBook().getId());

                this.characterRepository.saveAndFlush(character);

                String fullName = character.getFirstName() + " " + character.getMiddleName() + " " + character.getLastName();
                sb.append(String.format("Successfully imported Character: %s - age: %s", fullName, character.getAge()));

            } else {
                sb.append("Invalid Character");
            }
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();


    }

    @Override
    public String findCharactersInBookOrderedByLastNameDescendingThenByAge() {

        StringBuilder sb = new StringBuilder();

        List<Character> data = this.characterRepository.getCharacters();

        for (Character current : data) {

            String fullName = current.getFirstName() + " " + current.getMiddleName() + " " + current.getLastName();
            sb.append(String.format("Character name %s, age %s, in book %s",fullName,current.getAge(),current.getBook().getName()));
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
