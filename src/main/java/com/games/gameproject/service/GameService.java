package com.games.gameproject.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.games.gameproject.constants.MessageConstants;
import com.games.gameproject.entities.Game;
import com.games.gameproject.exception.CustomException;
import com.games.gameproject.repository.GameRepository;

import lombok.Data;

@Service
@Data
public class GameService implements IGameService {

        /**
         * Find a game by its ID.
         * 
         * @param id the ID of the game
         * @return the game with the specified ID, or null if not found
         */
        @Override
        public Game getGameById(Long id) {
                try {
                        return gameRepository.findById(id)
                                        .orElseThrow(() -> new CustomException(
                                                        MessageConstants.GAME_NOT_FOUND_WITH_ID + id));
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_FETCHING_GAME_BY_ID, e);
                }
        }

        @Autowired
        private GameRepository gameRepository;

        /**
         * Retrieve all games.
         * 
         * @return a list of all games
         */
        @Override
        public List<Game> findAll() {
                try {
                        List<Game> games = gameRepository.findAll().stream().sorted((game1, game2) -> {
                                int compareName = game1.getName().compareTo(game2.getName());
                                if (compareName != 0) {
                                        return compareName;
                                } else {
                                        return game2.getReleaseDate().compareTo(game1.getReleaseDate());
                                }
                        }).collect(Collectors.toList());

                        return games;
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_FETCHING_ALL_GAMES, e);
                }
        }

        /**
         * Find a game by its ID.
         * 
         * @param id the ID of the game
         * @return the game with the specified ID, or null if not found
         */
        @Override
        public Game findById(Long id) {
                try {
                        return gameRepository.findById(id)
                                        .orElseThrow(() -> new CustomException(
                                                        MessageConstants.GAME_NOT_FOUND_WITH_ID + id));
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_FETCHING_GAME_BY_ID, e);
                }
        }

        /**
         * Save a game.
         * 
         * @param game the game to save
         * @return the saved game
         */
        @Override
        public Game save(Game game) {
                try {
                        return gameRepository.save(game);
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_SAVING_GAME, e);
                }
        }

        /**
         * Delete games by their IDs.
         * 
         * @param ids the IDs of the games to delete
         */
        @Override
        public void deleteById(List<Long> ids) {
                try {
                        for (Long id : ids) {
                                gameRepository.deleteById(id);
                        }
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_DELETING_ALL_GAMES, e);
                }
        }

        /**
         * Delete all games.
         */
        @Override
        public void deleteAll() {
                try {
                        gameRepository.deleteAll();
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_DELETING_ALL_GAMES, e);
                }
        }

        /**
         * Find games by name containing the specified string, ignoring case.
         * 
         * @param name the string to search for in game names
         * @return a list of games with names containing the specified string
         */
        @Override
        public List<Game> findByNameContainingIgnoreCase(String name) {
                try {
                        return gameRepository.findByNameContainingIgnoreCase(name);
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_NAME, e);
                }
        }

        /**
         * Find games by genre containing the specified string, ignoring case.
         * 
         * @param genre the string to search for in game genres
         * @return a list of games with genres containing the specified string
         */
        @Override
        public List<Game> findByGenreContainingIgnoreCase(String genre) {
                try {
                        return gameRepository.findByGenreContainingIgnoreCase(genre);
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_GENRE, e);
                }
        }

        /**
         * Find games by platform containing the specified string, ignoring case.
         * 
         * @param platform the string to search for in game platforms
         * @return a list of games with platforms containing the specified string
         */
        @Override
        public List<Game> findByPlatformContainingIgnoreCase(String platform) {
                try {
                        return gameRepository.findByPlatformContainingIgnoreCase(platform);
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_PLATFORM, e);
                }
        }

        /**
         * Find games by company containing the specified string, ignoring case.
         * 
         * @param company the string to search for in game companies
         * @return a list of games with companies containing the specified string
         */
        @Override
        public List<Game> findByCompanyContainingIgnoreCase(String company) {
                try {
                        return gameRepository.findByCompanyContainingIgnoreCase(company);
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_COMPANY, e);
                }
        }

        /**
         * Find games by release date.
         * 
         * @param releaseDate the release date to search for
         * @return a list of games with the specified release date
         */
        @Override
        public List<Game> findByReleaseDate(Timestamp releaseDate) {
                try {
                        return gameRepository.findByReleaseDate(releaseDate);
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_RELEASE_DATE, e);
                }
        }

        /**
         * Find games by description containing the specified string, ignoring case.
         * 
         * @param description the string to search for in game descriptions
         * @return a list of games with descriptions containing the specified string
         */
        @Override
        public List<Game> findByDescriptionContainingIgnoreCase(String description) {
                try {
                        return gameRepository.findByDescriptionContainingIgnoreCase(description);
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_FINDING_GAMES_BY_DESCRIPTION, e);
                }
        }

        /**
         * Populate the database with a predefined list of games.
         */
        @Override
        public void populateDatabase() {
                List<Game> entities = new ArrayList<>(
                                List.of(new Game(null, "The Legend of Zelda: Breath of the Wild",
                                                "Action-adventure", "Nintendo Switch",
                                                Timestamp.valueOf("2017-03-03 00:00:00"), "Nintendo",
                                                "The Legend of Zelda: Breath of the Wild is an action-adventure game developed and published by Nintendo."),
                                                new Game(null, "Super Mario Odyssey", "Platformer", "Nintendo Switch",
                                                                Timestamp.valueOf("2017-10-27 00:00:00"), "Nintendo",
                                                                "Super Mario Odyssey is a platform game developed and published by Nintendo."),
                                                new Game(null, "God of War", "Action-adventure", "PlayStation 4",
                                                                Timestamp.valueOf("2018-04-20 00:00:00"),
                                                                "Santa Monica Studio",
                                                                "God of War is an action-adventure game developed by Santa Monica Studio."),
                                                new Game(null, "The Witcher 3: Wild Hunt", "Action role-playing", "PC",
                                                                Timestamp.valueOf("2015-05-19 00:00:00"),
                                                                "CD Projekt Red",
                                                                "The Witcher 3: Wild Hunt is an action role-playing game developed by CD Projekt Red."),
                                                new Game(null, "Minecraft", "Sandbox", "PC",
                                                                Timestamp.valueOf("2011-11-18 00:00:00"),
                                                                "Mojang Studios",
                                                                "Minecraft is a sandbox video game developed by Mojang Studios.")));

                try {
                        gameRepository.deleteAll();
                        gameRepository.saveAll(entities);
                } catch (Exception e) {
                        throw new CustomException(MessageConstants.ERROR_POPULATING_DATABASE, e);
                }
        }
}