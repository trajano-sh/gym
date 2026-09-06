package dev.trajano.gym.modules.student.specification;

import dev.trajano.gym.modules.student.domain.Student;
import dev.trajano.gym.modules.student.dto.StudentFilterRequestDTO;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecification {
    public static Specification<Student> filters(StudentFilterRequestDTO filter) {
        return Specification
                .where(nameContent(filter.name()))
                .and(emailContent(filter.email()))
                .and(cellPhoneContent(filter.cellPhone()))
                .and(cityContent(filter.city()))
                .and(stateContent(filter.state()));
    }

    private static Specification<Student> stateContent(String state) {
        return (root, query, cb) -> {
            if (state == null || state.isBlank()) return null;

            return cb.equal(cb.lower(root.get("state")), "%" + state.toLowerCase());
        };
    }

    private static Specification<Student> cityContent(String city) {
        return (root, query, cb) -> {
            if (city == null || city.isBlank()) return null;

            return cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
        };
    }

    private static Specification<Student> cellPhoneContent(String cellPhone) {
        return (root, query, cb) -> {
            if (cellPhone == null || cellPhone.isBlank()) return null;

            return cb.like(cb.lower(root.get("cellPhone")), "%" + cellPhone.toLowerCase() + "%");
        };
    }

    private static Specification<Student> emailContent(String email) {
        return (root, query, cb) -> {
            if (email == null || email.isBlank()) return null;

            return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
        };
    }

    private static Specification<Student> nameContent(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return null;

            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
}
