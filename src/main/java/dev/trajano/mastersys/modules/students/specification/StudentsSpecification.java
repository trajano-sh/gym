package dev.trajano.mastersys.modules.students.specification;

import dev.trajano.mastersys.modules.students.model.Students;
import dev.trajano.mastersys.modules.students.dto.StudentsFilterRequestDTO;
import org.springframework.data.jpa.domain.Specification;

public class StudentsSpecification {
    public static Specification<Students> filters(StudentsFilterRequestDTO filter) {
        return Specification
                .where(nameContent(filter.name()))
                .and(emailContent(filter.email()))
                .and(cellPhoneContent(filter.cellPhone()))
                .and(cityContent(filter.city()))
                .and(stateContent(filter.state()));
    }

    private static Specification<Students> stateContent(String state) {
        return (root, query, cb) -> {
            if (state == null || state.isBlank()) return null;

            return cb.equal(cb.lower(root.get("state")), "%" + state.toLowerCase());
        };
    }

    private static Specification<Students> cityContent(String city) {
        return (root, query, cb) -> {
            if (city == null || city.isBlank()) return null;

            return cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
        };
    }

    private static Specification<Students> cellPhoneContent(String cellPhone) {
        return (root, query, cb) -> {
            if (cellPhone == null || cellPhone.isBlank()) return null;

            return cb.like(cb.lower(root.get("cellPhone")), "%" + cellPhone.toLowerCase() + "%");
        };
    }

    private static Specification<Students> emailContent(String email) {
        return (root, query, cb) -> {
            if (email == null || email.isBlank()) return null;

            return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
        };
    }

    private static Specification<Students> nameContent(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) return null;

            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
}
