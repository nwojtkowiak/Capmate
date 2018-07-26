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

    /**
     * This method return set of GameEntity for player with id playerId
     * @param idPlayer - id player's
     * @return set of GameEntity
     */
    public Set<GameEntity> getListGamesOfPlayer(int idPlayer) {
        final List<Integer> listIdGames = listOfPlayersAndGames.stream().filter(p -> p.getPlayerId() == idPlayer).map(p -> p.getGameId()).collect(Collectors.toList());

        Set<GameEntity> listGames = gameDao.getGames();

        return listGames.stream().filter(p -> listIdGames.contains(p.getId())).collect(Collectors.toSet());

    }


    /**
     * This method return list of ids of players whose have game with gameId
     * @param gameId
     * @return
     */
    public List<Integer> getListPlayersOfGames(int gameId) {
        List<Integer> listIdPlayers = listOfPlayersAndGames.stream().filter(p -> p.getGameId() == gameId).map(p -> p.getPlayerId()).collect(Collectors.toList());

        return listIdPlayers;

    }

    /**
     * This method help in init data to test
     * @param numberOfRows - numberOfRows can't be greater than number of rows in lisOfGames
     */

    public void initListOfPlayersAndGames(int numberOfRows) {
        for (int i = 0; i < numberOfRows; i++) {
            PlayerGameEntity playerGameEntity = new PlayerGameEntity();
            playerGameEntity.setId(i);
            playerGameEntity.setGameId(i);
            playerGameEntity.setPlayerId(0);
            listOfPlayersAndGames.add(playerGameEntity);
        }
    }

    /**
     * This method add information which player has game with gameId and call addGame method in GameDAO
     * @param game - entity of Game
     * @param playerId - player id
     * @return new id rows in list with players and games
     */
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
