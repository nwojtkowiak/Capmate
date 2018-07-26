package com.capgemini.jstk.capmates.capmates.ranking.repository;

import com.capgemini.jstk.capmates.capmates.ranking.service.RankingDTO;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RankingDAO {

    private List<RankingEntity> listOfRanking;

    public RankingDAO(){
        listOfRanking = new LinkedList<>();
    }

    public long getPointsForPlayerAndGame(int playerId, int gameId){
        return listOfRanking.stream().filter(p -> p.getPlayerId() == playerId && p.getGameId() == gameId ).mapToLong(p -> p.getPoints()).sum();
    }

    //TODO sort this by points
    public Map<Integer, Long> getListOfPlayersWithPointsPerGame(int gameId){
        return listOfRanking.stream().filter(p -> p.getGameId() == gameId ).collect(
                Collectors.toMap(RankingEntity::getPlayerId, RankingEntity::getPoints));
    }

    public void addPointsForPlayer(int playerId, int gameId, long points){

        RankingEntity rankingEntity = listOfRanking.stream().filter(p -> p.getPlayerId() == playerId && p.getGameId() == gameId).findFirst().get();
        listOfRanking.remove(rankingEntity);

        long sumOfPoints = rankingEntity.getPoints() + points;
        rankingEntity.setPoints( sumOfPoints );

        listOfRanking.add(rankingEntity);

    }

    public void addNewRanking(List<RankingEntity> entities){
        int increment = 1;
        int size = listOfRanking.size();

        for(RankingEntity rankingEntity : entities){
            rankingEntity.setId(size + increment);
            increment++;
        }

        listOfRanking.addAll(entities);

    }

    public List<RankingEntity> getRankingForPlayer(int playerId){
        return listOfRanking.stream().filter(p -> p.getPlayerId() == playerId).collect(Collectors.toList());
    }

    public void init(int numberOfRows){
        for(int i = 0; i < numberOfRows; i++){
            RankingEntity rankingEntity = new RankingEntity();
            rankingEntity.setPlayerId(i);
            rankingEntity.setGameId(0);
            rankingEntity.setPoints(30);
            listOfRanking.add(rankingEntity);
        }
    }

    public void updatePointsPlayerForGame(int playerId, int gameId, long points){

        RankingEntity rankingEntity;
        rankingEntity = listOfRanking.stream().filter(p -> p.getGameId() == gameId && p.getPlayerId() == playerId).findFirst().get();
        int indexOfEntity = listOfRanking.indexOf(rankingEntity);
        long newPoints = rankingEntity.getPoints() + points;
        listOfRanking.get(indexOfEntity).setPoints(newPoints);

    }


}
