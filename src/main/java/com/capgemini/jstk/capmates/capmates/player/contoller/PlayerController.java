package com.capgemini.jstk.capmates.capmates.player.contoller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.capgemini.jstk.capmates.capmates.exception.PlayerNotExist;
import com.capgemini.jstk.capmates.capmates.player.service.PlayerDTO;
import com.capgemini.jstk.capmates.capmates.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        PlayerDTO playerDTO;

        try {
            playerDTO = managePlayerProfile.getPlayerInformation(id);
            return playerDTO;

        }catch (PlayerNotExist e){
            System.out.println(e.getMessage());
        }

        return null;
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
