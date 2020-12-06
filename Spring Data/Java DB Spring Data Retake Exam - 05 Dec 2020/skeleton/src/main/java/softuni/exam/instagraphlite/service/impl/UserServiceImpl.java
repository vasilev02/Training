package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.jsonDto.UserDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private final static String USERS_PATH = "src/main/resources/files/users.json";

    private final UserRepository userRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final PictureRepository pictureRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, PictureRepository pictureRepository) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Boolean аreImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(USERS_PATH)));
    }

    @Override
    public String importUsers() throws IOException {

        StringBuilder sb = new StringBuilder();

        UserDto[] data = this.gson.fromJson(new FileReader(USERS_PATH), UserDto[].class);

        for (UserDto current : data) {

            User findUser = this.userRepository.findUserByUsernameEquals(current.getUsername());

            Picture findPicture = this.pictureRepository.findPictureByPathEquals(current.getProfilePicture());

            if (validationUtil.isValid(current) && findUser == null && findPicture != null) {

                User user = this.modelMapper.map(current, User.class);

                user.setProfilePicture(findPicture);

                this.userRepository.saveAndFlush(user);

                sb.append(String.format("Successfully imported User: %s", user.getUsername()));

            } else {
                sb.append("Invalid User");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public String exportUsersWithTheirPosts() {

        StringBuilder sb = new StringBuilder();

        Set<User> data = this.userRepository.getUsersExport();

        for (User current : data) {

            sb.append(String.format("User: %s", current.getUsername()));
            sb.append(System.lineSeparator());

            sb.append(String.format("Post count: %d", current.getPosts().size()));
            sb.append(System.lineSeparator());

            List<Post> postsList = new ArrayList<>(current.getPosts());

            List<Post> orderedPosts = postsList.stream().sorted(Comparator.comparingDouble(a -> a.getPicture().getSize())).collect(Collectors.toList());

            for (Post post : orderedPosts) {

                sb.append("==Post Details:");
                sb.append(System.lineSeparator());

                sb.append(String.format("----Caption: %s", post.getCaption()));
                sb.append(System.lineSeparator());

                sb.append(String.format("----Picture Size: %.2f", post.getPicture().getSize()));
                sb.append(System.lineSeparator());
            }

        }
        return sb.toString().trim();
    }
}
