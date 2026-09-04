package dev.trajano.mastersys.dto;

import dev.trajano.mastersys.domain.Students;
import dev.trajano.mastersys.enums.SexEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record StudentsRequestDTO(@NotBlank String name, LocalDate dateOfBirth, SexEnum sex, String telephone, String cellPhone,
                                 String email, String observation, String address, String number, String complement,
                                 String neighborhood, String city, String state, String cep

) {
    public Students toEntity() {
        Students st = new Students();
        lora(st);
        return st;
    }

    public void lora(Students students) {
        students.setName(name);
        students.setDateOfBirth(dateOfBirth);
        students.setSex(sex);
        students.setTelephone(telephone);
        students.setCellPhone(cellPhone);
        students.setEmail(email);
        students.setObservations(observation);
        students.setAddress(address);
        students.setNumber(number);
        students.setComplements(complement);
        students.setNeighborhood(neighborhood);
        students.setState(state);
        students.setCep(cep);
    }
}
