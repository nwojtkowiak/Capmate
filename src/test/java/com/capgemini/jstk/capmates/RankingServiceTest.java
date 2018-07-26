package com.capgemini.jstk.capmates;

import com.capgemini.jstk.capmates.game.repository.GameDAO;
import com.capgemini.jstk.capmates.game.repository.PlayerGameDAO;
import com.capgemini.jstk.capmates.game.service.GameService;
import com.capgemini.jstk.capmates.mapper.MapperGame;
import com.capgemini.jstk.capmates.mapper.MapperPlayer;
import com.capgemini.jstk.capmates.mapper.MapperRanking;
import com.capgemini.jstk.capmates.player.repository.PlayerDAO;
import com.capgemini.jstk.capmates.player.service.PlayerService;
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
        MapperPlayer mapperPlayer = new MapperPlayer();

        GameDAO gameDAO = new GameDAO();
        gameDAO.initListOfGames(numOfRows);
        PlayerDAO playerDAO = new PlayerDAO();
        PlayerGameDAO playerGameDAO = new PlayerGameDAO(gameDAO);
        RankingDAO rankingDAO = new RankingDAO();

        GameService gameService = new GameService(mapperGame,gameDAO, playerGameDAO);
        PlayerService playerService = new PlayerService(mapperPlayer, mapperGame, playerDAO, playerGameDAO);

        rankingDAO.init(numOfRows);
        rankingService = new RankingService(mapperRanking,rankingDAO,gameService, playerService);
    }

    @Test
    public void shouldReturnPointsForFirstAndLastPlayer(){
        //when
        List<RankingPointsDTO> rankingList = rankingService.getRankingWithFillPositions(0);

        //then
        long pointsForFirst = rankingList.get(0).getPoints();
        long pointsForLast = rankingList.get(4).getPoints();
        assertThat(pointsForFirst).isEqualTo(30);
        assertThat(pointsForLast).isEqualTo(30);
    }

    @Test
    public void shouldReturnFirstPositionsForAll(){
        //when
        List<RankingPointsDTO> rankingList = rankingService.getRankingWithFillPositions(0);

        //then
        long positionForFirst = rankingList.get(0).getPosition();
        long positionForLast = rankingList.get(4).getPosition();
        assertThat(positionForFirst).isEqualTo(1);
        assertThat(positionForLast).isEqualTo(1);
    }
}
