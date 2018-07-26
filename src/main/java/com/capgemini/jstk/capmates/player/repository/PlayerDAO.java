package com.capgemini.jstk.capmates.player.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PlayerDAO {

    private Map<Integer, PlayerEntity> listOfPlayers;

    public PlayerDAO() {
        this.listOfPlayers = new HashMap<>();
    }

    public Set<PlayerEntity> getPlayers() {
        return listOfPlayers.values().stream().collect(Collectors.toSet());
    }

    public PlayerEntity getUserById(int id) {
        return listOfPlayers.get(id);
    }

    public void updateUser(PlayerEntity player) {
        listOfPlayers.put(player.getId(), player);
    }

    public void addPlayer(PlayerEntity player) {
        int id = listOfPlayers.size() + 1;
        player.setId(id);
        listOfPlayers.put(player.getId(), player);
    }


    /**
     * This method help to create testing date
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


}
