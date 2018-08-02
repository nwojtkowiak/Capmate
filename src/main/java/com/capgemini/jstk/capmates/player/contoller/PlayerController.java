package com.capgemini.jstk.capmates.player.contoller;


import com.capgemini.jstk.capmates.player.service.PlayerDTO;
import com.capgemini.jstk.capmates.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/player")

public class PlayerController {

    private PlayerService managePlayerProfile;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    public PlayerController(PlayerService managePlayerProfile) {
        this.managePlayerProfile = managePlayerProfile;
    }

    /**
     * This method return dto with data of new player
     *
     * @param playerDTO - dto with data of new player
     * @return filled dto supplemented by id
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public PlayerDTO addNewPlayer(@RequestBody PlayerDTO playerDTO) {
        LOGGER.info("add: FirstName: " + playerDTO.getFirstName());
        if (playerDTO != null) {
            playerDTO = managePlayerProfile.addPlayer(playerDTO);
        } else {
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return playerDTO;
    }

    /**
     * This method return list dto of all pleyers
     *
     * @return list dto of all players
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<PlayerDTO> showAllPlayer() {

        return managePlayerProfile.getAllPlayers();

    }

    /**
     * This method return dto player or exception
     *
     * @param id - id of the player to be displayed
     * @return - dto of the player with id from param id
     * @throws ResourceNotFoundException is throw when player with given id doesn't exist
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PlayerDTO showPlayerById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        PlayerDTO playerDTO = managePlayerProfile.getPlayerInformation(id);
        if (playerDTO != null) {
            return playerDTO;
        }
        throw new ResourceNotFoundException();

    }

    /**
     * This method return players whose fitted to data given as parameters
     * @param firstName  of player or part of it
     * @param lastName of player or part of it
     * @param motto of player or part of it
     * @param email of player or part of it
     * @return all players found
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<PlayerDTO> search(@RequestParam(name = "firstName", defaultValue = "") String firstName, @RequestParam(name = "lastName", defaultValue = "") String lastName,
                                  @RequestParam(name = "motto", defaultValue = "") String motto, @RequestParam(name = "email", defaultValue = "") String email) {

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setFirstName(firstName);
        playerDTO.setLastName(lastName);
        playerDTO.setMotto(motto);
        playerDTO.setEmail(email);

        return managePlayerProfile.searchByFields(playerDTO);
    }


}
