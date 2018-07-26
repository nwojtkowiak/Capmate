package com.capgemini.jstk.capmates.mapper;

import com.capgemini.jstk.capmates.game.repository.GameEntity;
import com.capgemini.jstk.capmates.game.service.GameDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MapperGame implements MapperObject<GameEntity, GameDTO> {

    @Override
    public GameDTO mapFromDAO(GameEntity object) {
        if (object != null) {
            GameDTO gameDTO = new GameDTO();

            gameDTO.setId(object.getId());
            gameDTO.setName(object.getName());
            gameDTO.setIsNeedMore(object.isNeedMore());

            return gameDTO;
        }
        throw new NullPointerException();
    }

    @Override
    public GameEntity mapfromDTO(GameDTO object) {

        if (object != null) {
            GameEntity game = new GameEntity();

            game.setId(object.getId());
            game.setName(object.getName());
            game.setIsNeedMore(object.getIsNeedMore());

            return game;
        }
        throw new NullPointerException();
    }

    public Set<GameDTO> mapfromSetDAO(Set<GameEntity> listObject) {

        Set<GameDTO> games = new HashSet<>();
        for (GameEntity game : listObject) {
            games.add(mapFromDAO(game));
        }

        return games;
    }
}
