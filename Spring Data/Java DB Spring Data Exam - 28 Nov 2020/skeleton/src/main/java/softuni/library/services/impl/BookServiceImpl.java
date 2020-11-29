package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.library.models.dto.json.BookDto;
import softuni.library.models.entities.Author;
import softuni.library.models.entities.Book;
import softuni.library.repositories.AuthorRepository;
import softuni.library.repositories.BookRepository;
import softuni.library.services.BookService;
import softuni.library.util.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class BookServiceImpl implements BookService {

    private final static String BOOKS_PATH = "src/main/resources/files/json/books.json";

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.authorRepository = authorRepository;
    }


    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(BOOKS_PATH)));
    }

    @Override
    public String importBooks() throws FileNotFoundException {

        StringBuilder sb = new StringBuilder();

        BookDto[] data = this.gson.fromJson(new FileReader(BOOKS_PATH), BookDto[].class);

        for (BookDto current : data) {

            Author author = this.authorRepository.findAuthorById(current.getAuthor());

            if(validationUtil.isValid(current) && author != null){

                Book book = this.modelMapper.map(current,Book.class);

                book.setAuthor(author);

                this.bookRepository.saveAndFlush(book);

                sb.append(String.format("Successfully imported Book: %s written in %s",book.getName(),book.getWritten()));

            }else{
                sb.append("Invalid Book");
            }
            sb.append(System.lineSeparator());

        }

        return sb.toString().trim();


    }
}
