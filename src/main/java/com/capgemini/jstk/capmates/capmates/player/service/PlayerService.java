package com.capgemini.jstk.capmates.capmates.player.service;

import com.capgemini.jstk.capmates.capmates.enums.Level;
import com.capgemini.jstk.capmates.capmates.exception.PlayerNotExist;
import com.capgemini.jstk.capmates.capmates.mapper.MapperPlayer;
import com.capgemini.jstk.capmates.capmates.player.repository.PlayerEntity;
import com.capgemini.jstk.capmates.capmates.player.repository.PlayerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private MapperPlayer mapperUser;
    private PlayerDAO dao;

    @Autowired
    public PlayerService(MapperPlayer mapperUser, PlayerDAO dao){
        this.mapperUser = mapperUser;
        this.dao = dao;
    }

    public PlayerDTO getPlayerInformation(int id) throws PlayerNotExist {
        try {
            PlayerEntity user = dao.getUserById(id);
            return mapperUser.mapFromDAO(user);
        }catch(NullPointerException e){
            throw new PlayerNotExist();
        }

    }

    public void savePlayerInformation(PlayerDTO playerDTO){
         dao.updateUser(mapperUser.mapfromDTO(playerDTO));
    }

//    public void addPlayer(PlayerDTO playerDTO){
//        PlayerEntity player = savePlayerInformation(playerDTO);
//        dao.updateUser(player);
//    }

    public PlayerDTO addPlayer(String email){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setLevel(Level.BEGINNER);
        playerDTO.setEmail(email);

        //nie jest wielowatkowe
        int id = dao.getNumberOfAllPlayers();
        playerDTO.setId(id);

        PlayerEntity player = mapperUser.mapfromDTO(playerDTO);
        dao.updateUser(player);


        return  playerDTO;
    }

    public PlayerDTO changeFirstName(PlayerDTO playerDTO, String firstName){

        playerDTO.setFirstName(firstName);
        savePlayerInformation(playerDTO);

        return playerDTO;
    }

    public PlayerDTO changeLastName(PlayerDTO playerDTO, String lasttName){

        playerDTO.setLastName(lasttName);
        savePlayerInformation(playerDTO);

        return playerDTO;
    }

    public PlayerDTO changeEmail(PlayerDTO playerDTO, String email){

        playerDTO.setEmail(email);
        savePlayerInformation(playerDTO);

        return playerDTO;
    }

    public PlayerDTO changeMotto(PlayerDTO playerDTO, String motto){

        playerDTO.setMotto(motto);
        savePlayerInformation(playerDTO);

        return playerDTO;
    }

    public PlayerDTO changePassword(PlayerDTO playerDTO, String oldPassword, String newPassword){

        PlayerDTO testPlayerDto = mapperUser.mapFromDAO(dao.getUserById(playerDTO.getId()));

        if(testPlayerDto.getPassword().equals(oldPassword)) {
            testPlayerDto.setPassword(newPassword);
            savePlayerInformation(testPlayerDto);
        }

        return testPlayerDto;
    }

   /* public void addGame(PlayerDTO playerDTO, GameDTO gameDTO){
        Set<GameDTO> games = playerDTO.getGames();
        games.add(gameDTO);

    }*/



}
