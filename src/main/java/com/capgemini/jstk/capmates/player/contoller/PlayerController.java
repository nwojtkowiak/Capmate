package com.capgemini.jstk.capmates.player.contoller;

import com.capgemini.jstk.capmates.exception.PlayerNotExist;
import com.capgemini.jstk.capmates.player.service.PlayerService;
import com.capgemini.jstk.capmates.player.service.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/player")

public class PlayerController {

    private PlayerService managePlayerProfile;


    @Autowired
    public PlayerController(PlayerService managePlayerProfile){

        this.managePlayerProfile = managePlayerProfile;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public PlayerDTO showPlayerById(@PathVariable(value = "id") int id) {

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setEmail("test@cap.com");
        playerDTO.setFirstName("Natalia");
        playerDTO.setMotto("Hakuna Matata");

        try {
            playerDTO = managePlayerProfile.getPlayerInformation(id);
            return playerDTO;

        }catch (Exception e){
            playerDTO = new PlayerDTO();
            playerDTO.setEmail("test@cap.com");
            playerDTO.setFirstName("Natalia");
            playerDTO.setMotto("Hakuna Matata");
        }

        return playerDTO;
    }

//TODO change this
    @RequestMapping(value = "/{id}/{firstName}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public PlayerDTO changeFirstName(@PathVariable(value = "id") int id, @PathVariable(value = "firstName") String firstName) {

        PlayerDTO playerDTO;

        try {
            playerDTO = managePlayerProfile.getPlayerInformation(id);
            playerDTO.setFirstName(firstName);
            return managePlayerProfile.savePlayerInformation(playerDTO);

        }catch (PlayerNotExist e){
            System.out.println(e.getMessage());
        }

        return null;
    }









}
