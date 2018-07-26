package com.capgemini.jstk.capmates.playability.repository;

import com.capgemini.jstk.capmates.enums.Days;
import org.springframework.stereotype.Repository;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PlayabilityDAO {

    private List<PlayabilityEntity> listOfPlayability;

    public PlayabilityDAO() {
        this.listOfPlayability = new LinkedList<>();
    }

    public int addNewTerm(PlayabilityEntity playabilityEntity) {

        int id = listOfPlayability.size() + 1;
        playabilityEntity.setId(id);
        listOfPlayability.add(playabilityEntity);

        return id;
    }

    public void removeTerm(int playabilityId) {

        PlayabilityEntity playabilityEntity = listOfPlayability.stream().filter(p -> p.getId() == playabilityId).findFirst().get();

        listOfPlayability.remove(playabilityEntity);
    }

    public void addCommentToTerm(int playabilityId, String comment) {
        int indexEntity = -1;
        for (PlayabilityEntity entity : listOfPlayability) {
            if (entity.getId() == playabilityId) {
                indexEntity = entity.getId();
            }
        }
        listOfPlayability.get(indexEntity).setComment(comment);
    }

    public List<PlayabilityEntity> getListSimilarPlayability(int playerId) {

        List<PlayabilityEntity> listWithSimilar = new LinkedList<>();
        List<PlayabilityEntity> listAllWithoutPlayer = listOfPlayability.stream().filter(p -> p.getPlayerId() != playerId).collect(Collectors.toList());
        List<PlayabilityEntity> listAllWithPlayer = listOfPlayability.stream().filter(p -> p.getPlayerId() == playerId).collect(Collectors.toList());

        for (PlayabilityEntity playabilityEntity : listAllWithPlayer) {

            Days day = playabilityEntity.getDay();
            LocalTime from = playabilityEntity.getFrom();
            LocalTime to = playabilityEntity.getTo();

            listWithSimilar.addAll(listAllWithoutPlayer.stream().filter(p ->
                    checkDateWhenFromIsEqualOrAfterAndBeforeTo(day, from, to, p) || checkDateWhenFromIsBeforeFromAndToAfterFrom(day, from, to, p)
            ).collect(Collectors.toList()));

        }

        return listWithSimilar;
    }

    private boolean checkDateWhenFromIsEqualOrAfterAndBeforeTo(Days day, LocalTime from, LocalTime to, PlayabilityEntity p) {
        return p.getDay() == day && (p.getFrom().equals(from) || p.getFrom().isAfter(from) && p.getFrom().isBefore(to));
    }

    private boolean checkDateWhenFromIsBeforeFromAndToAfterFrom(Days day, LocalTime from, LocalTime to, PlayabilityEntity p) {
        return p.getDay() == day && (p.getFrom().isBefore(from) && p.getTo().isAfter(from));
    }
}
