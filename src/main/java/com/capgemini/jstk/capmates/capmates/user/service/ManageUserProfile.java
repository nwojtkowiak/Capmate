package com.capgemini.jstk.capmates.capmates.user.service;

import com.capgemini.jstk.capmates.capmates.game.service.GameDTO;
import com.capgemini.jstk.capmates.capmates.mapper.MapperUser;
import com.capgemini.jstk.capmates.capmates.user.repository.User;
import com.capgemini.jstk.capmates.capmates.user.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ManageUserProfile {

    private MapperUser mapperUser;
    private UserDAO dao;

    @Autowired
    public ManageUserProfile(MapperUser mapperUser, UserDAO dao){
        this.mapperUser = mapperUser;
        this.dao = dao;
    }

    public UserDTO getUserInformation(int id){
        User user = dao.getUser(id);
        return mapperUser.mapFromDAO(user);
    }

    public User saveUserInformation(UserDTO userDTO){

        return mapperUser.mapfromDTO(userDTO);
    }

    public void addUser(UserDTO userDTO){
        User user = saveUserInformation(userDTO);
        dao.updateUser(user);
    }


    public void addGame(UserDTO userDTO, GameDTO gameDTO){
        Set<GameDTO> games = userDTO.getGames();
        games.add(gameDTO);

    }



}
