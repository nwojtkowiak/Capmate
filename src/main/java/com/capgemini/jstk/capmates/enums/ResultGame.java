package com.capgemini.jstk.capmates.enums;

public enum ResultGame {
    WIN(10), LOSE(0), REMIS(5);

    private final int points;

    ResultGame(int points){
        this.points = points;
    }
}
