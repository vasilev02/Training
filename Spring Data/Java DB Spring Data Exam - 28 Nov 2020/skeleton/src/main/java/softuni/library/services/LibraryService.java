package softuni.library.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface LibraryService {
    boolean areImported();
    String readLibrariesFileContent() throws IOException;
    String importLibraries() throws JAXBException;
}
