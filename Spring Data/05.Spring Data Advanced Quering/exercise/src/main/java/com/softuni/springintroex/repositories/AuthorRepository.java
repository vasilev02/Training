package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findAuthorById(long id);

    @Query(value = "SELECT a FROM Author AS a WHERE a.firstName LIKE %:givenInput")
    Set<Author> findAuthorsByFirstNameEndingWith(@Param(value = "givenInput") String input);

}
