package softuni.library.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface AuthorService {
    boolean areImported();
    String readAuthorsFileContent() throws IOException;
    String importAuthors() throws FileNotFoundException;
}
