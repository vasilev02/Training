package com.example.demo.services.impl;

import com.example.demo.dto.AddGameGto;
import com.example.demo.entities.Game;
import com.example.demo.entities.User;
import com.example.demo.entities.UserType;
import com.example.demo.repositories.GameRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.GameService;
import com.example.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, UserRepository userRepository, UserService userService1, UserRepository userRepository1) {
        this.gameRepository = gameRepository;
        this.userService = userService1;
        this.userRepository = userRepository1;
        this.modelMapper = new ModelMapper();
    }


    @Override
    public String addGame(AddGameGto addGameGto) {

        StringBuilder sb = new StringBuilder();

        if ("".equals(this.userService.getLoggedUser())) {
            return sb.append("No logged user").toString().trim();
        }

        User user = this.userRepository.findAllByFullName(this.userService.getLoggedUser()).orElse(null);

        if (user == null) {
            return sb.append("You are not logged").toString().trim();
        }

        if (!user.getUserType().equals(UserType.ADMINISTRATOR)) {
            return sb.append("You are not admin").toString().trim();
        }

        Game game = modelMapper.map(addGameGto, Game.class);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Game>> violations = validator.validate(game);

        if (violations.size() > 0) {
            for (ConstraintViolation<Game> violation : violations) {
                sb.append(violation.getMessage()).append(System.lineSeparator());
            }
        } else {

            this.gameRepository.saveAndFlush(game);
            sb.append("Added ").append(game.getTitle()).toString();
            Set<Game> set = new HashSet<>();
            set.add(game);
            user.setGames(set);
            this.userRepository.saveAndFlush(user);

        }

        return sb.toString().trim();

    }

    @Override
    public String deleteGameByGivenId(int id) {

        Game game = this.gameRepository.findById(id).orElse(null);

        if (game == null) {
            return "No such game to delete";
        }

        this.gameRepository.deleteById(id);

        return "Deleted " + game.getTitle();
    }

    @Override
    public String getAllGamesData() {

        StringBuilder sb = new StringBuilder();

        Set<Game> games = this.gameRepository.geAllGames();

        if(games.size() == 0){
            return sb.append("No games to show").toString().trim();
        }

        for (Game game : games) {
            sb.append(String.format("%s %.2f", game.getTitle(), game.getPrice())).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    @Override
    public String findGameByGivenTitle(String title) {

        StringBuilder sb = new StringBuilder();

        Game game = this.gameRepository.findByTitle(title).orElse(null);

        if(game == null){
            return sb.append("No such game ").append(title).toString().trim();
        }

        sb.append("Title: ").append(game.getTitle()).append(System.lineSeparator());
        sb.append("Price: ").append(game.getPrice()).append(System.lineSeparator());
        sb.append("Description: ").append(game.getDescription()).append(System.lineSeparator());
        sb.append("Release Date: ").append(game.getReleaseDate());

        return sb.toString().trim();
    }

    @Override
    public String getUserGames() {

        StringBuilder sb = new StringBuilder();

        User user = this.userRepository.findAllByFullName(this.userService.getLoggedUser()).orElse(null);

        if(user == null){
            return sb.append("No logged User").toString().trim();
        }

        Set<Game> userGames = this.userRepository.getAllGamesOfUser(user.getFullName());

        for (Game game : userGames) {
            sb.append(game.getTitle()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
