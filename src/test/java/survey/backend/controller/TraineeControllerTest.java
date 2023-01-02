package survey.backend.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import survey.backend.dto.TraineeDto;
import survey.backend.service.TraineeService;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TraineeController.class)
class TraineeControllerTest {

    final static String BASE_URL = "/api/trainee";

    // component to call TraineeController with HTTP requests
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TraineeService traineeService;

    @Test
    void testGetByIdOk() throws Exception {
        // 1. prepare
        long id = 123L;
        var traineeDto = TraineeDto.builder()
                .id(id)
                .lastName("Doe")
                .firstName("John")
                .build();

        given(traineeService.findById(id))
                .willReturn(Optional.of(traineeDto));

        // 2. when
        mockMvc.perform(
                get(BASE_URL + "/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                )
                // 3a. then/verify HTTP communication
                .andDo(print()) // log request/response
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));

        // 3b. then/verify Mock service has been called
        then(traineeService)
                .should()
                .findById(id);
    }

    @Test
    void testGetByIdKoNotFound() throws Exception {
        long id = 0L;

        given(traineeService.findById(id))
                .willReturn(Optional.empty());

        // 2. when
        mockMvc.perform(
                        get(BASE_URL + "/" + id)
                                .accept(MediaType.APPLICATION_JSON)
                )
                // 3a. then/verify HTTP communication
                .andDo(print()) // log request/response
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Trainee with id " + id + " not found"));

        // 3b. then/verify Mock service has been called
        then(traineeService)
                .should()
                .findById(id);
    }

}