package com.capgemini.jstk.capmates;

import com.capgemini.jstk.capmates.player.service.PlayerDTO;
import com.capgemini.jstk.capmates.player.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlayerControlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;


    private List<PlayerDTO> setPlayers(int size ){
        List<PlayerDTO> players = new ArrayList<>();
        PlayerDTO playerDTO = new PlayerDTO();


            playerDTO.setEmail("anna.kowalsk@cap.com");
            playerDTO.setMotto("Hakuna Matata");
            playerDTO.setLastName("Kowalsk");
            playerDTO.setFirstName("Anna");
            players.add(playerDTO);
        if(size > 1) {
            playerDTO = new PlayerDTO();
            playerDTO.setEmail("roman.kowalsk@cap.com");
            playerDTO.setMotto("Hakuna Matata");
            playerDTO.setLastName("Kowalsk");
            playerDTO.setFirstName("Roman");
            players.add(playerDTO);
        }
        return players;
    }
    @Test
    public void getAllPlayersShouldReturnSize2() throws Exception {
        //given
        List<PlayerDTO> players = setPlayers(2);

        //when
        when(playerService.getAllPlayers()).thenReturn(players);
        mockMvc.perform(get("/player"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].firstName",is("Anna")))
                .andExpect(jsonPath("$[1].firstName",is("Roman")));

        //then
        verify(playerService, times(1)).getAllPlayers();
    }

    @Test
    public void getPlayerInformationShouldReturnId() throws Exception {
        //given
        List<PlayerDTO> players = setPlayers(1);
        PlayerDTO playerDTO = players.get(0);

        //when
        when(playerService.getPlayerInformation(0)).thenReturn(playerDTO);
        mockMvc.perform(get("/player/0"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("id",is(0)));

        //then
        verify(playerService, times(1)).getPlayerInformation(0);
    }

    @Test
    public void addPlayerShouldReturnNewId() throws Exception {
        //given
        List<PlayerDTO> players = setPlayers(1);
        PlayerDTO playerDTO = players.get(0);
        playerDTO.setId(0);
        String asJsonString = new ObjectMapper().writeValueAsString(players.get(0));

        //when
        when(playerService.addPlayer(Mockito.any(PlayerDTO.class))).thenReturn(playerDTO);
        mockMvc.perform(post("/player/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString))
                .andExpect(jsonPath("id",is(0)));


        //then
        verify(playerService, times(1)).addPlayer(Mockito.any(PlayerDTO.class));
    }

    @Test
    public void searchShouldReturnSize1() throws Exception {
        //given
        List<PlayerDTO> players = setPlayers(1);
        PlayerDTO playerDTO = players.get(0);

        //when
        when(playerService.searchByFields(Mockito.any(PlayerDTO.class))).thenReturn(players);
        mockMvc.perform(get("/player/search?firstName=Anna"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].firstName",is("Anna")));

        //then
        verify(playerService, times(1)).searchByFields(Mockito.any(PlayerDTO.class));
    }

    @Test
    public void getResourceNotFoundException() throws Exception {

        //when
        when(playerService.getPlayerInformation(30)).thenReturn(null);
        mockMvc.perform(get("/player/30")).andExpect(status().is4xxClientError());

        //then
        verify(playerService, times(1)).getPlayerInformation(30);
    }


}
