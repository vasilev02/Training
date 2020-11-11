package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;

import java.io.IOException;
import java.util.Set;

public interface AuthorService {

    void seedAuthorsInDB() throws IOException;

    Set<Author> getAuthorsNamesEndingWith(String input);

}
