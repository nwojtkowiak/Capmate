package com.capgemini.jstk.capmates.player.service;

import com.capgemini.jstk.capmates.enums.Level;
import com.capgemini.jstk.capmates.game.service.GameDTO;

import java.util.ArrayList;
import java.util.List;

public class PlayerDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String motto;
    private Level level;
    private List<GameDTO> games;


    public PlayerDTO(){
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password ="";
        this.motto = "";
        this.level = Level.BEGINNER;
        this.games = new ArrayList<>();
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName;  }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Level getLevel() { return this.level; }

    public void setLevel(Level level) { this.level = level; }

    public List<GameDTO> getGames() { return games; }

    public void setGames(List<GameDTO> games) {this.games = games; }
}
