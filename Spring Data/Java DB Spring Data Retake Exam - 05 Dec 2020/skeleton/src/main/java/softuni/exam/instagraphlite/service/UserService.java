package softuni.exam.instagraphlite.service;

import java.io.IOException;

public interface UserService {
    Boolean аreImported();
    String readFromFileContent() throws IOException;
    String importUsers() throws IOException;
    String exportUsersWithTheirPosts();
}
