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
public class GameServiceImpl implements GameService {
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
                    .orElseThrow(() -> new CustomException(MessageConstants.GAME_NOT_FOUND_WITH_ID + id));
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
        List<Game> entities = new ArrayList<>();

        entities.addAll(List.of(
                new Game(null, "The Legend of Zelda: Breath of the Wild", "Action-adventure",
                        "Nintendo Switch",
                        Timestamp.valueOf("2017-03-03 00:00:00"), "Nintendo",
                        "The Legend of Zelda: Breath of the Wild is an action-adventure game developed and published by Nintendo, released for the Nintendo Switch and Wii U consoles on March 3, 2017."),
                new Game(null, "The Witcher 3: Wild Hunt", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2015-05-19 00:00:00"), "CD Projekt",
                        "The Witcher 3: Wild Hunt is a 2015 action role-playing game developed and published by CD Projekt. Based on The Witcher series of fantasy novels by Polish author Andrzej Sapkowski, it is the sequel to the 2011 game The Witcher 2: Assassins of Kings."),
                new Game(null, "Red Dead Redemption 2", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2018-10-26 00:00:00"), "Rockstar Games",
                        "Red Dead Redemption 2 is a 2018 action-adventure game developed and published by Rockstar Games. The game is the third entry in the Red Dead series and is a prequel to the 2010 game Red Dead Redemption."),
                new Game(null, "The Last of Us Part II", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2020-06-19 00:00:00"), "Naughty Dog",
                        "The Last of Us Part II is a 2020 action-adventure game developed by Naughty Dog and published by Sony Interactive Entertainment for the PlayStation 4."),
                new Game(null, "God of War", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2018-04-20 00:00:00"), "Santa Monica Studio",
                        "God of War is a 2018 action-adventure game developed by Santa Monica Studio and published by Sony Interactive Entertainment. The game is the eighth installment in the God of War series, the eighth chronologically, and the sequel to 2010's God of War III."),
                new Game(null, "Horizon Zero Dawn", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2017-02-28 00:00:00"), "Guerrilla Games",
                        "Horizon Zero Dawn is a 2017 action role-playing game developed by Guerrilla Games and published by Sony Interactive Entertainment. The plot follows Aloy, a young hunter in a world overrun by machines, who sets out to uncover her past."),
                new Game(null, "Uncharted 4: A Thief's End", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2016-05-10 00:00:00"), "Naughty Dog",
                        "Uncharted 4: A Thief's End is a 2016 action-adventure game developed by Naughty Dog and published by Sony Interactive Entertainment. It is the fourth main entry in the Uncharted series."),
                new Game(null, "Marvel", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2020-11-12 00:00:00"),
                        "Insomniac Games", "Marvel's Spider Man"),
                new Game(null, "Ghost of Tsushima", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2020-07-17 00:00:00"), "Sucker Punch Productions",
                        "Ghost of Tsushima is a 2020 action-adventure game developed by Sucker Punch Productions and published by Sony Interactive Entertainment. Featuring an open world, it follows Jin Sakai, a samurai on a quest to protect Tsushima Island during the first Mongol invasion of Japan."),
                new Game(null, "Death Stranding", "Action", "PlayStation 4",
                        Timestamp.valueOf("2019-11-08 00:00:00"),
                        "Kojima Productions",
                        "Death Stranding is a 2019 action game developed by Kojima Productions and published by Sony Interactive Entertainment. It is the first game from director Hideo Kojima and Kojima Productions after their disbandment from Konami in 2015."),
                new Game(null, "Cyberpunk 2077", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2020-12-10 00:00:00"), "CD Projekt",
                        "Cyberpunk 2077 is a 2020 action role-playing video game developed and published by CD Projekt. The story takes place in Night City, an open world set in the Cyberpunk universe."),
                new Game(null, "Assassin's Creed Valhalla", "Action role-playing", "PlayStation 5",
                        Timestamp.valueOf("2020-11-10 00:00:00"), "Ubisoft",
                        "Assassin's Creed Valhalla is an action role-playing video game developed by Ubisoft Montreal and published by Ubisoft."),
                new Game(null, "FIFA 21", "Sports", "PlayStation 4",
                        Timestamp.valueOf("2020-10-06 00:00:00"), "EA Sports",
                        "FIFA 21 is a football simulation video game published by Electronic Arts as part of the FIFA series."),
                new Game(null, "Resident Evil Village", "Survival horror", "PlayStation 5",
                        Timestamp.valueOf("2021-05-07 00:00:00"), "Capcom",
                        "Resident Evil Village is a survival horror game developed and published by Capcom."),
                new Game(null, "NBA 2K21", "Sports", "PlayStation 4",
                        Timestamp.valueOf("2020-09-04 00:00:00"), "2K Sports",
                        "NBA 2K21 is a basketball simulation video game developed by Visual Concepts and published by 2K Sports."),
                new Game(null, "Call of Duty: Black Ops Cold War", "First-person shooter",
                        "PlayStation 4",
                        Timestamp.valueOf("2020-11-13 00:00:00"), "Treyarch",
                        "Call of Duty: Black Ops Cold War is a first-person shooter video game developed by Treyarch and Raven Software and published by Activision."),
                new Game(null, "Doom Eternal", "First-person shooter", "PlayStation 4",
                        Timestamp.valueOf("2020-03-20 00:00:00"), "id Software",
                        "Doom Eternal is a first-person shooter game developed by id Software and published by Bethesda Softworks."),
                new Game(null, "Genshin Impact", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2020-09-28 00:00:00"), "miHoYo",
                        "Genshin Impact is an action role-playing game developed and published by miHoYo."),
                new Game(null, "Among Us", "Party", "PC",
                        Timestamp.valueOf("2018-06-15 00:00:00"), "Innersloth",
                        "Among Us is a multiplayer party game developed and published by Innersloth."),
                new Game(null, "Fall Guys: Ultimate Knockout", "Platformer", "PC",
                        Timestamp.valueOf("2020-08-04 00:00:00"), "Mediatonic",
                        "Fall Guys: Ultimate Knockout is a platform battle royale game developed by Mediatonic and published by Devolver Digital."),
                new Game(null, "Fortnite", "Battle Royale", "PC",
                        Timestamp.valueOf("2017-07-25 00:00:00"), "Epic Games",
                        "Fortnite is an online video game developed by Epic Games and released in 2017."),
                new Game(null, "Minecraft", "Sandbox", "PC", Timestamp.valueOf("2011-11-18 00:00:00"),
                        "Mojang",
                        "Minecraft is a sandbox video game developed by Mojang."),
                new Game(null, "Grand Theft Auto V", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2013-09-17 00:00:00"), "Rockstar Games",
                        "Grand Theft Auto V is an action-adventure game developed by Rockstar North and published by Rockstar Games."),
                new Game(null, "Overwatch", "First-person shooter", "PC",
                        Timestamp.valueOf("2016-05-24 00:00:00"), "Blizzard Entertainment",
                        "Overwatch is a team-based multiplayer first-person shooter developed and published by Blizzard Entertainment."),
                new Game(null, "League of Legends", "MOBA", "PC",
                        Timestamp.valueOf("2009-10-27 00:00:00"), "Riot Games",
                        "League of Legends is a multiplayer online battle arena video game developed and published by Riot Games."),
                new Game(null, "Apex Legends", "Battle Royale", "PC",
                        Timestamp.valueOf("2019-02-04 00:00:00"), "Respawn Entertainment",
                        "Apex Legends is a free-to-play battle royale-hero shooter game developed by Respawn Entertainment and published by Electronic Arts."),
                new Game(null, "Valorant", "First-person shooter", "PC",
                        Timestamp.valueOf("2020-06-02 00:00:00"), "Riot Games",
                        "Valorant is a free-to-play first-person hero shooter developed and published by Riot Games."),
                new Game(null, "Animal Crossing: New Horizons", "Simulation", "Nintendo Switch",
                        Timestamp.valueOf("2020-03-20 00:00:00"), "Nintendo",
                        "Animal Crossing: New Horizons is a 2020 life simulation video game developed and published by Nintendo for the Nintendo Switch."),
                new Game(null, "Super Mario Odyssey", "Platformer", "Nintendo Switch",
                        Timestamp.valueOf("2017-10-27 00:00:00"), "Nintendo",
                        "Super Mario Odyssey is a platform game developed and published by Nintendo for the Nintendo Switch."),
                new Game(null, "Splatoon 2", "Third-person shooter", "Nintendo Switch",
                        Timestamp.valueOf("2017-07-21 00:00:00"), "Nintendo",
                        "Splatoon 2 is a third-person shooter game developed and published by Nintendo for the Nintendo Switch."),
                new Game(null, "Mario Kart 8 Deluxe", "Racing", "Nintendo Switch",
                        Timestamp.valueOf("2017-04-28 00:00:00"), "Nintendo",
                        "Mario Kart 8 Deluxe is a kart racing game developed and published by Nintendo for the Nintendo Switch."),
                new Game(null, "The Sims 4", "Simulation", "PC",
                        Timestamp.valueOf("2014-09-02 00:00:00"), "Maxis",
                        "The Sims 4 is a life simulation game developed by Maxis and published by Electronic Arts."),
                new Game(null, "Stardew Valley", "Simulation", "PC",
                        Timestamp.valueOf("2016-02-26 00:00:00"), "ConcernedApe",
                        "Stardew Valley is a simulation role-playing video game developed by Eric 'ConcernedApe' Barone."),
                new Game(null, "Terraria", "Action-adventure", "PC",
                        Timestamp.valueOf("2011-05-16 00:00:00"), "Re-Logic",
                        "Terraria is an action-adventure sandbox game developed by Re-Logic."),
                new Game(null, "Dark Souls III", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2016-03-24 00:00:00"), "FromSoftware",
                        "Dark Souls III is an action role-playing game developed by FromSoftware and published by Bandai Namco Entertainment."),
                new Game(null, "Sekiro: Shadows Die Twice", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2019-03-22 00:00:00"), "FromSoftware",
                        "Sekiro: Shadows Die Twice is an action-adventure game developed by FromSoftware and published by Activision."),
                new Game(null, "Bloodborne", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2015-03-24 00:00:00"), "FromSoftware",
                        "Bloodborne is an action role-playing game developed by FromSoftware and published by Sony Computer Entertainment."),
                new Game(null, "Monster Hunter: World", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2018-01-26 00:00:00"), "Capcom",
                        "Monster Hunter: World is an action role-playing game developed and published by Capcom."),
                new Game(null, "Persona 5", "Role-playing", "PlayStation 4",
                        Timestamp.valueOf("2016-09-15 00:00:00"), "Atlus",
                        "Persona 5 is a role-playing video game developed by Atlus."),
                new Game(null, "Final Fantasy XV", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2016-11-29 00:00:00"), "Square Enix",
                        "Final Fantasy XV is an action role-playing game developed and published by Square Enix."),
                new Game(null, "Nier: Automata", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2017-02-23 00:00:00"), "PlatinumGames",
                        "Nier: Automata is an action role-playing game developed by PlatinumGames and published by Square Enix."),
                new Game(null, "Dragon Quest XI", "Role-playing", "PlayStation 4",
                        Timestamp.valueOf("2017-07-29 00:00:00"), "Square Enix",
                        "Dragon Quest XI: Echoes of an Elusive Age is a role-playing video game developed and published by Square Enix."),
                new Game(null, "Yakuza 0", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2015-03-12 00:00:00"), "Sega",
                        "Yakuza 0 is an action-adventure game developed and published by Sega."),
                new Game(null, "The Elder Scrolls V: Skyrim", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2011-11-11 00:00:00"), "Bethesda Game Studios",
                        "The Elder Scrolls V: Skyrim is an action role-playing video game developed by Bethesda Game Studios and published by Bethesda Softworks."),
                new Game(null, "Fallout 4", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2015-11-10 00:00:00"), "Bethesda Game Studios",
                        "Fallout 4 is an action role-playing game developed by Bethesda Game Studios and published by Bethesda Softworks."),
                new Game(null, "The Outer Worlds", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2019-10-25 00:00:00"), "Obsidian Entertainment",
                        "The Outer Worlds is an action role-playing game developed by Obsidian Entertainment and published by Private Division."),
                new Game(null, "Control", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2019-08-27 00:00:00"), "Remedy Entertainment",
                        "Control is an action-adventure game developed by Remedy Entertainment and published by 505 Games."),
                new Game(null, "Hades", "Roguelike", "PC", Timestamp.valueOf("2020-09-17 00:00:00"),
                        "Supergiant Games",
                        "Hades is a roguelike action dungeon crawler video game developed and published by Supergiant Games."),
                new Game(null, "Celeste", "Platformer", "PC", Timestamp.valueOf("2018-01-25 00:00:00"),
                        "Maddy Makes Games",
                        "Celeste is a platforming video game developed and published by Maddy Makes Games."),
                new Game(null, "Hollow Knight", "Metroidvania", "PC",
                        Timestamp.valueOf("2017-02-24 00:00:00"), "Team Cherry",
                        "Hollow Knight is a Metroidvania video game developed and published by Team Cherry."),
                new Game(null, "Cuphead", "Run and gun", "PC", Timestamp.valueOf("2017-09-29 00:00:00"),
                        "Studio MDHR",
                        "Cuphead is a run and gun video game developed and published by Studio MDHR."),
                new Game(null, "Dead Cells", "Roguelike", "PC",
                        Timestamp.valueOf("2018-08-07 00:00:00"), "Motion Twin",
                        "Dead Cells is a roguelike-metroidvania hybrid video game developed and published by Motion Twin."),
                new Game(null, "Slay the Spire", "Roguelike", "PC",
                        Timestamp.valueOf("2019-01-23 00:00:00"), "MegaCrit",
                        "Slay the Spire is a roguelike deck-building video game developed by American studio MegaCrit and published by Humble Bundle."),
                new Game(null, "Disco Elysium", "Role-playing", "PC",
                        Timestamp.valueOf("2019-10-15 00:00:00"), "ZA/UM",
                        "Disco Elysium is a role-playing video game developed and published by ZA/UM."),
                new Game(null, "Divinity: Original Sin II", "Role-playing", "PC",
                        Timestamp.valueOf("2017-09-14 00:00:00"), "Larian Studios",
                        "Divinity: Original Sin II is a role-playing video game developed and published by Larian Studios."),
                new Game(null, "Pillars of Eternity II: Deadfire", "Role-playing", "PC",
                        Timestamp.valueOf("2018-05-08 00:00:00"), "Obsidian Entertainment",
                        "Pillars of Eternity II: Deadfire is a role-playing video game developed by Obsidian Entertainment and published by Versus Evil."),
                new Game(null, "XCOM 2", "Turn-based tactics", "PC",
                        Timestamp.valueOf("2016-02-05 00:00:00"), "Firaxis Games",
                        "XCOM 2 is a turn-based tactics video game developed by Firaxis Games and published by 2K Games."),

                new Game(null, "Darkest Dungeon", "Roguelike", "PC",
                        Timestamp.valueOf("2016-01-19 00:00:00"), "Red Hook Studios",
                        "Darkest Dungeon is a roguelike turn-based role-playing video game developed and published by Red Hook Studios."),
                new Game(null, "Stardew Valley", "Simulation", "PC",
                        Timestamp.valueOf("2016-02-26 00:00:00"), "ConcernedApe",
                        "Stardew Valley is a simulation role-playing video game developed by Eric 'ConcernedApe' Barone."),
                new Game(null, "The Binding of Isaac: Rebirth", "Roguelike", "PC",
                        Timestamp.valueOf("2014-11-04 00:00:00"), "Nicalis",
                        "The Binding of Isaac: Rebirth is a roguelike dungeon crawler video game developed and published by Nicalis."),
                new Game(null, "Hollow Knight", "Metroidvania", "PC",
                        Timestamp.valueOf("2017-02-24 00:00:00"), "Team Cherry",
                        "Hollow Knight is a Metroidvania action-adventure game developed and published by Team Cherry."),
                new Game(null, "Ori and the Blind Forest", "Platformer", "PC",
                        Timestamp.valueOf("2015-03-11 00:00:00"), "Moon Studios",
                        "Ori and the Blind Forest is a platformer Metroidvania video game developed by Moon Studios and published by Microsoft Studios."),
                new Game(null, "Celeste", "Platformer", "PC",
                        Timestamp.valueOf("2018-01-25 00:00:00"), "Maddy Makes Games",
                        "Celeste is a platforming video game developed and published by Maddy Makes Games."),
                new Game(null, "Undertale", "Role-playing", "PC",
                        Timestamp.valueOf("2015-09-15 00:00:00"), "Toby Fox",
                        "Undertale is a role-playing video game created by indie developer Toby Fox."),
                new Game(null, "Hades", "Roguelike", "PC",
                        Timestamp.valueOf("2020-09-17 00:00:00"), "Supergiant Games",
                        "Hades is a roguelike dungeon crawler video game developed and published by Supergiant Games.")));

        try {
            gameRepository.deleteAll();
            gameRepository.saveAll(entities);
        } catch (Exception e) {
            throw new CustomException(MessageConstants.ERROR_POPULATING_DATABASE, e);
        }
    }
}