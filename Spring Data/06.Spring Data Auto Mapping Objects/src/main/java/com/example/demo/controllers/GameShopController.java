package com.example.demo.controllers;

import com.example.demo.dto.AddGameGto;
import com.example.demo.dto.LoginUserDto;
import com.example.demo.dto.RegisterUserDto;
import com.example.demo.services.GameService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Controller
public class GameShopController implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public GameShopController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String[] data = scanner.nextLine().split("\\|");

            switch (data[0]) {

                case "RegisterUser":

                    RegisterUserDto dto = new RegisterUserDto(data[4], data[1], data[2], data[3]);

                    System.out.println(this.userService.registerUser(dto));

                    break;

                case "LoginUser":

                    LoginUserDto loginUserDto = new LoginUserDto(data[1], data[2]);

                    System.out.println(this.userService.loginUser(loginUserDto));

                    break;

                case "Logout":

                    System.out.println(this.userService.logout());

                    break;

                case "AddGame":

                    BigDecimal decimal = new BigDecimal(data[2]);
                    LocalDate date = LocalDate.parse(data[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    AddGameGto addGameGto = new AddGameGto(data[1], decimal,
                            Double.parseDouble(data[3]), data[4], data[5], data[6], date);

                    System.out.println(this.gameService.addGame(addGameGto));

                    break;

                case "DeleteGame":

                    System.out.println(this.gameService.deleteGameByGivenId(Integer.parseInt(data[1])));

                    break;

                case "AllGames":

                    System.out.println(this.gameService.getAllGamesData());

                    break;

                case "DetailGame":

                    System.out.println(this.gameService.findGameByGivenTitle(data[1]));

                    break;

                case "OwnedGames":

                    System.out.println(this.gameService.getUserGames());

                    break;

            }

        }

    }

}
