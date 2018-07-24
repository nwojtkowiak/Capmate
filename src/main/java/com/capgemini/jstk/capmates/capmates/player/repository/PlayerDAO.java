package com.capgemini.jstk.capmates.capmates.player.repository;

import com.capgemini.jstk.capmates.capmates.exception.PlayerNotExist;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PlayerDAO {

    private Map<Integer,PlayerEntity> listOfUser;

    public PlayerDAO(){
        this.listOfUser = new HashMap<>();
    }

    public Set<PlayerEntity> getUsers(){
        return listOfUser.values().stream().collect(Collectors.toSet());
    }

    public PlayerEntity getUserById(int id){
        return listOfUser.get(id);
    }

    public PlayerEntity getUserByEmail(String email) throws PlayerNotExist {

        for(PlayerEntity player : listOfUser.values()){
            if(player.getEmail().equals(email)){
                return player;
            }
        }

        throw new PlayerNotExist();
    }


    public void updateUser(PlayerEntity user){
        listOfUser.put(user.getId(), user);
    }

    public void addUser(PlayerEntity user){
        listOfUser.put(user.getId(), user);
    }


    public int getNumberOfAllPlayers(){
        return listOfUser.size();
    }



    public void initListOfUsers(int numberOfUsers){


        for(int i = 0; i < numberOfUsers;  i++) {
            PlayerEntity player = new PlayerEntity();
            player.setEmail("player"+i+"@cap.com");
            player.setFirstName("First "+i);
            player.setLastName("Last "+i);
            player.setMotto("Hakuna Matata "+i);
            player.setPassword("password");
            player.setId(i);
            listOfUser.put(i, player);
        }
    }


   }
