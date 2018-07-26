package com.capgemini.jstk.capmates.capmates.ranking.service;

import com.capgemini.jstk.capmates.capmates.game.service.GameDTO;
import com.capgemini.jstk.capmates.capmates.game.service.GameService;
import com.capgemini.jstk.capmates.capmates.mapper.MapperRanking;
import com.capgemini.jstk.capmates.capmates.ranking.repository.RankingDAO;
import com.capgemini.jstk.capmates.capmates.ranking.repository.RankingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RankingService {

    private RankingDAO rankingDAO;
    private GameService gameService;
    private MapperRanking mapperRanking;

    @Autowired
    public RankingService(MapperRanking mapperRanking, RankingDAO rankingDAO,GameService gameService){
        this.mapperRanking = mapperRanking;
        this.rankingDAO = rankingDAO;
        this.gameService = gameService;
    }


    public long getPointsForPlayerAndGame(int playerId, int gameId){
        return rankingDAO.getPointsForPlayerAndGame(playerId, gameId);
    }

    public List<RankingDTO> getRankForPlayer(int playerId){
        List<RankingEntity> ranksEntity = rankingDAO.getRankingForPlayer(playerId);
        List<RankingDTO> ranksDTO = mapperRanking.mapfromListDAO(ranksEntity);
        return  ranksDTO;
    }



    private List<RankingPointsDTO> getRankingWithEmptyPositions(int gameId){
        List<RankingPointsDTO> ranking = new ArrayList<>();

        Map<Integer, Long> points = rankingDAO.getListOfPlayersWithPointsPerGame(gameId);
        for(Map.Entry<Integer, Long> entry : points.entrySet()){
            RankingPointsDTO rankingPointsDTO = new RankingPointsDTO();
            rankingPointsDTO.setPlayerId(entry.getKey());
            rankingPointsDTO.setPoints(entry.getValue());
            String gameName = gameService.getGameName(gameId);
            rankingPointsDTO.setGameName(gameName);
            ranking.add(rankingPointsDTO);
        }

        return ranking;
    }

    public List<RankingPointsDTO> getRankingWithFillPositions(int gameId){
        List<RankingPointsDTO> ranking = getRankingWithEmptyPositions(gameId);
        sortForGameByPoints(ranking);
        countPositions(ranking);
        return ranking;
    }

    private void sortForGameByPoints(List<RankingPointsDTO> ranking){
        Collections.sort(ranking, Comparator.comparing(RankingPointsDTO::getPoints).reversed());
    }

    private void countPositions(List<RankingPointsDTO> points){


        RankingPointsDTO tmpPoints;
        points.get(0).setPosition(1);

        for(int i = 1; i < points.size(); i++){
            tmpPoints = points.get(i-1);
            if(points.get(i).getPoints() == tmpPoints.getPoints()){
                points.get(i).setPosition(tmpPoints.getPosition());
            }else{
                points.get(i).setPosition(tmpPoints.getPosition()+1);
            }
        }

    }

    private void updatePointsForGame(int playerId, int gameId, long newPoints){
        rankingDAO.updatePointsPlayerForGame(playerId, gameId, newPoints);
    }


}
