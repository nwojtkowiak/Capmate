package com.capgemini.jstk.capmates.capmates.playability.repository;

import com.capgemini.jstk.capmates.capmates.enums.Days;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PlayabilityDAO {

    private List<PlayabilityEntity> listOfPlayability;

    public PlayabilityDAO(){
        this.listOfPlayability = new LinkedList<>();
    }

    public int addNewTerm(PlayabilityEntity playabilityEntity){

        int id = listOfPlayability.size() + 1;
        playabilityEntity.setId(id);
        listOfPlayability.add(playabilityEntity);

        return id;
    }

    public void removeTerm(int playabilityId){

        PlayabilityEntity playabilityEntity = new PlayabilityEntity();
        for(PlayabilityEntity entity : listOfPlayability){
            if(entity.getId() == playabilityId){
                playabilityEntity = entity;
                break;
            }
        }

        listOfPlayability.remove(playabilityEntity);
    }

    public void addCommentToTerm(int playabilityId, String comment){
        int indexEntity = -1;
        for(PlayabilityEntity entity : listOfPlayability){
            if(entity.getId() == playabilityId){
               indexEntity = entity.getId();
            }
        }
        listOfPlayability.get(indexEntity).setComment(comment);
    }

    public List<PlayabilityEntity> getListSimilarPlayability(int playerId){

        List<PlayabilityEntity> listWithSimilar = new LinkedList<>();
        List<PlayabilityEntity> listAllWithoutPlayer = listOfPlayability.stream().filter(p -> p.getPlayerId() != playerId).collect(Collectors.toList());
        List<PlayabilityEntity> listAllWithPlayer = listOfPlayability.stream().filter(p -> p.getPlayerId() == playerId).collect(Collectors.toList());

        for(PlayabilityEntity playabilityEntity : listAllWithPlayer) {

            Days day = playabilityEntity.getDay();
            LocalTime from = playabilityEntity.getFrom();
            LocalTime to = playabilityEntity.getTo();

            listWithSimilar.addAll(listAllWithoutPlayer.stream().filter(p -> p.getDay() == day && (p.getFrom().equals(from) || p.getFrom().isAfter(from) && p.getFrom().isBefore(to))).collect(Collectors.toList()));
            listWithSimilar.addAll(listAllWithoutPlayer.stream().filter(p -> p.getDay() == day && (p.getFrom().isBefore(from) && p.getTo().isAfter(from))).collect(Collectors.toList()));
        }

        return  listWithSimilar;
    }
}
