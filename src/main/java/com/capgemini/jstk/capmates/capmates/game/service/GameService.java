package com.capgemini.jstk.capmates.capmates.game.service;

import com.capgemini.jstk.capmates.capmates.game.repository.GameEntity;
import com.capgemini.jstk.capmates.capmates.game.repository.GameDAO;
import com.capgemini.jstk.capmates.capmates.mapper.MapperGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GameService {

    private MapperGame mapperGame;
    private GameDAO dao;

    @Autowired
    public GameService(MapperGame mapperGame, GameDAO dao) {
        this.mapperGame = mapperGame;
        this.dao = dao;
    }

    public GameDTO getGameInformation(int id) {

        GameEntity game = dao.getGame(id);

        return mapperGame.mapFromDAO(game);
    }

    public String getGameName(int id) {

        GameEntity game = dao.getGame(id);

        return game.getName();
    }

    public GameEntity saveGameInformation(GameDTO gameDTO) {

        return mapperGame.mapfromDTO(gameDTO);
    }

    public void addGame(GameDTO gameDTO) {
        GameEntity game = saveGameInformation(gameDTO);
        dao.add(game);
    }

    public void add(String name, boolean isNeedMore) {
        //check in the system if game with "name" exists, when not then add to list
        GameDTO newGame = new GameDTO(name, isNeedMore);
        Set<GameDTO> games = mapperGame.mapfromSetDAO(dao.getGames());
        boolean isNotInSet = true;

        for (GameDTO game : games) {
            if (game.getName().equals(name)) {
                isNotInSet = false;
                break;
            }
        }

        if (isNotInSet) {
            games.add(newGame);
        }


    }

    public int getSizeSetOfGames() {
        return dao.getSizeSetOfGames();

    }

}

