package com.capgemini.jstk.capmates.capmates.game.repository;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class GameDAO {

    Map<Integer,GameEntity> listOfGames;

    public Set<GameEntity> getGames(){
        return listOfGames.values().stream().collect(Collectors.toSet());
    }

    public Set<GameEntity> getGames(List<Integer> idOfGames){
        Set<GameEntity> games = new HashSet<>();

        for(int id : idOfGames){
            games.add(listOfGames.get(id));
        }

        return games;
    }

    public GameEntity getGame(int id){
        return listOfGames.get(id);
    }

    public void add(GameEntity game){
        listOfGames.put(game.getId(), game);
    }
}
