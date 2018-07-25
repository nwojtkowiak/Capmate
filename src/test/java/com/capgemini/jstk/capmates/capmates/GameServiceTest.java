package com.capgemini.jstk.capmates.capmates;

import com.capgemini.jstk.capmates.capmates.enums.Level;
import com.capgemini.jstk.capmates.capmates.exception.PlayerNotExist;
import com.capgemini.jstk.capmates.capmates.mapper.MapperPlayer;
import com.capgemini.jstk.capmates.capmates.player.repository.PlayerDAO;
import com.capgemini.jstk.capmates.capmates.player.service.PlayerDTO;
import com.capgemini.jstk.capmates.capmates.player.service.PlayerService;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerServiceTest {
    private PlayerService playerService;

    @Before
    public void setUp() {
        final int numberOfUsers = 5;

        PlayerDAO dao = new PlayerDAO();
        dao.initListOfUsers(5);
        MapperPlayer mapperPlayer = new MapperPlayer();
        playerService = new PlayerService(mapperPlayer, dao);

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
        //when
        playerDTO.setPassword(newPasword);
        playerService.savePlayerInformation(playerDTO);

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


}
