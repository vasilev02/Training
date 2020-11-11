package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.models.BookModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;


public interface BookService {

    void seedBooksInDB() throws IOException;

    Set<Book> findBooksByGivenAgeRestriction(String input);

    Set<Book> findGoldenBooksWithCopiesLessThan(int input);

    Set<Book> findBooksBetweenTwoPrices(BigDecimal less, BigDecimal greater);

    Set<Book> findBooksWithTitleContainingGivenString(String input);

    Set<Book> findBooksWithAuthorWithLastNameContainingString(String input);

    Set<Book> findBooksNotReleasedInYear(String input);

    Set<Book> findBooksReleasedBefore(String input);

    int findCountOfBooksWithTitleLongerThan(int input);

    Set<Book> findAllBooksAndTheirCopies();

    BookModel findBookByGivenTitle(String input);

    void removeBooksWithCopiesLessThan(int input);

    void updateBooksWithDateHigherThan(String input, int copiesInput);

}
