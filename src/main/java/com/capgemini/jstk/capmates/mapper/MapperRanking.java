package com.capgemini.jstk.capmates.mapper;

import com.capgemini.jstk.capmates.ranking.repository.RankingEntity;
import com.capgemini.jstk.capmates.ranking.service.RankingDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperRanking implements MapperObject<RankingEntity, RankingDTO> {

    @Override
    public RankingDTO mapFromDAO(RankingEntity object) {

        if (object != null) {
            RankingDTO rankingDTO = new RankingDTO();
            rankingDTO.setGameId(object.getGameId());
            rankingDTO.setPlayerId(object.getPlayerId());
            rankingDTO.setPoints(object.getPoints());

            return rankingDTO;
        }

        throw new NullPointerException();
    }

    @Override
    public RankingEntity mapfromDTO(RankingDTO object) {
        if (object != null) {


            RankingEntity rankingEntity = new RankingEntity();
            rankingEntity.setGameId(object.getGameId());
            rankingEntity.setPlayerId(object.getPlayerId());
            rankingEntity.setPoints(object.getPoints());

            return rankingEntity;

        }

        throw new NullPointerException();
    }

    public List<RankingDTO> mapfromListDAO(List<RankingEntity> listObject) {

        List<RankingDTO> ranks = new ArrayList<>();
        for (RankingEntity rank : listObject) {
            ranks.add(mapFromDAO(rank));
        }

        return ranks;
    }

}
