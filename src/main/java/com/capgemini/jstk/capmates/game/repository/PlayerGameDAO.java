package com.capgemini.jstk.capmates.game.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PlayerGameDAO {

    private Set<PlayerGameEntity> listOfPlayersAndGames;

    private GameDAO gameDao;

    @Autowired
    public PlayerGameDAO(GameDAO gameDao) {
        listOfPlayersAndGames = new HashSet<>();
        this.gameDao = gameDao;
    }

    public Set<GameEntity> getListGamesOfPlayer(int idPlayer) {
        final List<Integer> listIdGames = listOfPlayersAndGames.stream().filter(p -> p.getPlayerId() == idPlayer).map(p -> p.getGameId()).collect(Collectors.toList());

        Set<GameEntity> listGames = gameDao.getGames();

        return listGames.stream().filter(p -> listIdGames.contains(p.getId())).collect(Collectors.toSet());

    }


    public List<Integer> getListPlayersOfGames(int idGame) {
        List<Integer> listIdPlayers = listOfPlayersAndGames.stream().filter(p -> p.getGameId() == idGame).map(p -> p.getPlayerId()).collect(Collectors.toList());

        return listIdPlayers;

    }

    //numberOfRows can't be greater than number of rows in lisOfGames
    public void initListOfPlayersAndGames(int numberOfRows) {
        for (int i = 0; i < numberOfRows; i++) {
            PlayerGameEntity playerGameEntity = new PlayerGameEntity();
            playerGameEntity.setId(i);
            playerGameEntity.setGameId(i);
            playerGameEntity.setPlayerId(0);
            listOfPlayersAndGames.add(playerGameEntity);
        }
    }

    public int addGame(GameEntity game, int playerId) {
        //add game to set of all games
        int idGame = gameDao.getSizeSetOfGames();
        game.setId(idGame);
        gameDao.add(game);
        PlayerGameEntity playerGameEntity = new PlayerGameEntity();

        int id = listOfPlayersAndGames.size() + 1;
        playerGameEntity.setPlayerId(playerId);
        playerGameEntity.setGameId(game.getId());
        playerGameEntity.setId(id);

        //add game to set of ids players and games
        listOfPlayersAndGames.add(playerGameEntity);

        return idGame;
    }
}
