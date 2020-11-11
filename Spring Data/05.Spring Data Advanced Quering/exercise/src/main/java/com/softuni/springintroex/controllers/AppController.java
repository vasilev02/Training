package com.softuni.springintroex.controllers;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.models.BookModel;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

@Controller
public class AppController implements CommandLineRunner {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Autowired
    public AppController(BookService bookService, CategoryService categoryService, AuthorService authorService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        //this.seedCategories();
        //this.seedAuthors();
        //this.seedBooks();

        //this.executeTaskOne(input);

        //this.executeTaskTwo();

        //this.executeTaskThree();

        //this.executeTaskFour(input);

        //this.executeTaskFive(input);

        //his.executeTaskSix(input);

        //this.executeTaskSeven(input);

        //this.executeTaskEight(input);

        //this.executeTaskNine(input);

        //this.executeTaskTen();

        //this.executeTaskEleven(input);

        //int copies = Integer.parseInt(scanner.nextLine());
        //this.executeTaskTwelve(input, copies);

        //this.executeTaskThirteen(input);


    }

    private void executeTaskThirteen(String input) {

        int copies = Integer.parseInt(input);

        Set<Book> result = this.bookService.findGoldenBooksWithCopiesLessThan(copies);

        this.bookService.removeBooksWithCopiesLessThan(copies);

        System.out.printf("Deleted books %d", result.size()).println();

    }

    private void executeTaskTwelve(String input, int copies) {

        this.bookService.updateBooksWithDateHigherThan(input, copies);

    }

    private void executeTaskEleven(String input) {

        BookModel book = this.bookService.findBookByGivenTitle(input);

        System.out.printf("%s %S %s %.2f", book.getTitle(), book.getEditionType(),
                book.getAgeRestriction(), book.getPrice()).println();

    }

    private void executeTaskTen() {

        Set<Book> result = this.bookService.findAllBooksAndTheirCopies();

        result.forEach(entry -> {

            System.out.printf("%s %s - %d", entry.getAuthor().getFirstName(),
                    entry.getAuthor().getLastName(), entry.getCopies()).println();

        });

    }

    private void executeTaskNine(String input) {

        int result = this.bookService.findCountOfBooksWithTitleLongerThan(Integer.parseInt(input));

        System.out.printf("There are %d books with longer title than %s symbols", result, input);

    }

    private void executeTaskFive(String input) {

        Set<Book> result = this.bookService.findBooksReleasedBefore(input);

        result.forEach(entry -> {

            System.out.printf("%s %s %.2f", entry.getTitle(),
                    entry.getEditionType(), entry.getPrice()).println();

        });

    }

    private void executeTaskFour(String input) {

        Set<Book> result = this.bookService.findBooksNotReleasedInYear(input);

        result.forEach(entry -> {

            System.out.printf("%s", entry.getTitle()).println();

        });

    }

    private void executeTaskEight(String input) {

        Set<Book> result = this.bookService.findBooksWithAuthorWithLastNameContainingString(input);

        result.forEach(entry -> {

            System.out.printf("%s (%s %s)", entry.getTitle(),
                    entry.getAuthor().getFirstName(), entry.getAuthor().getLastName()).println();

        });

    }

    private void executeTaskSeven(String input) {

        Set<Book> result = this.bookService.findBooksWithTitleContainingGivenString(input);

        result.forEach(entry -> {

            System.out.printf("%s", entry.getTitle()).println();

        });

    }

    private void executeTaskSix(String input) {

        Set<Author> result = this.authorService.getAuthorsNamesEndingWith(input);

        System.out.println("");
        result.forEach(entry -> {

            System.out.printf("%s %s", entry.getFirstName(), entry.getLastName()).println();

        });

    }

    private void executeTaskThree() {

        BigDecimal lessPrice = new BigDecimal("5");
        BigDecimal greaterPrice = new BigDecimal("40");

        Set<Book> result = this.bookService.findBooksBetweenTwoPrices(lessPrice, greaterPrice);

        result.forEach(entry -> {

            System.out.printf("%s - $%.2f", entry.getTitle(), entry.getPrice()).println();

        });

    }

    private void executeTaskTwo() {

        int copies = 5000;

        Set<Book> result = this.bookService.findGoldenBooksWithCopiesLessThan(copies);

        result.forEach(entry -> {

            System.out.printf("%s", entry.getTitle()).println();

        });

    }

    private void seedBooks() throws IOException {

        this.bookService.seedBooksInDB();

    }

    private void seedAuthors() throws IOException {

        this.authorService.seedAuthorsInDB();

    }

    private void seedCategories() throws IOException {

        this.categoryService.seedCategoryInDB();

    }

    private void executeTaskOne(String input) {

        Set<Book> result = this.bookService.findBooksByGivenAgeRestriction(input);

        result.forEach(entry -> {

            System.out.printf("%s", entry.getTitle()).println();

        });

    }

}
