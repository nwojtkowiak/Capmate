package com.capgemini.jstk.capmates.capmates.history.repository;

import com.capgemini.jstk.capmates.capmates.enums.ResultGame;

import java.util.Date;

public class HistoryEntity {

    private int id;
    private int playerId;
    private int gameId;
    private ResultGame resultGame;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public ResultGame getResultGame() {
        return resultGame;
    }

    public void setResultGame(ResultGame resultGame) {
        this.resultGame = resultGame;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
