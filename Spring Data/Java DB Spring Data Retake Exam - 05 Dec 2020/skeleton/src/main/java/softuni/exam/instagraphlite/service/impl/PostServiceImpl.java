package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.xmlDto.PostRootXmlDto;
import softuni.exam.instagraphlite.models.dto.xmlDto.PostXmlDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PostServiceImpl implements PostService {

    private final static String POSTS_PATH = "src/main/resources/files/posts.xml";

    private final PostRepository postRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, UserRepository userRepository, PictureRepository pictureRepository) {
        this.postRepository = postRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
    }


    @Override
    public Boolean аreImported() {
        return this.postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        return String.join("", Files.readAllLines(Path.of(POSTS_PATH)));
    }

    @Override
    public String importPosts() throws IOException, JAXBException {

        StringBuilder sb = new StringBuilder();

        PostRootXmlDto data = this.xmlParser.parseXml(PostRootXmlDto.class, POSTS_PATH);

        for (PostXmlDto current : data.getPostXmlDtos()) {

            User findUser = this.userRepository.findUserByUsernameEquals(current.getUser().getUsername());
            Picture findPicture = this.pictureRepository.findPictureByPathEquals(current.getPicture().getPath());

            if (validationUtil.isValid(current) && findUser != null && findPicture != null) {

                Post post = this.modelMapper.map(current, Post.class);

                post.setUser(findUser);
                post.setPicture(findPicture);

                this.postRepository.saveAndFlush(post);

                sb.append(String.format("Successfully imported Post, made by %s",findUser.getUsername()));

            } else {
                sb.append("Invalid Post");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();

    }
}
