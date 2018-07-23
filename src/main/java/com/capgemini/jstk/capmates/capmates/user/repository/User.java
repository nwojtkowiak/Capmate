package com.capgemini.jstk.capmates.capmates.user.repository;

import com.capgemini.jstk.capmates.capmates.game.repository.Game;

import java.util.List;
import java.util.Set;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private StringBuffer password;
    private String motto;
    private Set<Game> games;

    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

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
        return email;
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

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
