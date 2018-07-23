package com.capgemini.jstk.capmates.capmates.mapper;

import com.capgemini.jstk.capmates.capmates.game.repository.Game;
import com.capgemini.jstk.capmates.capmates.game.service.GameDTO;

import java.util.HashSet;
import java.util.Set;

public class MapperGame implements MapperObject<Game, GameDTO> {

    @Override
    public GameDTO mapFromDAO(Game object){

        GameDTO gameDTO = new GameDTO();

        gameDTO.setId(object.getId());
        gameDTO.setName(object.getName());
        gameDTO.setIsNeedMore(object.isNeedMore() );

        return gameDTO;

    }

    @Override
    public Game mapfromDTO(GameDTO object){

        Game game = new Game();

        game.setId(object.getId());
        game.setName(object.getName());
        game.setIsNeedMore(object.getIsNeedMore());

        return game;
    }

    public Set<GameDTO> mapfromSetDAO(Set<Game> listObject){

        Set<GameDTO> games = new HashSet<>();
        for(Game game : listObject){
            games.add(mapFromDAO(game));
        }

        return games;
    }
}
