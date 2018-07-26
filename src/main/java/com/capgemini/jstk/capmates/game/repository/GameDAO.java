package com.capgemini.jstk.capmates.game.repository;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class GameDAO {

    private Map<Integer, GameEntity> listOfGames;

    public GameDAO() {
        this.listOfGames = new HashMap<>();
    }

    public Set<GameEntity> getGames() {
        return listOfGames.values().stream().collect(Collectors.toSet());
    }

    public GameEntity getGame(int id) {
        return listOfGames.get(id);
    }

    public void add(GameEntity game) {
        long countGame = listOfGames.values().stream().filter(p -> p.getName().equals(game.getName())).count();
        if (countGame == 0) {
            listOfGames.put(game.getId(), game);
        }
    }

    public void initListOfGames(int numberOfGames) {
        for (int i = 0; i < numberOfGames; i++) {
            GameEntity game = new GameEntity();
            game.setId(i);
            game.setIsNeedMore(false);
            game.setName("Game " + i);
            game.setNumberOfPlayers(i + 1);

            listOfGames.put(i, game);
        }
    }

    public int getSizeSetOfGames() {
        return listOfGames.size();
    }
}
