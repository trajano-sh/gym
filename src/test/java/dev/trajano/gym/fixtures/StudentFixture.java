package dev.trajano.gym.fixtures;

import dev.trajano.gym.modules.student.domain.SexEnum;
import dev.trajano.gym.modules.student.domain.Student;
import dev.trajano.gym.modules.student.dto.StudentRequestDTO;

import java.time.LocalDate;
import java.util.UUID;

public final class StudentFixture {

    private StudentFixture() {
    }

    public static Student validStudent() {
        return studentWithId(1L);
    }

    public static Student studentWithId(Long id) {
        String suffix = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        Student student = new Student();

        student.setId(id);
        student.setName("Test User " + suffix);
        student.setDateOfBirth(LocalDate.of(2001, 1, 1));
        student.setSex(SexEnum.M);
        student.setTelephone("11999999999");
        student.setCellPhone("11988888888");
        student.setEmail("student-" + suffix + "@gmail.com");
        student.setAddress("Rua Test");
        student.setNumber("100");
        student.setNeighborhood("Brickell");
        student.setCity("Miami");
        student.setState("FL");
        student.setCep("00000-000");

        return student;
    }

    public static StudentRequestDTO validRequest() {
        String suffix = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        return new StudentRequestDTO(
                "Test User " + suffix,
                LocalDate.of(2001, 1, 1),
                SexEnum.M,
                "11999999999",
                "11988888888",
                "student-" + suffix + "@gmail.com",
                null,
                "Rua Test",
                "100",
                null,
                "Brickell",
                "Miami",
                "FL",
                "00000-000"
        );
    }
}