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

    // component to call TraineeController with HTTP requests
    @Autowired
    MockMvc mockMvc;

    @MockBean
    TraineeService traineeService;

    @Test
    void testGetByIdOk() throws Exception {
        // prepare
        int id = 123;
        var traineeDto = TraineeDto.builder()
                .id(id)
                .lastname("Doe")
                .firstname("John")
                .build();

        given(traineeService.findById(id))
                .willReturn(Optional.of(traineeDto));

        // when
        mockMvc.perform(
                get("/api/trainee/123")
                        .accept(MediaType.APPLICATION_JSON)
                )
                // then/verify HTTP communication
                .andDo(print()) // log request/response
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(id));

        //  then/verify Mock service has been called
        then(traineeService)
                .should()
                .findById(id);
    }

    @Test
    void testGetByIdKoNotFound(){
        fail();
    }

}