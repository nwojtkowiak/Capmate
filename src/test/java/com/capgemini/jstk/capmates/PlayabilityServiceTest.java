package com.capgemini.jstk.capmates;

import com.capgemini.jstk.capmates.enums.Days;
import com.capgemini.jstk.capmates.mapper.MapperPlayability;
import com.capgemini.jstk.capmates.playability.repository.PlayabilityDAO;
import com.capgemini.jstk.capmates.playability.repository.PlayabilityEntity;
import com.capgemini.jstk.capmates.playability.service.PlayabilityDTO;
import com.capgemini.jstk.capmates.playability.service.PlayabilityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayabilityServiceTest {

    @Mock
    private PlayabilityDAO playabilityDAO;

    @Spy
    private MapperPlayability mapperPlayability;

    @InjectMocks
    private PlayabilityService playabilityService;



    public List<PlayabilityEntity> setPlayabilityList(){
        List<PlayabilityEntity> listOfPlayability = new LinkedList<>();

        PlayabilityEntity playabilityEntity = new PlayabilityEntity();
        playabilityEntity.setPlayerId(0);
        playabilityEntity.setDay(Days.FRIDAY);
        playabilityEntity.setFrom(LocalTime.parse("16:00"));
        playabilityEntity.setFrom(LocalTime.parse("23:00"));
        listOfPlayability.add(playabilityEntity);

        playabilityEntity = new PlayabilityEntity();
        playabilityEntity.setDay(Days.SUNDAY);
        playabilityEntity.setFrom(LocalTime.parse("16:00"));
        playabilityEntity.setFrom(LocalTime.parse("23:00"));
        listOfPlayability.add(playabilityEntity);
        playabilityEntity.setPlayerId(0);

        playabilityEntity = new PlayabilityEntity();
        playabilityEntity.setPlayerId(1);
        playabilityEntity.setDay(Days.FRIDAY);
        playabilityEntity.setFrom(LocalTime.parse("15:00"));
        playabilityEntity.setFrom(LocalTime.parse("20:00"));
        listOfPlayability.add(playabilityEntity);

        playabilityEntity = new PlayabilityEntity();
        playabilityEntity.setDay(Days.SUNDAY);
        playabilityEntity.setFrom(LocalTime.parse("18:00"));
        playabilityEntity.setFrom(LocalTime.parse("23:00"));
        listOfPlayability.add(playabilityEntity);
        playabilityEntity.setPlayerId(1);

        return listOfPlayability;
    }

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        Mockito.when(playabilityDAO.getListSimilarPlayability(Mockito.anyInt())).thenReturn(setPlayabilityList());

        Mockito.when(playabilityDAO.addNewTerm(Mockito.any(PlayabilityEntity.class))).thenReturn(1);

        Mockito.when(playabilityDAO.removeTerm(Mockito.anyInt())).thenReturn(5);

        Mockito.when(playabilityDAO.addCommentToTerm(Mockito.anyInt(), Mockito.anyString())).thenReturn(5);
    }


    @Test
    public void shouldReturnSize2WhenSimilarAbility(){
        //when
        List<PlayabilityDTO> list = playabilityService.getPlayerWithSimilarPlayability(3);

        //then
        int size = list.size();
        assertThat(size).isEqualTo(4);

    }

    @Test
    public void schouldReturnSize1AfterAddTerm(){
        //give
        PlayabilityDTO playabilityDTO = new PlayabilityDTO();
        playabilityDTO.setDay(Days.MONDAY);
        //when
        int id = playabilityService.addNewTerm(playabilityDTO);

        //then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void schouldReturnSize5AfterRemove(){
        //give
        int id = 2;
        String comment = "I can't on Friday 27.07";

        //when
        int index = playabilityService.addCommentToTerm(id, comment);

        //then
        assertThat(index).isEqualTo(5);
    }


}
