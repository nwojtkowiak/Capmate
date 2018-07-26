package com.capgemini.jstk.capmates.ranking.repository;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class RankingDAO {

    private List<RankingEntity> listOfRanking;

    public RankingDAO() {
        listOfRanking = new LinkedList<>();
    }

    public long getPointsForPlayerAndGame(int playerId, int gameId) {
        return listOfRanking.stream().filter(checkPlayerIdAndGameId(playerId, gameId)).mapToLong(p -> p.getPoints()).sum();
    }

    /**
     * This method return map of id of players and points for them
     *
     * @param gameId - id game
     * @return map of id players as Integer and points as Long
     */
    public Map<Integer, Long> getListOfPlayersWithPointsPerGame(int gameId) {
        return listOfRanking.stream().filter(p -> p.getGameId() == gameId).collect(
                Collectors.toMap(RankingEntity::getPlayerId, RankingEntity::getPoints));
    }


    /**
     * This method add new information of points
     *
     * @param rankingEntity - new information from played game
     */
    public void addNewRanking(RankingEntity rankingEntity) {

        int id = listOfRanking.size() + 1;

        rankingEntity.setId(id);

        listOfRanking.add(rankingEntity);
    }

    /**
     * This method return ranking of points for player with  player id
     *
     * @param playerId - id player
     * @return list of all points per games as RankingEntity
     */
    public List<RankingEntity> getRankingForPlayer(int playerId) {

        return listOfRanking.stream().filter(p -> p.getPlayerId() == playerId).collect(Collectors.toList());
    }

    /**
     * This method update points of player for game.
     *
     * @param playerId - id player
     * @param gameId   - id game
     * @param points   - new points which player got in the last game
     */
    public void updatePointsPlayerForGame(int playerId, int gameId, long points) {

        RankingEntity rankingEntity;
        rankingEntity = listOfRanking.stream().filter(checkPlayerIdAndGameId(playerId, gameId)).findFirst().get();

        int indexOfEntity = listOfRanking.indexOf(rankingEntity);
        long newPoints = rankingEntity.getPoints() + points;
        listOfRanking.get(indexOfEntity).setPoints(newPoints);

    }

    private Predicate<RankingEntity> checkPlayerIdAndGameId(int playerId, int gameId) {
        return p -> p.getGameId() == gameId && p.getPlayerId() == playerId;
    }

    /**
     * This method help generate testing data
     *
     * @param numberOfRows - number of rows new data
     */
    public void init(int numberOfRows) {
        for (int i = 0; i < numberOfRows; i++) {
            RankingEntity rankingEntity = new RankingEntity();
            rankingEntity.setPlayerId(i);
            rankingEntity.setGameId(0);
            rankingEntity.setPoints(30);
            listOfRanking.add(rankingEntity);
        }
    }

}
