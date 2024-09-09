package com.games.gameproject.entities;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "game")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 250, unique = false)
    private String name;

    @Column(nullable = true, length = 250, unique = false)
    private String genre;

    @Column(nullable = true, length = 250, unique = false)
    private String platform;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(nullable = true, unique = false)
    private Timestamp releaseDate;

    @Column(nullable = true, length = 250, unique = false)
    private String company;

    @Column(nullable = true, length = 2000, unique = false)
    private String description;
}
