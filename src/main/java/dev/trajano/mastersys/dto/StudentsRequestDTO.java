package dev.trajano.mastersys.dto;

import dev.trajano.mastersys.domain.Students;
import dev.trajano.mastersys.enums.SexEnum;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentsRequestDTO(@NotBlank(message = "Name is required") @Size(max = 150,message = "Name max 150 character")
                                 String name,

                                 @Past(message = "It needs to be in the past tense.")
                                 LocalDate dateOfBirth,

                                 @NotNull(message = "Sex is required")
                                 SexEnum sex,

                                 @NotBlank(message = "Telephone is required")
                                 @Size(max = 30)
                                 String telephone,

                                 @NotBlank(message = "CellPhone is required")
                                 @Size(max = 30)
                                 String cellPhone,

                                 @NotBlank(message = "Email is required")
                                 @Size(max = 150,message = "Max in email 150 character")
                                 @Email(message = "Email invalid")
                                 String email,

                                 String observation,

                                 @NotBlank(message = "Address is required")
                                 @Size(max = 150,message = "Max in address 150 character")
                                 String address,

                                 @Size(max = 20,message = "Required max 20 character in number")
                                 @NotBlank(message = "Number is required")
                                 String number,

                                 @NotBlank(message = "Complement is required")
                                 String complement,

                                 @NotBlank(message = "Neighborhood is required")
                                 String neighborhood,

                                 @NotBlank(message = "City is required")
                                 String city,

                                 @NotBlank(message = "State is required")
                                 String state,

                                 @NotBlank(message = "Cep is required")
                                 String cep

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
        students.setCity(city);
        students.setNumber(number);
        students.setComplements(complement);
        students.setNeighborhood(neighborhood);
        students.setState(state);
        students.setCep(cep);
    }
}
