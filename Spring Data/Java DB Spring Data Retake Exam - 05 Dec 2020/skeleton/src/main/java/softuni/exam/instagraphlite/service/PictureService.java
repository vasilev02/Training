package softuni.exam.instagraphlite.service;

import java.io.IOException;

public interface PictureService {
    Boolean areImported();
    String readFromFileContent() throws IOException;
    String importPictures() throws IOException;
    String exportPictures();

}
