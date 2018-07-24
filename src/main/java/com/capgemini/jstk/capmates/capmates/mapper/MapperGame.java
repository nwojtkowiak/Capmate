package com.capgemini.jstk.capmates.capmates.mapper;

import com.capgemini.jstk.capmates.capmates.game.repository.GameEntity;
import com.capgemini.jstk.capmates.capmates.game.service.GameDTO;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MapperGame implements MapperObject<GameEntity, GameDTO> {

    @Override
    public GameDTO mapFromDAO(GameEntity object){

        GameDTO gameDTO = new GameDTO();

        gameDTO.setId(object.getId());
        gameDTO.setName(object.getName());
        gameDTO.setIsNeedMore(object.isNeedMore() );

        return gameDTO;

    }

    @Override
    public GameEntity mapfromDTO(GameDTO object){

        GameEntity game = new GameEntity();

        game.setId(object.getId());
        game.setName(object.getName());
        game.setIsNeedMore(object.getIsNeedMore());

        return game;
    }

    public Set<GameDTO> mapfromSetDAO(Set<GameEntity> listObject){

        Set<GameDTO> games = new HashSet<>();
        for(GameEntity game : listObject){
            games.add(mapFromDAO(game));
        }

        return games;
    }
}
