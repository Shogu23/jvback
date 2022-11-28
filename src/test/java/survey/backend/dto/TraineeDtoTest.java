package survey.backend.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDtoTest {

    @Test
    void testDefaultConstructor(){
        // when
        var traineeDto = new TraineeDto();
        // then
        assertAll(
                () -> assertNull(traineeDto.getId(), "id"),
                () -> assertNull(traineeDto.getLastName(), "lastName"),
                () -> assertNull(traineeDto.getFirstName(), "firstName"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertNull(traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthDate(), "birthDate"));
    }
    @Test
    void testAllArgsConstructor(){
        // given
        int id = 123;
        String lastName = "Doe";
        String firstName = "John";
        String email = "john.doe@example.org";
        String phoneNumber = "+33600000001";
        var birthDate = LocalDate.of(1952, 2, 29);
        // when
        var traineeDto = new TraineeDto(id,lastName,firstName,email,phoneNumber,birthDate);
        // then
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "id"),
                () -> assertEquals(lastName, traineeDto.getLastName(), "lastName"),
                () -> assertEquals(firstName, traineeDto.getFirstName(), "firstName"),
                () -> assertEquals(email, traineeDto.getEmail(), "email"),
                () -> assertEquals(phoneNumber, traineeDto.getPhoneNumber(), "phone number"),
                () -> assertEquals(birthDate, traineeDto.getBirthDate(), "birthDate")
        );
    }

    // NB: It will be important to test special case like
    // - simple attribute with default value
    // - collection initialized to empty collection
    @Test
    void testBuilderPartial() {
        // given
        int id = 123;
        String lastName = "Doe";
        String firstName = "John";
        // when
        var traineeDto = TraineeDto.builder()
                .id(id)
                .lastName(lastName)
                .firstName(firstName)
                .build();
        // then
        assertAll(
                () -> assertEquals(id, traineeDto.getId(), "id"),
                () -> assertEquals(lastName, traineeDto.getLastName(), "lastName"),
                () -> assertEquals(firstName, traineeDto.getFirstName(), "firstName"),
                () -> assertNull(traineeDto.getEmail(), "email"),
                () -> assertNull(traineeDto.getPhoneNumber(), "phone number"),
                () -> assertNull(traineeDto.getBirthDate(), "birthDate")
        );
    }
}