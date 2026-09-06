package dev.trajano.gym.modules.student.mapper;

import dev.trajano.gym.modules.student.domain.Student;
import dev.trajano.gym.modules.student.dto.StudentRequestDTO;
import dev.trajano.gym.modules.student.dto.StudentResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(StudentRequestDTO request){
        Student student = new Student();
        student.setName(request.name());
        student.setDateOfBirth(request.dateOfBirth());
        student.setSex(request.sex());
        student.setTelephone(request.telephone());
        student.setCellPhone(request.cellPhone());
        student.setEmail(request.email());
        student.setObservations(request.observation());
        student.setAddress(request.address());
        student.setCity(request.city());
        student.setNumber(request.number());
        student.setComplements(request.complement());
        student.setNeighborhood(request.neighborhood());
        student.setState(request.state());
        student.setCep(request.cep());
        return student;
    }

    public StudentResponseDTO fromEntity(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getDateOfBirth(),
                student.getSex(),
                student.getCellPhone(),
                student.getEmail(),
                student.getCity(),
                student.getState(),
                student.getCreatedAt());
    }

    public Student toUpdate(Student student, StudentRequestDTO request){
        student.setName(request.name());
        student.setDateOfBirth(request.dateOfBirth());
        student.setSex(request.sex());
        student.setTelephone(request.telephone());
        student.setCellPhone(request.cellPhone());
        student.setEmail(request.email());
        student.setObservations(request.observation());
        student.setAddress(request.address());
        student.setCity(request.city());
        student.setNumber(request.number());
        student.setComplements(request.complement());
        student.setNeighborhood(request.neighborhood());
        student.setState(request.state());
        student.setCep(request.cep());
        return student;
    }
}
