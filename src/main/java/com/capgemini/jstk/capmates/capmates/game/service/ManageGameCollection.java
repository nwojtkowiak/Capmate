package com.capgemini.jstk.capmates.capmates.game.service;

import com.capgemini.jstk.capmates.capmates.game.repository.GameEntity;
import com.capgemini.jstk.capmates.capmates.game.repository.GameDAO;
import com.capgemini.jstk.capmates.capmates.mapper.MapperGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ManageGameCollection {

    private MapperGame mapperGame;
    private GameDAO dao;

    @Autowired
    public ManageGameCollection(MapperGame mapperGame, GameDAO dao){
        this.mapperGame = mapperGame;
        this.dao = dao;
    }

     public GameDTO getGameInformation(int id){

        GameEntity user = dao.getGame(id);

        return mapperGame.mapFromDAO(user);
    }

    public GameEntity saveGameInformation(GameDTO gameDTO){

        return mapperGame.mapfromDTO(gameDTO);
    }

    public void addGame(GameDTO gameDTO){
        GameEntity game = saveGameInformation(gameDTO);
        dao.add(game);
    }

    public void remove(int id){
    //remove from list id games from player
//        GameDTO gameToRemove = new GameDTO();
//        Set<GameDTO> games = mapperGame.mapfromSetDAO(dao.getGames());
//
//        for(GameDTO game : games){
//            if(game.getId() == id){
//                gameToRemove = game;
//            }
//        }
//
//        games.remove(gameToRemove);


    }

    public void add(String name, boolean isNeedMore){
        //check in the system if game with "name" exists, when not then add to list
        GameDTO newGame = new GameDTO(name, isNeedMore);
        Set<GameDTO> games = mapperGame.mapfromSetDAO(dao.getGames());
        boolean isNotInSet = true;

        for(GameDTO game : games){
            if(game.getName().equals(name)){
                isNotInSet = false;
                break;
            }
        }

        if(isNotInSet){
            games.add(newGame);
        }



    }

}

