package com.capgemini.jstk.capmates.capmates.user.service;

import com.capgemini.jstk.capmates.capmates.game.service.GameDTO;

import java.util.Set;

public class UserDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private StringBuffer password;
    private String motto;

    //zmapowac liste ids na liste obiektow ??
    private Set<GameDTO> games;


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

    public StringBuffer getPassword() {
        return password;
    }

    public void setPassword(StringBuffer password) {
        this.password = password;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public Set<GameDTO> getGames() {
        return games;
    }

    public void setGames(Set<GameDTO> games) {
        this.games = games;
    }
}
