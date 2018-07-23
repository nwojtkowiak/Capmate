package com.capgemini.jstk.capmates.capmates.game.repository;

public class Game {

    private int id;
    private String name;
    private boolean isNeedMore;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNeedMore() {
        return isNeedMore;
    }

    public void setIsNeedMore(boolean needMore) {
        isNeedMore = needMore;
    }
}
