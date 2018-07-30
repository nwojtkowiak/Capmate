package com.capgemini.jstk.capmates.player.contoller;


import com.capgemini.jstk.capmates.player.service.PlayerDTO;
import com.capgemini.jstk.capmates.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        LOGGER.info("add: FirstName: " + playerDTO);

        playerDTO = managePlayerProfile.addPlayer(playerDTO);

        return playerDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PlayerDTO showPlayerById(@PathVariable(value = "id") int id) {

        PlayerDTO playerDTO;

        try {
            playerDTO = managePlayerProfile.getPlayerInformation(id);
            return playerDTO;

        } catch (Exception e) {
            playerDTO = new PlayerDTO();
        }

        return playerDTO;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Set<PlayerDTO> search(@RequestParam(name = "firstName", defaultValue = "") String firstName, @RequestParam(name = "lastName", defaultValue = "") String lastName,
                                 @RequestParam(name = "motto", defaultValue = "") String motto, @RequestParam(name = "email", defaultValue = "") String email){

        //@RequestBody PlayerDTO playerDTO) {

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setFirstName(firstName);
        playerDTO.setLastName(lastName);
        playerDTO.setMotto(motto);
        playerDTO.setEmail(email);

        return managePlayerProfile.searchByFields(playerDTO);
    }


}
