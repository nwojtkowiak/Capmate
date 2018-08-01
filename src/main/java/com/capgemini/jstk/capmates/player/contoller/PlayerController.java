package com.capgemini.jstk.capmates.player.contoller;


import com.capgemini.jstk.capmates.player.service.PlayerDTO;
import com.capgemini.jstk.capmates.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/player")

public class PlayerController {

    private PlayerService managePlayerProfile;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    public PlayerController(PlayerService managePlayerProfile) {

        this.managePlayerProfile = managePlayerProfile;

    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public PlayerDTO addNewPlayer(@RequestBody PlayerDTO playerDTO) {
        LOGGER.info("add: FirstName: " + playerDTO.getFirstName());
        if (playerDTO != null) {
            playerDTO = managePlayerProfile.addPlayer(playerDTO);
        }else{
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return playerDTO;
    }

    @RequestMapping( method = RequestMethod.GET)
    public List<PlayerDTO> showAllPlayer() {

        return managePlayerProfile.getAllPlayers();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PlayerDTO showPlayerById(@PathVariable(value = "id") int id) {
        try {
            return managePlayerProfile.getPlayerInformation(id);
        } catch (Exception e) {
        throw new ResourceNotFoundException();
    }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<PlayerDTO> search(@RequestParam(name = "firstName", defaultValue = "") String firstName, @RequestParam(name = "lastName", defaultValue = "") String lastName,
                                 @RequestParam(name = "motto", defaultValue = "") String motto, @RequestParam(name = "email", defaultValue = "") String email) {

        //@RequestBody PlayerDTO playerDTO) {

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setFirstName(firstName);
        playerDTO.setLastName(lastName);
        playerDTO.setMotto(motto);
        playerDTO.setEmail(email);

        return managePlayerProfile.searchByFields(playerDTO);
    }






}
