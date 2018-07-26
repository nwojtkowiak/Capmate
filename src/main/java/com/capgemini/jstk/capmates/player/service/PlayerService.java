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

@Service
public class PlayerService {

    private MapperPlayer mapperUser;
    private MapperGame mapperGame;
   // private MapperRanking mapperRanking;
    private PlayerDAO playerDAO;
    private PlayerGameDAO playerGameDAO;
  //  private RankingDAO rankingDAO;

    @Autowired
    public PlayerService(MapperPlayer mapperUser, MapperGame mapperGame,
                         PlayerDAO playerDAO, PlayerGameDAO playerGameDAO){
        this.mapperUser = mapperUser;
        this.playerDAO = playerDAO;
     //   this.mapperRanking = mapperRanking;
        this.playerGameDAO = playerGameDAO;
        this.mapperGame = mapperGame;
     //   this.rankingDAO = rankingDAO;
    }

    public PlayerDTO getPlayerInformation(int id) throws PlayerNotExist {
        try {
            PlayerEntity user = playerDAO.getUserById(id);
            return mapperUser.mapFromDAO(user);
        }catch(NullPointerException e){
            throw new PlayerNotExist();
        }

    }

    public PlayerDTO savePlayerInformation(PlayerDTO playerDTO){
         playerDAO.updateUser(mapperUser.mapfromDTO(playerDTO));
         return playerDTO;
    }

//    public void addPlayer(PlayerDTO playerDTO){
//        PlayerEntity player = savePlayerInformation(playerDTO);
//        playerDAO.updateUser(player);
//    }

    public PlayerDTO addPlayer(String email){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setLevel(Level.BEGINNER);
        playerDTO.setEmail(email);

        //nie jest wielowatkowe
        int id = playerDAO.getNumberOfAllPlayers();
        playerDTO.setId(id);

        PlayerEntity player = mapperUser.mapfromDTO(playerDTO);
        playerDAO.updateUser(player);


        return playerDTO;
    }


    public PlayerDTO changePassword(PlayerDTO playerDTO, String oldPassword, String newPassword){

        PlayerDTO testPlayerDto = mapperUser.mapFromDAO(playerDAO.getUserById(playerDTO.getId()));

        if(testPlayerDto.getPassword().equals(oldPassword)) {
            testPlayerDto.setPassword(newPassword);
            savePlayerInformation(testPlayerDto);
        }

        return testPlayerDto;
    }


    public int addGame(PlayerDTO playerDTO, GameDTO gameDTO){
        //add game to player
        playerDTO.getGames().add(gameDTO);
        PlayerEntity rankingEntity = mapperUser.mapfromDTO(playerDTO);
        playerDAO.updateUser(rankingEntity);

        //add game to playersGames and games
        GameEntity gameEntity = mapperGame.mapfromDTO(gameDTO);
        return playerGameDAO.addGame(gameEntity, playerDTO.getId());


    }

    public void removeGame(PlayerDTO playerDTO, GameDTO gameDTO){
        playerDTO.getGames().remove(gameDTO);
        PlayerEntity rankingEntity = mapperUser.mapfromDTO(playerDTO);
        playerDAO.updateUser(rankingEntity);
    }


    public Level countLevel(long points){
        return Level.getLevelByPoints(points);
    }





}
