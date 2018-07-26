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

import java.util.Set;

@Service
public class PlayerService {

    private MapperPlayer mapperPlayer;
    private MapperGame mapperGame;
    private PlayerDAO playerDAO;
    private PlayerGameDAO playerGameDAO;

    @Autowired
    public PlayerService(MapperPlayer mapperUser, MapperGame mapperGame,
                         PlayerDAO playerDAO, PlayerGameDAO playerGameDAO) {

        this.mapperPlayer = mapperUser;
        this.playerDAO = playerDAO;
        this.playerGameDAO = playerGameDAO;
        this.mapperGame = mapperGame;
    }

    public PlayerDTO getPlayerInformation(int id) throws PlayerNotExist {

        try {
            PlayerEntity user = playerDAO.getUserById(id);
            return mapperPlayer.mapFromDAO(user);
        } catch (NullPointerException e) {
            throw new PlayerNotExist();
        }

    }

    public PlayerDTO savePlayerInformation(PlayerDTO playerDTO) {

        playerDAO.updateUser(mapperPlayer.mapfromDTO(playerDTO));
        return playerDTO;
    }

    public PlayerDTO addPlayer(PlayerDTO playerDTO) {

        PlayerEntity player = mapperPlayer.mapfromDTO(playerDTO);
        playerDAO.addPlayer(player);
        return playerDTO;
    }


    public PlayerDTO changePassword(PlayerDTO playerDTO, String oldPassword, String newPassword) {

        PlayerDTO testPlayerDto = mapperPlayer.mapFromDAO(playerDAO.getUserById(playerDTO.getId()));

        if (testPlayerDto.getPassword().equals(oldPassword)) {
            testPlayerDto.setPassword(newPassword);
            savePlayerInformation(testPlayerDto);
        }

        return testPlayerDto;
    }


    public int addGame(PlayerDTO playerDTO, GameDTO gameDTO) {

        //add game to player
        playerDTO.getGames().add(gameDTO);
        PlayerEntity rankingEntity = mapperPlayer.mapfromDTO(playerDTO);
        playerDAO.updateUser(rankingEntity);

        //add game to playersGames and games
        GameEntity gameEntity = mapperGame.mapfromDTO(gameDTO);
        return playerGameDAO.addGame(gameEntity, playerDTO.getId());


    }

    public void removeGame(PlayerDTO playerDTO, GameDTO gameDTO) {
        playerDTO.getGames().remove(gameDTO);
        PlayerEntity rankingEntity = mapperPlayer.mapfromDTO(playerDTO);
        playerDAO.updateUser(rankingEntity);
    }


    public Level countLevel(long points) {
        return Level.getLevelByPoints(points);
    }

    public Set<GameDTO> getGamesOfPlayer(int playerId) {
        Set<GameEntity> setOfGamesById = playerGameDAO.getListGamesOfPlayer(playerId);
        return mapperGame.mapfromSetDAO(setOfGamesById);
    }

    public Set<PlayerDTO> getAllPlayers() {
        Set<PlayerEntity> setOfPlayers = playerDAO.getPlayers();
        return mapperPlayer.mapfromSetDAO(setOfPlayers);
    }


}
