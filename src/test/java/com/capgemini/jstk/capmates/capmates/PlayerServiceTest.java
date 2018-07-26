package com.capgemini.jstk.capmates.capmates;

import com.capgemini.jstk.capmates.capmates.enums.Level;
import com.capgemini.jstk.capmates.capmates.exception.PlayerNotExist;
import com.capgemini.jstk.capmates.capmates.game.repository.GameDAO;
import com.capgemini.jstk.capmates.capmates.game.repository.PlayerGameDAO;
import com.capgemini.jstk.capmates.capmates.game.service.GameDTO;
import com.capgemini.jstk.capmates.capmates.game.service.GameService;
import com.capgemini.jstk.capmates.capmates.mapper.MapperGame;
import com.capgemini.jstk.capmates.capmates.mapper.MapperPlayer;
import com.capgemini.jstk.capmates.capmates.mapper.MapperRanking;
import com.capgemini.jstk.capmates.capmates.player.repository.PlayerDAO;
import com.capgemini.jstk.capmates.capmates.player.service.PlayerDTO;
import com.capgemini.jstk.capmates.capmates.player.service.PlayerService;
import com.capgemini.jstk.capmates.capmates.ranking.repository.RankingDAO;
import com.capgemini.jstk.capmates.capmates.ranking.service.RankingService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerServiceTest {
    private PlayerService playerService;
    private GameService gameService;

    @Before
    public void setUp() {
        final int numberOfUsers = 5;

        PlayerDAO playerDAO = new PlayerDAO();
        playerDAO.initListOfUsers(5);
        GameDAO gameDAO = new GameDAO();
        PlayerGameDAO playerGameDAO = new PlayerGameDAO(gameDAO);


        MapperPlayer mapperPlayer = new MapperPlayer();
        MapperGame mapperGame = new MapperGame();

        playerService = new PlayerService(mapperPlayer, mapperGame, playerDAO, playerGameDAO);
        gameService = new GameService(mapperGame, gameDAO);
    }

    @Test
    public void schouldChangeFirstNameUserOnAnna() throws PlayerNotExist {

        //give
        PlayerDTO playerDTO = playerService.getPlayerInformation(0);
        String newFirstName = "Anna";

        //when
        playerDTO.setFirstName(newFirstName);
        playerService.savePlayerInformation(playerDTO);

        PlayerDTO testPlayerDTO = playerService.getPlayerInformation(0);
        String firstName = testPlayerDTO.getFirstName();

        //then
        assertThat(firstName).isEqualTo(newFirstName);

    }

    @Test
    public void schouldChangeLastNameUserOnKowalska() throws PlayerNotExist {

        //give
        PlayerDTO playerDTO = playerService.getPlayerInformation(0);
        String newLastName = "Kowalska";
        //when
        playerDTO.setLastName(newLastName);
        playerService.savePlayerInformation(playerDTO);

        PlayerDTO testPlayerDTO = playerService.getPlayerInformation(0);
        String result = testPlayerDTO.getLastName();

        //then
        assertThat(result).isEqualTo(newLastName);

    }

    @Test
    public void schouldChangeEmailUserOnTest() throws PlayerNotExist {

        //give
        PlayerDTO playerDTO = playerService.getPlayerInformation(0);
        String newEmail = "test@cap.com";
        //when
        playerDTO.setEmail(newEmail);
        playerService.savePlayerInformation(playerDTO);

        PlayerDTO testPlayerDTO = playerService.getPlayerInformation(0);
        String result = testPlayerDTO.getEmail();

        //then
        assertThat(result).isEqualTo(newEmail);

    }

    @Test
    public void schouldChangePasswordUserOnQwerty() throws PlayerNotExist {

        //give
        PlayerDTO playerDTO = playerService.getPlayerInformation(0);
        String newPasword = "qwerty";
        String oldPassword = playerDTO.getPassword();
        //when
        playerDTO.setPassword(newPasword);
        playerService.changePassword(playerDTO, oldPassword, newPasword);

        PlayerDTO testPlayerDTO = playerService.getPlayerInformation(0);
        String result = testPlayerDTO.getPassword();

        //then
        assertThat(result).isEqualTo(newPasword);

    }


    @Test
    public void schouldChangeMottoUserOnQwerty() throws PlayerNotExist {

        //give
        PlayerDTO playerDTO = playerService.getPlayerInformation(0);
        String newMotto = "qwerty";
        //when
        playerDTO.setMotto(newMotto);
        //playerService.savePlayerInformation(playerDTO);
        playerService.savePlayerInformation(playerDTO);

        PlayerDTO testPlayerDTO = playerService.getPlayerInformation(0);
        String result = testPlayerDTO.getMotto();

        //then
        assertThat(result).isEqualTo(newMotto);

    }


    @Test
    public void schouldGetInfoAboutUser() throws PlayerNotExist {

        //give
        PlayerDTO playerDTO = playerService.getPlayerInformation(1);

        //when
       String firstName = playerDTO.getFirstName();
       String lastName = playerDTO.getLastName();
       String email = playerDTO.getEmail();
       String password = playerDTO.getPassword();
       String motto = playerDTO.getMotto();
       Level level = playerDTO.getLevel();

        //then
        assertThat(firstName).isEqualTo("First 1");
        assertThat(lastName).isEqualTo("Last 1");
        assertThat(email).isEqualTo("player1@cap.com");
        assertThat(password).isEqualTo("password");
        assertThat(motto).isEqualTo("Hakuna Matata 1");
        assertThat(level).isEqualTo(Level.BEGINNER);

    }

    @Test
    public void schouldReturnSize1AfterAddNewGame() throws PlayerNotExist {
        //give
        PlayerDTO playerDTO = playerService.getPlayerInformation(0);
//TODO czy game dla playera potrzebuje id?
        GameDTO gameDTO = new GameDTO();
        gameDTO.setName("Wysokie napiecie");
        gameDTO.setIsNeedMore(false);
        gameDTO.setNumberOfPlayers(2,6);


        //when
        playerService.addGame(playerDTO,gameDTO);

        //then
        int sizeGamesOfPlayer = playerDTO.getGames().size();
        int sizeAllGames = gameService.getSizeSetOfGames();
        String name = playerDTO.getGames().get(0).getName();
        assertThat(sizeGamesOfPlayer).isEqualTo(1);
        assertThat(sizeAllGames).isEqualTo(1);
        assertThat(name).isEqualTo("Wysokie napiecie");

        //TODO przetestowac czy zgadzaja sie id w tabelach
        //List<Integer>  = gameService.
    }

    @Test
    public void shouldReturnSize0AfterRemoveGame() throws PlayerNotExist {
        //give
        PlayerDTO playerDTO = playerService.getPlayerInformation(0);

        GameDTO gameDTO = new GameDTO();
        gameDTO.setName("Wysokie napiecie");
        gameDTO.setIsNeedMore(false);
        gameDTO.setNumberOfPlayers(2,6);
        //czy game dla playera potrzebuje id?

        playerService.addGame(playerDTO,gameDTO);

        //when
        playerService.removeGame(playerDTO, gameDTO);

        int sizeGamesOfPlayer = playerDTO.getGames().size();
        assertThat(sizeGamesOfPlayer).isEqualTo(0);
    }

    @Test
    public void shouldReturnSize0AfterRemoveGameFromEmptyList() throws PlayerNotExist {
        //give
        PlayerDTO playerDTO = playerService.getPlayerInformation(0);

        GameDTO gameDTO = new GameDTO();
        gameDTO.setName("Wysokie napiecie");
        gameDTO.setIsNeedMore(false);
        gameDTO.setNumberOfPlayers(2,6);

        //when
        playerService.removeGame(playerDTO, gameDTO);

        int sizeGamesOfPlayer = playerDTO.getGames().size();
        assertThat(sizeGamesOfPlayer).isEqualTo(0);
    }

    @Test
    public void shouldReturnBeginnerLevel(){
        //given
        long points = 10;

        //when
        Level level = playerService.countLevel(points);

        //then
        assertThat(level).isEqualTo(Level.BEGINNER);
    }

    @Test
    public void shouldReturnIntermediateLevel(){
        //given
        long points = 500;

        //when
        Level level = playerService.countLevel(points);

        //then
        assertThat(level).isEqualTo(Level.INTERMEDIATE);
    }


}
