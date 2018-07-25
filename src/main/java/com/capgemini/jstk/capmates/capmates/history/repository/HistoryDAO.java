package com.capgemini.jstk.capmates.capmates.history.repository;

import com.capgemini.jstk.capmates.capmates.enums.ResultGame;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class HistoryDAO {

    private List<HistoryEntity> listOfHistory;

    public HistoryDAO(){
        this.listOfHistory = new LinkedList<>();
    }

    public List<HistoryEntity> getPlayedGamesForPlayer(int playerId){
        return listOfHistory.stream().filter(p -> p.getPlayerId() == playerId).collect(Collectors.toList());
    }

    public List<HistoryEntity> getPlayedGamesForPlayerWithDate(int playerId, Date date){
        List<HistoryEntity> playedGames = getPlayedGamesForPlayer(playerId);
        return playedGames.stream().filter(p -> p.getDate().compareTo(date) == 0).collect(Collectors.toList());
    }
    public List<HistoryEntity> getPlayedGamesForPlayerBetweemDates(int playerId, Date dateFrom, Date dateTo){
        List<HistoryEntity> playedGames = getPlayedGamesForPlayer(playerId);
        return playedGames.stream().filter(p -> p.getDate().after(dateFrom) && p.getDate().before(dateTo)).collect(Collectors.toList());
    }

    public void addNewPlayedGame(List<HistoryEntity> historyEntities){
        listOfHistory.addAll(historyEntities);
    }

    public List<HistoryEntity> getWinGamesForPlayer(int playerId){
        return listOfHistory.stream().filter(p-> (p.getPlayerId() == playerId && p.getResultGame() == ResultGame.WIN)).collect(Collectors.toList());
    }

    public List<HistoryEntity> getLoseGamesForPlayer(int playerId){
        return listOfHistory.stream().filter(p->(p.getPlayerId() == playerId && p.getResultGame() == ResultGame.LOSE)).collect(Collectors.toList());
    }

    public List<HistoryEntity> getRemisGamesForPlayer(int playerId){
        return listOfHistory.stream().filter(p->(p.getPlayerId() == playerId && p.getResultGame() == ResultGame.REMIS)).collect(Collectors.toList());
    }

    public void init(int numberOfRowInHistory){
        for(int i = 0; i < numberOfRowInHistory; i++){
            HistoryEntity historyEntity = new HistoryEntity();
            historyEntity.setId(i);
            historyEntity.setPlayerId(0);
            historyEntity.setGameId(i);
            historyEntity.setResultGame( ResultGame.LOSE);
            historyEntity.setDate(new Date());

            listOfHistory.add(historyEntity);
        }
    }
}
