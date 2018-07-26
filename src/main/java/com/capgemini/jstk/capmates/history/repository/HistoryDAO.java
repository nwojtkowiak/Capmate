package com.capgemini.jstk.capmates.history.repository;

import com.capgemini.jstk.capmates.enums.ResultGame;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class HistoryDAO {

    private List<HistoryEntity> listOfHistory;

    public HistoryDAO() {
        this.listOfHistory = new LinkedList<>();
    }

    /**
     * This method return list of history entity for player
     *
     * @param playerId - - player id
     * @return list of history entity
     */
    public List<HistoryEntity> getPlayedGamesForPlayer(int playerId) {
        return listOfHistory.stream().filter(p -> p.getPlayerId() == playerId).collect(Collectors.toList());
    }

    /**
     * This method return list of history entity for player when game was played in @param date
     *
     * @param playerId - player id
     * @param date     - date of played game
     * @return list of history entity
     */
    public List<HistoryEntity> getPlayedGamesForPlayerWithDate(int playerId, LocalDate date) {
        List<HistoryEntity> playedGames = getPlayedGamesForPlayer(playerId);
        return playedGames.stream().filter(p -> p.getDate().compareTo(date) == 0).collect(Collectors.toList());
    }

    /**
     * This method return list of history entity for player when game was played between @param dateFrom and @param dateTo
     *
     * @param playerId - player id
     * @param dateFrom - min date of played game
     * @param dateTo   - max date of played game
     * @return list of history entity
     */
    public List<HistoryEntity> getPlayedGamesForPlayerBetweenDates(int playerId, LocalDate dateFrom, LocalDate dateTo) {
        List<HistoryEntity> playedGames = getPlayedGamesForPlayer(playerId);
        return playedGames.stream().filter(p -> p.getDate().isAfter(dateFrom) && p.getDate().isBefore(dateTo)).collect(Collectors.toList());
    }

    /**
     * This method add list of played games with result to list of history
     *
     * @param historyEntities - list of result for every player
     */
    public void addNewPlayedGame(List<HistoryEntity> historyEntities) {

        listOfHistory.addAll(historyEntities);
    }

    /**
     * This method return list of game which player win
     *
     * @param playerId - player id
     * @return - list of history entity
     */
    public List<HistoryEntity> getWinGamesForPlayer(int playerId) {
        return listOfHistory.stream().filter(checkPlayerAndResultGame(playerId, ResultGame.WIN)).collect(Collectors.toList());
    }

    /**
     * This method return list of game which player lose
     *
     * @param playerId - player id
     * @return - list of history entity
     */
    public List<HistoryEntity> getLoseGamesForPlayer(int playerId) {
        return listOfHistory.stream().filter(checkPlayerAndResultGame(playerId, ResultGame.LOSE)).collect(Collectors.toList());
    }

    /**
     * This method return list of game which player had remis
     *
     * @param playerId - player id
     * @return - list of history entity
     */
    public List<HistoryEntity> getRemisGamesForPlayer(int playerId) {
        return listOfHistory.stream().filter(checkPlayerAndResultGame(playerId, ResultGame.REMIS)).collect(Collectors.toList());
    }


    private Predicate<HistoryEntity> checkPlayerAndResultGame(int playerId, ResultGame resultGame) {
        return p -> p.getPlayerId() == playerId && p.getResultGame() == resultGame;
    }

    /**
     * This method help to generate testing data
     *
     * @param numberOfRowInHistory - number of rows history
     */
    public void init(int numberOfRowInHistory) {
        for (int i = 0; i < numberOfRowInHistory; i++) {
            HistoryEntity historyEntity = new HistoryEntity();
            historyEntity.setId(i);
            historyEntity.setPlayerId(0);
            historyEntity.setGameId(i);
            historyEntity.setResultGame(ResultGame.LOSE);
            historyEntity.setDate(LocalDate.now());

            listOfHistory.add(historyEntity);
        }
    }
}
