package survey.backend.dto;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDtoTest {

    @Test
    void testDefaultConstructor() {
        var traineeDto = new TraineeDto();
        assertAll(
                () -> assertNull(traineeDto.getId(), "id"),
                () -> assertNull(traineeDto.getFirstName(), "firstname"),
                () -> assertNull(traineeDto.getLastName(), "lastname"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertNull(traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthDate(), "birthdate"));

    }

    // TODO: builder

    // TODO: all args constructor

}