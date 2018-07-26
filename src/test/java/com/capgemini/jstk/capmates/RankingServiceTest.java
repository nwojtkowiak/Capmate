package com.capgemini.jstk.capmates;

import com.capgemini.jstk.capmates.game.repository.GameDAO;
import com.capgemini.jstk.capmates.game.service.GameService;
import com.capgemini.jstk.capmates.mapper.MapperGame;
import com.capgemini.jstk.capmates.mapper.MapperRanking;
import com.capgemini.jstk.capmates.ranking.repository.RankingDAO;
import com.capgemini.jstk.capmates.ranking.service.RankingPointsDTO;
import com.capgemini.jstk.capmates.ranking.service.RankingService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class RankingServiceTest {

    private RankingService rankingService;

    @Before
    public void setUp(){
        int numOfRows = 5;
        MapperRanking mapperRanking = new MapperRanking();
        MapperGame mapperGame = new MapperGame();
        GameDAO gameDAO = new GameDAO();
        gameDAO.initListOfGames(numOfRows);
        RankingDAO rankingDAO = new RankingDAO();
        GameService gameService = new GameService(mapperGame,gameDAO);

        rankingDAO.init(numOfRows);
        rankingService = new RankingService(mapperRanking,rankingDAO,gameService);
    }

    @Test
    public void shouldReturnPointsForFirstAndLastPlayer(){
        //give
        List<RankingPointsDTO> rankingList = rankingService.getRankingWithFillPositions(0);

        //when
        long pointsForFirst = rankingList.get(0).getPoints();
        long pointsForLast = rankingList.get(4).getPoints();
        //then
        assertThat(pointsForFirst).isEqualTo(30);
        assertThat(pointsForLast).isEqualTo(30);
    }

    @Test
    public void shouldReturnFirstPositionsForAll(){
        //give
        List<RankingPointsDTO> rankingList = rankingService.getRankingWithFillPositions(0);

        //when
        long positionForFirst = rankingList.get(0).getPosition();
        long positionForLast = rankingList.get(4).getPosition();
        //then
        assertThat(positionForFirst).isEqualTo(1);
        assertThat(positionForLast).isEqualTo(1);
    }
}