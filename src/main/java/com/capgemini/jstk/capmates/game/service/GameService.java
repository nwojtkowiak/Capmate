package com.capgemini.jstk.capmates.game.service;

import com.capgemini.jstk.capmates.game.repository.GameDAO;
import com.capgemini.jstk.capmates.game.repository.GameEntity;
import com.capgemini.jstk.capmates.game.repository.PlayerGameDAO;
import com.capgemini.jstk.capmates.mapper.MapperGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private MapperGame mapperGame;
    private GameDAO gameDAO;
    private PlayerGameDAO playerGameDAO;

    @Autowired
    public GameService(MapperGame mapperGame, GameDAO gameDAO, PlayerGameDAO playerGameDAO) {
        this.mapperGame = mapperGame;
        this.gameDAO = gameDAO;
        this.playerGameDAO = playerGameDAO;
    }

    public GameDTO getGameInformation(int id) {

        GameEntity game = gameDAO.getGame(id);

        return mapperGame.mapFromDAO(game);
    }

    public String getGameName(int id) {

        GameEntity game = gameDAO.getGame(id);

        return game.getName();
    }

    public GameEntity saveGameInformation(GameDTO gameDTO) {

        return mapperGame.mapfromDTO(gameDTO);
    }

    public void addGame(GameDTO gameDTO) {
        GameEntity game = saveGameInformation(gameDTO);
        gameDAO.add(game);
    }

    public int getSizeSetOfGames() {
        return gameDAO.getSizeSetOfGames();
    }

    public List<Integer> getListOfPlayerIdByGame(int gameId) {
        return playerGameDAO.getListPlayersOfGames(gameId);
    }

}

