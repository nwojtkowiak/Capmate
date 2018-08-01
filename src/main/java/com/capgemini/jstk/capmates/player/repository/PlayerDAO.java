package com.capgemini.jstk.capmates.player.repository;

import com.capgemini.jstk.capmates.game.repository.GameEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PlayerDAO {

    private Map<Integer, PlayerEntity> listOfPlayers;

    public PlayerDAO() {
        this.listOfPlayers = new HashMap<>();
    }

    public List<PlayerEntity> getPlayers() {
        return listOfPlayers.values().stream().collect(Collectors.toList());
    }

    public PlayerEntity getUserById(int id) {
        return listOfPlayers.get(id);
    }

    public void updateUser(PlayerEntity player) {
        listOfPlayers.put(player.getId(), player);
    }

    public PlayerEntity addPlayer(PlayerEntity player) {
        int id = listOfPlayers.size() + 1;
        player.setId(id);
        listOfPlayers.put(player.getId(), player);

        return player;
    }


    /**
     * This method help to create testing date
     *
     * @param numberOfPlayers - number of rows new data
     */
    public void initListOfUsers(int numberOfPlayers) {

        for (int i = 0; i < numberOfPlayers; i++) {
            PlayerEntity player = new PlayerEntity();
            player.setEmail("player" + i + "@cap.com");
            player.setFirstName("First " + i);
            player.setLastName("Last " + i);
            player.setMotto("Hakuna Matata " + i);
            player.setPassword("password");
            player.setId(i);
            listOfPlayers.put(i, player);
        }
    }

//    public Set<PlayerEntity> searchByAllFields(PlayerEntity playerEntity) {
//        String firstName = playerEntity.getFirstName();
//        String lastName = playerEntity.getLastName();
//        String email = playerEntity.getEmail();
//        String motto = playerEntity.getMotto();
//
//
//        return listOfPlayers.values().stream().filter(p ->
//                checkFirstName(firstName, p) && checkLastName(lastName, p) &&
//                        checkEmail(email, p) && checkMotto(motto, p)).collect(Collectors.toSet());
//    }

    public List<PlayerEntity> searchByFirstName(String firstName) {
        return listOfPlayers.values().stream().filter(p ->
                checkFirstName(firstName, p)).collect(Collectors.toList());
    }

    public List<PlayerEntity> searchByLastName(String lastName) {
        return listOfPlayers.values().stream().filter(p ->
                checkLastName(lastName, p)).collect(Collectors.toList());
    }

    public List<PlayerEntity> searchByEmail(String email) {
        return listOfPlayers.values().stream().filter(p -> checkEmail(email, p)).collect(Collectors.toList());
    }

    public List<PlayerEntity> searchByMotto(String motto) {
        return listOfPlayers.values().stream().filter(p -> checkMotto(motto, p)).collect(Collectors.toList());
    }

    public Set<PlayerEntity> searchByGames(Set<GameEntity> games) {
        Set<PlayerEntity> foundPlayers = new HashSet<>();
        for(PlayerEntity player : listOfPlayers.values()){
            for (GameEntity game : games){

            }
        }
        return  foundPlayers;
    }

    private boolean checkFirstName(String firstName, PlayerEntity p) {
        return p.getFirstName().equals(firstName);
    }

    private boolean checkLastName(String lastName, PlayerEntity p) {
        return p.getLastName().equals(lastName);
    }

    private boolean checkEmail(String email, PlayerEntity p) {
        return p.getEmail().equals(email);
    }

    private boolean checkMotto(String motto, PlayerEntity p) {
        return p.getMotto().equals(motto);
    }


}
