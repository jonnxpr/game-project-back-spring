package com.games.gameproject.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.games.gameproject.entities.Game;
import com.games.gameproject.repository.GameRepository;

import lombok.Data;

@Service
@Data
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findById(Long id) {
        return gameRepository.findById(id).orElse(null);
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    public List<Game> findByNameContainingIgnoreCase(String name) {
        return gameRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Game> findByGenreContainingIgnoreCase(String genre) {
        return gameRepository.findByGenreContainingIgnoreCase(genre);
    }

    public List<Game> findByPlatformContainingIgnoreCase(String platform) {
        return gameRepository.findByPlatformContainingIgnoreCase(platform);
    }

    public List<Game> findByCompanyContainingIgnoreCase(String company) {
        return gameRepository.findByCompanyContainingIgnoreCase(company);
    }

    public List<Game> findByReleaseDate(Timestamp releaseDate) {
        return gameRepository.findByReleaseDate(releaseDate);
    }

    public List<Game> findByDescriptionContainingIgnoreCase(String description) {
        return gameRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<Game> populateDatabase() {
        List<Game> entities = List.of(
                new Game(null, "The Legend of Zelda: Breath of the Wild", "Action-adventure", "Nintendo Switch",
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
                new Game(null, "Marvel", "Action-adventure", "PlayStation 4", Timestamp.valueOf("2020-11-12 00:00:00"),
                        "Insomniac Games", "Marvel's Spider Man"),
                new Game(null, "Ghost of Tsushima", "Action-adventure", "PlayStation 4",
                        Timestamp.valueOf("2020-07-17 00:00:00"), "Sucker Punch Productions",
                        "Ghost of Tsushima is a 2020 action-adventure game developed by Sucker Punch Productions and published by Sony Interactive Entertainment. Featuring an open world, it follows Jin Sakai, a samurai on a quest to protect Tsushima Island during the first Mongol invasion of Japan."),
                new Game(null, "Death Stranding", "Action", "PlayStation 4", Timestamp.valueOf("2019-11-08 00:00:00"),
                        "Kojima Productions",
                        "Death Stranding is a 2019 action game developed by Kojima Productions and published by Sony Interactive Entertainment. It is the first game from director Hideo Kojima and Kojima Productions after their disbandment from Konami in 2015."),
                new Game(null, "Cyberpunk 2077", "Action role-playing", "PlayStation 4",
                        Timestamp.valueOf("2020-12-10 00:00:00"), "CD Projekt",
                        "Cyberpunk 2077 is a 2020 action role-playing video game developed and published by CD Projekt. The story takes place in Night City, an open world set in the Cyberpunk universe."));

        gameRepository.deleteAll();
        gameRepository.saveAll(entities);

        return entities;
    }
}
