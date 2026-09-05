package dev.trajano.gym.modules.students.mapper;

import dev.trajano.gym.modules.students.dto.StudentsRequestDTO;
import dev.trajano.gym.modules.students.dto.StudentsResponseDTO;
import dev.trajano.gym.modules.students.model.Students;
import org.springframework.stereotype.Component;

@Component
public class StudentsMapper {
    public Students toEntity(StudentsRequestDTO request){
        Students students = new Students();
        students.setName(request.name());
        students.setDateOfBirth(request.dateOfBirth());
        students.setSex(request.sex());
        students.setTelephone(request.telephone());
        students.setCellPhone(request.cellPhone());
        students.setEmail(request.email());
        students.setObservations(request.observation());
        students.setAddress(request.address());
        students.setCity(request.city());
        students.setNumber(request.number());
        students.setComplements(request.complement());
        students.setNeighborhood(request.neighborhood());
        students.setState(request.state());
        students.setCep(request.cep());
        return students;
    }

    public StudentsResponseDTO fromEntity(Students students) {
        return new StudentsResponseDTO(
                students.getId(),
                students.getName(),
                students.getDateOfBirth(),
                students.getSex(),
                students.getCellPhone(),
                students.getEmail(),
                students.getCity(),
                students.getState(),
                students.getCreatedAt());
    }

    public Students toUpdate(Students students,StudentsRequestDTO request){
        students.setName(request.name());
        students.setDateOfBirth(request.dateOfBirth());
        students.setSex(request.sex());
        students.setTelephone(request.telephone());
        students.setCellPhone(request.cellPhone());
        students.setEmail(request.email());
        students.setObservations(request.observation());
        students.setAddress(request.address());
        students.setCity(request.city());
        students.setNumber(request.number());
        students.setComplements(request.complement());
        students.setNeighborhood(request.neighborhood());
        students.setState(request.state());
        students.setCep(request.cep());
        return students;
    }
}
