package com.capgemini.jstk.capmates.mapper;

import com.capgemini.jstk.capmates.player.repository.PlayerEntity;
import com.capgemini.jstk.capmates.player.service.PlayerDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MapperPlayer implements MapperObject<PlayerEntity, PlayerDTO> {

    @Override
    public PlayerDTO mapFromDAO(PlayerEntity object) {

        if (object != null) {
            PlayerDTO playerDTO = new PlayerDTO();

            playerDTO.setFirstName(object.getFirstName());
            playerDTO.setLastName(object.getLastName());
            playerDTO.setPassword(object.getPassword());
            playerDTO.setEmail(object.getEmail());
            playerDTO.setMotto(object.getMotto());
            playerDTO.setId(object.getId());

            return playerDTO;
        }

        throw new NullPointerException();
    }

    @Override
    public PlayerEntity mapfromDTO(PlayerDTO object) {
        if (object != null) {
            PlayerEntity player = new PlayerEntity();

            player.setFirstName(object.getFirstName());
            player.setLastName(object.getLastName());
            player.setPassword(object.getPassword());
            player.setEmail(object.getEmail());
            player.setMotto(object.getMotto());
            player.setId(object.getId());

            return player;
        }
        throw new NullPointerException();
    }

    public Set<PlayerDTO> mapfromSetDAO(Set<PlayerEntity> listObject) {

        Set<PlayerDTO> players = new HashSet<>();
        for (PlayerEntity game : listObject) {
            players.add(mapFromDAO(game));
        }

        return players;
    }
}
