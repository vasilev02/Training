package com.example.demo.services;

import com.example.demo.dto.AddGameGto;

public interface GameService {

    String addGame(AddGameGto addGameGto);

    String deleteGameByGivenId(int id);

    String getAllGamesData();

    String findGameByGivenTitle(String title);

    String getUserGames();

}
