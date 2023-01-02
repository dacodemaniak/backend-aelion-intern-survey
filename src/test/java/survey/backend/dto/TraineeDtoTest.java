package survey.backend.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDtoTest {

    @Test
    void testDefaultConstructor(){
        // when
        var traineeDto = new TraineeDto();
        // then
        assertAll(
                () -> assertNull(traineeDto.getId(), "id"),
                () -> assertNull(traineeDto.getLastName(), "lastname"),
                () -> assertNull(traineeDto.getFirstName(), "firstname"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertNull(traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthDate(), "birthdate"));
    }
    @Test
    void testAllArgsConstructor(){
        // given
        long id = 123;
        String lastname = "Doe";
        String firstname = "John";
        String email = "john.doe@example.org";
        String phoneNumber = "+33600000001";
        var birthdate = new Date(1952, 2, 29);
        // when
        var traineeDto = new TraineeDto(id,lastname,firstname,email,phoneNumber,birthdate);
        // then
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "id"),
                () -> assertEquals(lastname, traineeDto.getLastName(), "lastname"),
                () -> assertEquals(firstname, traineeDto.getFirstName(), "firstname"),
                () -> assertEquals(email, traineeDto.getEmail(), "email"),
                () -> assertEquals(phoneNumber, traineeDto.getPhoneNumber(), "phone number"),
                () -> assertEquals(birthdate, traineeDto.getBirthDate(), "birthdate")
        );
    }

    // NB: It will be important to test special case like
    // - simple attribute with default value
    // - collection initialized to empty collection
    @Test
    void testBuilderPartial() {
        // given
        long id = 123L;
        String lastname = "Doe";
        String firstname = "John";
        // when
        var traineeDto = TraineeDto.builder()
                .id(id)
                .lastName(lastname)
                .firstName(firstname)
                .build();
        // then
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "id"),
                () -> assertEquals(lastname, traineeDto.getLastName(), "lastname"),
                () -> assertEquals(firstname, traineeDto.getFirstName(), "firstname"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertNull(traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthDate(), "birthdate")
        );
    }
}