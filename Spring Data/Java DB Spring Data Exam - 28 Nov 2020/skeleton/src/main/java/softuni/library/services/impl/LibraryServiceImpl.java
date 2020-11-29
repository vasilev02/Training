package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.xml.CharacterRootDto;
import softuni.library.models.dto.xml.CharacterXmlDto;
import softuni.library.models.dto.xml.LibraryRootDto;
import softuni.library.models.dto.xml.LibraryXmlDto;
import softuni.library.models.entities.Book;
import softuni.library.models.entities.Character;
import softuni.library.models.entities.Library;
import softuni.library.repositories.BookRepository;
import softuni.library.repositories.LibraryRepository;
import softuni.library.services.LibraryService;
import softuni.library.util.ValidationUtil;
import softuni.library.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final static String LIBRARIES_PATH = "src/main/resources/files/xml/libraries.xml";

    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtil validationUtil, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean areImported() {
        return this.libraryRepository.count() > 0;
    }

    @Override
    public String readLibrariesFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(LIBRARIES_PATH)));
    }

    @Override
    public String importLibraries() throws JAXBException {


        StringBuilder sb = new StringBuilder();

        LibraryRootDto data = this.xmlParser.parseXml(LibraryRootDto.class, LIBRARIES_PATH);

        for (LibraryXmlDto current : data.getLibraryXmlDtos()) {

            Book findBook = this.bookRepository.findBookById(current.getBook().getId());


            if (validationUtil.isValid(current) && findBook != null) {

                Library library = this.libraryRepository.findLibraryByName(current.getName());

                if (library == null) {

                    Library libraryToInsert = this.modelMapper.map(current, Library.class);

                    this.libraryRepository.saveAndFlush(libraryToInsert);

                    Library newlibrary = this.libraryRepository.findLibraryByName(current.getName());

                    newlibrary.getBooks().add(findBook);

                    this.libraryRepository.saveAndFlush(newlibrary);


                    sb.append(String.format("Successfully added Library: %s - %s", libraryToInsert.getName(), libraryToInsert.getLocation()));

                    sb.append(System.lineSeparator());
                }else{

                    library.getBooks().add(findBook);

                    this.libraryRepository.saveAndFlush(library);

                }


            } else {
                sb.append("Invalid Library").append(System.lineSeparator());
            }


        }

        return sb.toString().trim();


    }
}
