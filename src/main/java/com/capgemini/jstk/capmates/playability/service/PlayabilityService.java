package com.capgemini.jstk.capmates.playability.service;

import com.capgemini.jstk.capmates.mapper.MapperPlayability;
import com.capgemini.jstk.capmates.playability.repository.PlayabilityDAO;
import com.capgemini.jstk.capmates.playability.repository.PlayabilityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayabilityService {

    private PlayabilityDAO playabilityDAO;
    private MapperPlayability mapperPlayability;

    @Autowired
    public PlayabilityService(MapperPlayability mapperPlayability,PlayabilityDAO playabilityDAO){
        this.mapperPlayability = mapperPlayability;
        this.playabilityDAO = playabilityDAO;
    }

    public int addNewTerm(PlayabilityDTO playabilityDTO){
        PlayabilityEntity playabilityEntity = mapperPlayability.mapfromDTO(playabilityDTO);
        return playabilityDAO.addNewTerm(playabilityEntity);
    }

    public int removeTerm(int playabilityId){
        return playabilityDAO.removeTerm(playabilityId);

    }

    public List<PlayabilityDTO> getPlayerWithSimilarPlayability(int playerId){
        List<PlayabilityEntity> playabilityEntity = playabilityDAO.getListSimilarPlayability(playerId);
        return mapperPlayability.mapfromListDAO(playabilityEntity);

    }

    public int addCommentToTerm(int playabilityId, String comment){
        return playabilityDAO.addCommentToTerm(playabilityId, comment);
    }

    public List<PlayabilityDTO> getAllPlayability(){
        return mapperPlayability.mapfromListDAO(playabilityDAO.getAllPlayability());
    }
}
