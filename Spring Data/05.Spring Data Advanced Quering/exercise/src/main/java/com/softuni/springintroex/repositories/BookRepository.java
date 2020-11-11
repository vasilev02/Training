package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Set<Book> findBooksByAgeRestrictionLike(AgeRestriction ageRestriction);

    Set<Book> findBooksByCopiesLessThan(int copies);

    Set<Book> findBooksByPriceLessThanOrPriceGreaterThan(BigDecimal less, BigDecimal greater);

    @Query(value = "SELECT b FROM Book as b WHERE b.title LIKE %:givenInput%")
    Set<Book> findBooksContainingGivenStringInTitle(@Param(value = "givenInput") String input);

    @Query(value = "SELECT b FROM Book as b JOIN b.author AS a WHERE a.lastName LIKE :givenInput%")
    Set<Book> findBooksByAuthorLastNameContainingString(@Param(value = "givenInput") String input);

    Set<Book> findBooksByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    Set<Book> findBooksByReleaseDateBefore(LocalDate date);

    @Query(value = "SELECT b FROM Book AS b WHERE LENGTH(b.title) > :givenLength")
    Set<Book> findBooksByTitleLength(@Param(value = "givenLength") int length);

    @Query(value = "SELECT b AS copies FROM Book AS b GROUP BY b.author.id ORDER BY b.copies DESC")
    Set<Book> findBooksAndCopies();

    Book findBookByTitleEquals(String title);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Book AS b SET b.copies = b.copies + :givenCopies WHERE b.releaseDate > :givenDate")
    void increaseBookCopies(@Param(value = "givenCopies") int copies, @Param(value = "givenDate") LocalDate date);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Book WHERE copies < :copies")
    void removeBooks(@Param(value = "copies") int copies);

}
