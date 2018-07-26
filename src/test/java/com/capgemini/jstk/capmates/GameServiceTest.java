package com.capgemini.jstk.capmates;

import com.capgemini.jstk.capmates.game.repository.GameDAO;
import com.capgemini.jstk.capmates.game.repository.PlayerGameDAO;
import com.capgemini.jstk.capmates.game.service.GameDTO;
import com.capgemini.jstk.capmates.game.service.GameService;
import com.capgemini.jstk.capmates.mapper.MapperGame;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameServiceTest {
    private GameService gameService;
    //TODO jak to obsluzyc aby moc pobrac id gier i id graczy?? -> PlayerGameDao i GameDao

    @Before
    public void setUp() {
        final int numberOfGame = 5;

        GameDAO gameDAO = new GameDAO();
        gameDAO.initListOfGames(5);
        PlayerGameDAO playerGameDAO = new PlayerGameDAO(gameDAO);
        playerGameDAO.initListOfPlayersAndGames(5);

        MapperGame mapperGame = new MapperGame();
        gameService = new GameService(mapperGame, gameDAO);

    }


    @Test
    public void shouldGetGame1(){
        //give
        GameDTO gameDTO;
        int idGame = 1;

        //when
        gameDTO = gameService.getGameInformation(idGame);

        //then
        assertThat(gameDTO.getName()).isEqualTo("Game 1");
    }

    @Test
    public void shouldReturnSize5(){
        //give
        List<Integer> listOfIdGames;
        int idPlayer = 0;

        //when
        //listOfIdGames =

        //then
        //assertThat(gameDTO.getName()).isEqualTo("Game 1");
    }


}
