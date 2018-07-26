package com.capgemini.jstk.capmates.mapper;

import com.capgemini.jstk.capmates.history.repository.HistoryEntity;
import com.capgemini.jstk.capmates.history.service.HistoryDTO;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperHistory implements MapperObject<HistoryEntity, HistoryDTO> {

    @Override
    public HistoryDTO mapFromDAO(HistoryEntity object) {

        if (object != null) {

            HistoryDTO historyDTO = new HistoryDTO();
            historyDTO.setId(object.getId());
            historyDTO.setPlayerId(object.getPlayerId());
            historyDTO.setGameId(object.getGameId());
            historyDTO.setResultGame(object.getResultGame());
            historyDTO.setDate(object.getDate());

            return historyDTO;
        }

        throw new NullPointerException();
    }

    @Override
    public HistoryEntity mapfromDTO(HistoryDTO object) {
        if (object != null) {

            HistoryEntity historyEntity = new HistoryEntity();
            historyEntity.setId(object.getId());
            historyEntity.setPlayerId(object.getPlayerId());
            historyEntity.setGameId(object.getGameId());
            historyEntity.setResultGame(object.getResultGame());
            historyEntity.setDate(object.getDate());

            return historyEntity;

        }

        throw new NullPointerException();
    }

    public List<HistoryDTO> mapfromListDAO(List<HistoryEntity> listObject) {

        List<HistoryDTO> allHistory = new ArrayList<>();
        for (HistoryEntity hist : listObject) {
            allHistory.add(mapFromDAO(hist));
        }

        return allHistory;
    }

    public List<HistoryEntity> mapfromListDTO(List<HistoryDTO> listObject) {

        List<HistoryEntity> allHistory = new ArrayList<>();
        for (HistoryDTO hist : listObject) {
            allHistory.add(mapfromDTO(hist));
        }

        return allHistory;
    }

}
