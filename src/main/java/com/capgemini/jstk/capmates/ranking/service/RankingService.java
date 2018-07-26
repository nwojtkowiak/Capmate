package com.capgemini.jstk.capmates.ranking.service;

import com.capgemini.jstk.capmates.exception.PlayerNotExist;
import com.capgemini.jstk.capmates.game.service.GameService;
import com.capgemini.jstk.capmates.mapper.MapperRanking;
import com.capgemini.jstk.capmates.player.service.PlayerService;
import com.capgemini.jstk.capmates.ranking.repository.RankingDAO;
import com.capgemini.jstk.capmates.ranking.repository.RankingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RankingService {

    private RankingDAO rankingDAO;
    private GameService gameService;
    private PlayerService playerService;
    private MapperRanking mapperRanking;

    @Autowired
    public RankingService(MapperRanking mapperRanking, RankingDAO rankingDAO, GameService gameService, PlayerService playerService) {
        this.mapperRanking = mapperRanking;
        this.rankingDAO = rankingDAO;
        this.gameService = gameService;
        this.playerService = playerService;
    }


    public long getPointsForPlayerAndGame(int playerId, int gameId) {
        return rankingDAO.getPointsForPlayerAndGame(playerId, gameId);
    }

    public List<RankingDTO> getRankForPlayer(int playerId) {
        List<RankingEntity> ranksEntity = rankingDAO.getRankingForPlayer(playerId);
        List<RankingDTO> ranksDTO = mapperRanking.mapfromListDAO(ranksEntity);
        return ranksDTO;
    }

    public void updateRanking(List<RankingDTO> results) {

        for (RankingDTO dto : results) {
            int playerId = dto.getPlayerId();
            try {
                playerService.getPlayerInformation(playerId);
                updatePointsForGame(playerId, dto.getGameId(), dto.getPoints());

            } catch (PlayerNotExist playerNotExist) {
                rankingDAO.addNewRanking(mapperRanking.mapfromDTO(dto));
            }
        }
    }

    /**
     * This method return descending sort by points list with position dor players
     * @param gameId - id game for which positions are count
     * @return list of points with position as RankingPointsDTO
     */
    public List<RankingPointsDTO> getRankingWithFillPositions(int gameId) {
        List<RankingPointsDTO> ranking = getRankingWithEmptyPositions(gameId);
        sortForGameByPoints(ranking);
        countPositions(ranking);
        return ranking;
    }

    /**
     * This method get list of points without position and not sorted
     * @param gameId
     * @return
     */
    private List<RankingPointsDTO> getRankingWithEmptyPositions(int gameId) {
        List<RankingPointsDTO> ranking = new ArrayList<>();

        Map<Integer, Long> points = rankingDAO.getListOfPlayersWithPointsPerGame(gameId);
        for (Map.Entry<Integer, Long> entry : points.entrySet()) {
            RankingPointsDTO rankingPointsDTO = new RankingPointsDTO();
            rankingPointsDTO.setPlayerId(entry.getKey());
            rankingPointsDTO.setPoints(entry.getValue());
            String gameName = gameService.getGameName(gameId);
            rankingPointsDTO.setGameName(gameName);
            ranking.add(rankingPointsDTO);
        }

        return ranking;
    }

    /**
     * This method sort list by points in descending order
     * @param ranking - list of information about points as RankingPointsDTO
     */
    private void sortForGameByPoints(List<RankingPointsDTO> ranking) {
        Collections.sort(ranking, Comparator.comparing(RankingPointsDTO::getPoints).reversed());
    }

    /**
     * This method count position for everyone player.
     * If players have the same amount of points that they have the same position
     * @param listOfPoints
     */
    private void countPositions(List<RankingPointsDTO> listOfPoints) {

        RankingPointsDTO tmpPoints;
        listOfPoints.get(0).setPosition(1);

        for (int i = 1; i < listOfPoints.size(); i++) {
            tmpPoints = listOfPoints.get(i - 1);
            if (listOfPoints.get(i).getPoints() == tmpPoints.getPoints()) {
                listOfPoints.get(i).setPosition(tmpPoints.getPosition());
            } else {
                listOfPoints.get(i).setPosition(tmpPoints.getPosition() + 1);
            }
        }

    }

    private void updatePointsForGame(int playerId, int gameId, long newPoints) {
        rankingDAO.updatePointsPlayerForGame(playerId, gameId, newPoints);
    }


}
