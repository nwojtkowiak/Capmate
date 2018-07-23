package com.capgemini.jstk.capmates.capmates.game.service;

import org.springframework.stereotype.Service;


public class GameDTO {
    private int id;
    private String name;
    private boolean isNeedMore;

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
}
