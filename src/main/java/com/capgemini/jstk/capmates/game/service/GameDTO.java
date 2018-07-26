package com.capgemini.jstk.capmates.game.service;

import javafx.util.Pair;
import org.springframework.stereotype.Service;


public class GameDTO {
    private int id;
    private String name;
    private boolean isNeedMore;
    private Pair<Integer, Integer> numberOfPlayers;

    public GameDTO(){

    }
    public GameDTO(String name, boolean isNeedMore){
        this.name = name;
        this.isNeedMore = isNeedMore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsNeedMore() {
        return isNeedMore;
    }

    public void setIsNeedMore(boolean needMore) {
        isNeedMore = needMore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinNumberOfPlayers() { return numberOfPlayers.getKey(); }

    public int getMaxNumberOfPlayers() { return numberOfPlayers.getValue(); }

    public Pair<Integer, Integer> getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int minPlayers, int maxPlayers) {
        this.numberOfPlayers = new Pair<>(minPlayers, maxPlayers);
    }
}
