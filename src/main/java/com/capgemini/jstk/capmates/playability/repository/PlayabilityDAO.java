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

    /**
     * This method add new term playability to list of playability
     * @param playabilityEntity - entity of playability
     * @return id new row of list
     */
    public int addNewTerm(PlayabilityEntity playabilityEntity) {

        int id = listOfPlayability.size() + 1;
        playabilityEntity.setId(id);
        listOfPlayability.add(playabilityEntity);

        return id;
    }

    /**
     * This method remove term from list
     * @param playabilityId - id of entity playability
     * @return id removed entity
     */
    public int removeTerm(int playabilityId) {

        PlayabilityEntity playabilityEntity = listOfPlayability.stream().filter(p -> p.getId() == playabilityId).findFirst().get();

        listOfPlayability.remove(playabilityEntity);

        return playabilityId;
    }

    /**
     * This method add comment to term
     * @param playabilityId - id of playability entity
     * @param comment - comment to add
     * @return index in list of entity with playabilityId
     */
    public int addCommentToTerm(int playabilityId, String comment) {
        int indexEntity = -1;
        for (PlayabilityEntity entity : listOfPlayability) {
            if (entity.getId() == playabilityId) {
                indexEntity = entity.getId();
            }
        }
        listOfPlayability.get(indexEntity).setComment(comment);

        return indexEntity;
    }

    public List<PlayabilityEntity> getAllPlayability() {
        return listOfPlayability;
    }

    public List<PlayabilityEntity> getPlayabilityPlayer(int playerId) {
        return listOfPlayability.stream().filter(p -> p.getPlayerId() == playerId).collect(Collectors.toList());
    }

    /**
     * This method create list of similar playability to playability of player with id playerId.
     * It compare every term of player with player id with others players term
     * @param playerId - player id
     * @return list of similar playability
     */
    public List<PlayabilityEntity> getListSimilarPlayability(int playerId) {

        List<PlayabilityEntity> listWithSimilar = new LinkedList<>();
        List<PlayabilityEntity> listAllWithoutPlayer = listOfPlayability.stream().filter(p -> p.getPlayerId() != playerId).collect(Collectors.toList());
        List<PlayabilityEntity> listAllWithPlayer = getPlayabilityPlayer(playerId);

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
