package softuni.library.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface BookService {
    boolean areImported();
    String readBooksFileContent() throws IOException;
    String importBooks() throws FileNotFoundException;
}
