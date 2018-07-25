package com.capgemini.jstk.capmates.capmates;
import com.capgemini.jstk.capmates.capmates.history.repository.HistoryDAO;
import com.capgemini.jstk.capmates.capmates.history.service.HistoryDTO;
import com.capgemini.jstk.capmates.capmates.history.service.HistoryService;
import com.capgemini.jstk.capmates.capmates.mapper.MapperHistory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class HistoryServiceTest {

    private HistoryService historyService;

    @Before
    public void setUp(){
        MapperHistory mapperHistory = new MapperHistory();
        HistoryDAO historyDAO = new HistoryDAO();
        int numberOfRows = 5;
        historyDAO.init(numberOfRows);
        historyService = new HistoryService(mapperHistory, historyDAO);
    }


    @Test
    public void shouldReturnListWIthSize5(){
        //give
        List<HistoryDTO> history = historyService.getHistoryForPlayer(0);

        //when
        int size = history.size();

        //then
        assertThat(size).isEqualTo(5);
    }

    @Test
    public void shouldReturnListWithSize5WhenLose(){
        //give
        List<HistoryDTO> history = historyService.getLoseGamesForPlayer(0);

        //when
        int size = history.size();

        //then
        assertThat(size).isEqualTo(5);
    }


}
