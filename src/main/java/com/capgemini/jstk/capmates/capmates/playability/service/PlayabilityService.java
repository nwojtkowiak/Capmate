package com.capgemini.jstk.capmates.capmates.playability.service;

import com.capgemini.jstk.capmates.capmates.mapper.MapperPlayability;
import com.capgemini.jstk.capmates.capmates.playability.repository.PlayabilityDAO;
import com.capgemini.jstk.capmates.capmates.playability.repository.PlayabilityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void removeTerm(int playabilityId){
        playabilityDAO.removeTerm(playabilityId);

    }
}
