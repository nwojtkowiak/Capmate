package com.capgemini.jstk.capmates.history.service;

import com.capgemini.jstk.capmates.history.repository.HistoryDAO;
import com.capgemini.jstk.capmates.history.repository.HistoryEntity;
import com.capgemini.jstk.capmates.mapper.MapperHistory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HistoryService {

    private HistoryDAO historyDAO;
    private MapperHistory mapperHistory;


    public HistoryService(MapperHistory mapperHistory, HistoryDAO historyDAO) {
        this.mapperHistory = mapperHistory;
        this.historyDAO = historyDAO;
    }


    public List<HistoryDTO> getHistoryForPlayer(int playerId) {
        List<HistoryEntity> historyEntities = historyDAO.getPlayedGamesForPlayer(playerId);
        return mapperHistory.mapfromListDAO(historyEntities);
    }

    public List<HistoryDTO> getPlayedGamesForPlayerWithDate(int playerId, LocalDate date) {
        List<HistoryEntity> historyEntities = historyDAO.getPlayedGamesForPlayerWithDate(playerId, date);
        return mapperHistory.mapfromListDAO(historyEntities);
    }

    public List<HistoryDTO> getPlayedGamesForPlayerBetweemDates(int playerId, LocalDate dateFrom, LocalDate dateTo) {
        List<HistoryEntity> historyEntities = historyDAO.getPlayedGamesForPlayerBetweemDates(playerId, dateFrom, dateTo);
        return mapperHistory.mapfromListDAO(historyEntities);
    }

    public void addNewPlayedGame(List<HistoryDTO> historyDTO) {
        List<HistoryEntity> historyEntities = mapperHistory.mapfromListDTO(historyDTO);
        historyDAO.addNewPlayedGame(historyEntities);
    }

    public List<HistoryDTO> getWinGamesForPlayer(int playerId) {
        List<HistoryEntity> historyEntities = historyDAO.getWinGamesForPlayer(playerId);
        return mapperHistory.mapfromListDAO(historyEntities);
    }

    public List<HistoryDTO> getLoseGamesForPlayer(int playerId) {
        List<HistoryEntity> historyEntities = historyDAO.getLoseGamesForPlayer(playerId);
        return mapperHistory.mapfromListDAO(historyEntities);
    }

    public List<HistoryDTO> getRemisGamesForPlayer(int playerId) {
        List<HistoryEntity> historyEntities = historyDAO.getRemisGamesForPlayer(playerId);
        return mapperHistory.mapfromListDAO(historyEntities);
    }
}