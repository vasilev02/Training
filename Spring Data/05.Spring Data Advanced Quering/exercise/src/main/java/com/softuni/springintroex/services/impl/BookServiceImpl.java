package com.softuni.springintroex.services.impl;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.entities.*;
import com.softuni.springintroex.models.BookModel;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.repositories.CategoryRepository;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Transactional
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedBooksInDB() throws IOException {

        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] fileContent = this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);

        Arrays.stream(fileContent).forEach(entry -> {

            String[] data = entry.split(" ");

            Book book = new Book();

            book.setEditionType(EditionType.values()[Integer.parseInt(data[0])]);

            LocalDate date = this.makeFormattedDate(data[1]);
            book.setReleaseDate(date);

            book.setCopies(Integer.parseInt(data[2]));

            BigDecimal decimal = new BigDecimal(data[3]);

            book.setPrice(decimal);

            book.setAgeRestriction(AgeRestriction.values()[Integer.parseInt(data[4])]);

            book.setTitle(this.makeTitle(data));

            book.setAuthor(this.addRandomAuthor());


            book.setCategories(this.addCategories());

            this.bookRepository.saveAndFlush(book);

        });

    }

    @Override
    public Set<Book> findBooksByGivenAgeRestriction(String input) {

        AgeRestriction ageRestriction = AgeRestriction.valueOf(input.toUpperCase());

        return this.bookRepository.findBooksByAgeRestrictionLike(ageRestriction);

    }

    @Override
    public Set<Book> findGoldenBooksWithCopiesLessThan(int input) {

        return this.bookRepository.findBooksByCopiesLessThan(input);

    }

    @Override
    public Set<Book> findBooksBetweenTwoPrices(BigDecimal less, BigDecimal greater) {

        return this.bookRepository.findBooksByPriceLessThanOrPriceGreaterThan(less, greater);

    }

    @Override
    public Set<Book> findBooksWithTitleContainingGivenString(String input) {

        return this.bookRepository.findBooksContainingGivenStringInTitle(input);

    }

    @Override
    public Set<Book> findBooksWithAuthorWithLastNameContainingString(String input) {

        return this.bookRepository.findBooksByAuthorLastNameContainingString(input);

    }

    @Override
    public Set<Book> findBooksNotReleasedInYear(String input) {

        LocalDate after = LocalDate.of(Integer.parseInt(input), 12, 31);
        LocalDate before = LocalDate.of(Integer.parseInt(input), 1, 1);

        return this.bookRepository.findBooksByReleaseDateBeforeOrReleaseDateAfter(before, after);

    }

    @Override
    public Set<Book> findBooksReleasedBefore(String input) {

        String[] dataInput = input.split("-");

        int day = Integer.parseInt(dataInput[0]);
        int month = Integer.parseInt(dataInput[1]);
        int year = Integer.parseInt(dataInput[2]);

        LocalDate date = LocalDate.of(year, month, day);

        return this.bookRepository.findBooksByReleaseDateBefore(date);
    }

    @Override
    public int findCountOfBooksWithTitleLongerThan(int input) {

        return this.bookRepository.findBooksByTitleLength(input).size();

    }

    @Override
    public Set<Book> findAllBooksAndTheirCopies() {

        return this.bookRepository.findBooksAndCopies();

    }

    @Override
    public BookModel findBookByGivenTitle(String input) {

        Book book = this.bookRepository.findBookByTitleEquals(input);

        return new BookModel(book.getTitle(), book.getEditionType(),
                book.getAgeRestriction(), book.getPrice());

    }

    @Override
    public void removeBooksWithCopiesLessThan(int input) {

        this.bookRepository.removeBooks(input);

    }

    @Override
    public void updateBooksWithDateHigherThan(String input, int copiesInput) {

        String[] dataInput = input.split(" ");
        String date = dataInput[0] + "-" + dataInput[1] + "-" + dataInput[2];

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        LocalDate localDate = LocalDate.parse(date, dateTimeFormatter);

        System.out.println(localDate);

        this.bookRepository.increaseBookCopies(copiesInput, localDate);

    }

    private Set<Category> addCategories() {

        Random random = new Random();

        int current = random.nextInt(8) + 1;

        Set<Category> categories = new HashSet<>();

        categories.add(this.categoryRepository.findCategoryById(current));

        current = random.nextInt(8) + 1;

        categories.add(this.categoryRepository.findCategoryById(current));

        current = random.nextInt(8) + 1;

        categories.add(this.categoryRepository.findCategoryById(current));

        return categories;
    }

    private Author addRandomAuthor() {

        Random random = new Random();
        int number = random.nextInt(30) + 1;


        return this.authorRepository.findAuthorById(number);

    }

    private String makeTitle(String[] data) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 5; i < data.length; i++) {
            stringBuilder.append(data[i]).append(" ");
        }

        return stringBuilder.toString().trim();

    }

    private LocalDate makeFormattedDate(String givenData) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate localDate = LocalDate.parse(givenData, formatter);

        return localDate;
    }
}
