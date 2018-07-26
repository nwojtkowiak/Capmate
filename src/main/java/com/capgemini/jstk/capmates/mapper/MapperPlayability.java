package com.capgemini.jstk.capmates.mapper;

import com.capgemini.jstk.capmates.playability.repository.PlayabilityEntity;
import com.capgemini.jstk.capmates.playability.service.PlayabilityDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperPlayability implements MapperObject<PlayabilityEntity, PlayabilityDTO> {

    @Override
    public PlayabilityDTO mapFromDAO(PlayabilityEntity object) {

        PlayabilityDTO playabilityDTO = new PlayabilityDTO();
        playabilityDTO.setId(object.getId());
        playabilityDTO.setPlayerId(object.getPlayerId());
        playabilityDTO.setFrom(object.getFrom());
        playabilityDTO.setTo(object.getTo());
        playabilityDTO.setDay(object.getDay());
        playabilityDTO.setComment(object.getComment());

        return playabilityDTO;
    }

    @Override
    public PlayabilityEntity mapfromDTO(PlayabilityDTO object) {

        PlayabilityEntity playabilityEntity = new PlayabilityEntity();
        playabilityEntity.setId(object.getId());
        playabilityEntity.setPlayerId(object.getPlayerId());
        playabilityEntity.setFrom(object.getFrom());
        playabilityEntity.setTo(object.getTo());
        playabilityEntity.setDay(object.getDay());
        playabilityEntity.setComment(object.getComment());

        return playabilityEntity;
    }

    public List<PlayabilityDTO> mapfromListDAO(List<PlayabilityEntity> listObject) {

        List<PlayabilityDTO> allPlayability = new ArrayList<>();
        for (PlayabilityEntity playability : listObject) {
            allPlayability.add(mapFromDAO(playability));
        }

        return allPlayability;
    }
}
