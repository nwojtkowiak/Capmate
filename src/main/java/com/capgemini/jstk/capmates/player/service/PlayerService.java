package com.capgemini.jstk.capmates.player.service;

import com.capgemini.jstk.capmates.enums.Level;
import com.capgemini.jstk.capmates.exception.PlayerNotExist;
import com.capgemini.jstk.capmates.game.repository.GameEntity;
import com.capgemini.jstk.capmates.game.repository.PlayerGameDAO;
import com.capgemini.jstk.capmates.game.service.GameDTO;
import com.capgemini.jstk.capmates.mapper.MapperGame;
import com.capgemini.jstk.capmates.mapper.MapperPlayer;
import com.capgemini.jstk.capmates.player.repository.PlayerDAO;
import com.capgemini.jstk.capmates.player.repository.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PlayerService {

    private MapperPlayer mapperPlayer;
    private MapperGame mapperGame;
    private PlayerDAO playerDAO;
    private PlayerGameDAO playerGameDAO;

    @Autowired
    public PlayerService(MapperPlayer mapperPlayer, MapperGame mapperGame,
                         PlayerDAO playerDAO, PlayerGameDAO playerGameDAO) {

        this.mapperPlayer = mapperPlayer;
        this.playerDAO = playerDAO;
        this.playerGameDAO = playerGameDAO;
        this.mapperGame = mapperGame;
    }

    /**
     * This method return dto of player with id.
     * When player with this id doesn't exist then throw PlayerNotExist
     *
     * @param id - id of player
     * @return information of player as PlayerDTO
     * @throws PlayerNotExist
     */
    public PlayerDTO getPlayerInformation(int id) throws PlayerNotExist {

        try {
            PlayerEntity user = playerDAO.getUserById(id);
            return mapperPlayer.mapFromDAO(user);
        } catch (NullPointerException e) {
            throw new PlayerNotExist();
        }

    }

    /**
     * This method save information of player to data base
     *
     * @param playerDTO - DTO of player
     * @return information of player which was updated as PlayerDTO
     */
    public PlayerDTO savePlayerInformation(PlayerDTO playerDTO) {

        playerDAO.updateUser(mapperPlayer.mapfromDTO(playerDTO));
        return playerDTO;
    }

    /**
     * This method add new player
     *
     * @param playerDTO - information about new player
     * @return information of player which was added as PlayerDTO
     */
    public PlayerDTO addPlayer(PlayerDTO playerDTO) {

        PlayerEntity player = mapperPlayer.mapfromDTO(playerDTO);
        return mapperPlayer.mapFromDAO(playerDAO.addPlayer(player));
    }


    /**
     * This method change password for user. When old password is equal to password in data base then password will be change
     *
     * @param playerDTO   - information about player which want to change password
     * @param oldPassword - password which will be change
     * @param newPassword - password which will be save
     * @return information of player as PlayerDTO with change password
     */
    public PlayerDTO changePassword(PlayerDTO playerDTO, String oldPassword, String newPassword) {

        PlayerDTO testPlayerDto = mapperPlayer.mapFromDAO(playerDAO.getUserById(playerDTO.getId()));

        if (testPlayerDto.getPassword().equals(oldPassword)) {
            testPlayerDto.setPassword(newPassword);
            savePlayerInformation(testPlayerDto);
        }

        return testPlayerDto;
    }


    /**
     * This method add new game for player. This method call add game to list of all games.
     *
     * @param playerDTO - information of player as PlayerDTO
     * @param gameDTO   - information of game as GameDTO
     * @return id new game
     */
    public int addGame(PlayerDTO playerDTO, GameDTO gameDTO) {

        //add game to player
        playerDTO.getGames().add(gameDTO);
        PlayerEntity rankingEntity = mapperPlayer.mapfromDTO(playerDTO);
        playerDAO.updateUser(rankingEntity);

        //add game to playersGames and games
        GameEntity gameEntity = mapperGame.mapfromDTO(gameDTO);
        return playerGameDAO.addGame(gameEntity, playerDTO.getId());


    }

    /**
     * This method remove game only from list of game player's
     *
     * @param playerDTO - information about player as PlayerDTO
     * @param gameDTO   - information about game as GameDTO
     */
    public void removeGame(PlayerDTO playerDTO, GameDTO gameDTO) {
        playerDTO.getGames().remove(gameDTO);
        PlayerEntity rankingEntity = mapperPlayer.mapfromDTO(playerDTO);
        playerDAO.updateUser(rankingEntity);
    }


    /**
     * This method count level for player by points.
     *
     * @param points - points which has a player
     * @return level of player - BEGINNER when has less than 500 points
     * INTERMEDIATE when less than 1000 points
     * ADVANCED when has 1000 or more points
     */
    public Level countLevel(long points) {
        return Level.getLevelByPoints(points);
    }

    /**
     * This method return set of game which has player with playerId
     *
     * @param playerId - player id
     * @return set of information of games as GameDTO
     */
    public Set<GameDTO> getGamesOfPlayer(int playerId) {
        Set<GameEntity> setOfGamesById = playerGameDAO.getListGamesOfPlayer(playerId);
        return mapperGame.mapfromSetDAO(setOfGamesById);
    }

    /**
     * This method return set of all players
     *
     * @return set of information of players as PlayerDTO
     */
    public List<PlayerDTO> getAllPlayers() {
        List<PlayerEntity> setOfPlayers = playerDAO.getPlayers();
        return mapperPlayer.mapfromSetDAO(setOfPlayers);
    }

    public List<PlayerDTO> searchByFields(PlayerDTO playerDTO) {
        List<PlayerEntity> results = new ArrayList<>();
        List<PlayerEntity> foundPlayers;
        boolean checked = false;

        if (playerDTO.getFirstName().length() > 0) {
            foundPlayers = playerDAO.searchByFirstName(playerDTO.getFirstName());
            results.addAll(foundPlayers);
            checked = true;
        }
        if (playerDTO.getLastName().length() > 0) {
            foundPlayers = playerDAO.searchByLastName(playerDTO.getLastName());
            if (checked) {
                results.retainAll(foundPlayers);

            } else {
                results.addAll(foundPlayers);
                checked = true;
            }

        }
        if (playerDTO.getEmail().length() > 0) {
            foundPlayers = playerDAO.searchByEmail(playerDTO.getEmail());
            if (checked) {
                results.retainAll(foundPlayers);
            } else {
                results.addAll(foundPlayers);
                checked = true;
            }
        }
        if (playerDTO.getMotto().length() > 0) {
            foundPlayers = playerDAO.searchByMotto(playerDTO.getMotto());
            if (checked) {
                results.retainAll(foundPlayers);
            } else {
                results.addAll(foundPlayers);
                checked = true;
            }
        }
        //TODO
        /*
        if(playerDTO.getGames().size() > 0){

        }*/

        return mapperPlayer.mapfromSetDAO(results);
    }


}
