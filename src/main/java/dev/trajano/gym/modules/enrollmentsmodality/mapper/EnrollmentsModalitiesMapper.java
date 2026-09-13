package dev.trajano.gym.modules.enrollmentsmodality.mapper;

import dev.trajano.gym.modules.enrollment.domain.Enrollment;
import dev.trajano.gym.modules.enrollmentsmodality.domain.EnrollmentsModalities;
import dev.trajano.gym.modules.enrollmentsmodality.dto.EnrollmentsModalitiesResponseDTO;
import dev.trajano.gym.modules.graduation.domain.Graduations;
import dev.trajano.gym.modules.modality.domain.Modalities;
import dev.trajano.gym.modules.plan.domain.Plan;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentsModalitiesMapper {
    public EnrollmentsModalities toEntity(Enrollment enrollment, Modalities modality, Graduations graduations, Plan plan){
        EnrollmentsModalities enrollmentsModalities = new EnrollmentsModalities();
        enrollmentsModalities.setEnrollmentId(enrollment);
        enrollmentsModalities.setModalityId(modality);
        enrollmentsModalities.setGraduationsId(graduations);
        enrollmentsModalities.setPlanId(plan);
        return enrollmentsModalities;
    }

    public EnrollmentsModalitiesResponseDTO fromEntity(EnrollmentsModalities enrollmentsModalities){
        return new EnrollmentsModalitiesResponseDTO(
                enrollmentsModalities.getId(),
                enrollmentsModalities.getEnrollmentId().getId(),
                enrollmentsModalities.getModalityId().getId(),
                enrollmentsModalities.getGraduationsId().getId(),
                enrollmentsModalities.getPlanId().getId(),
                enrollmentsModalities.getStartDate(),
                enrollmentsModalities.getEndDate()
        );
    }

    public EnrollmentsModalities toUpdate(EnrollmentsModalities enrollmentsModalities, Enrollment enrollmentUpdate, Graduations graduationsUpdate,Modalities modalityUpdate,Plan planUpdate){
        enrollmentsModalities.setEnrollmentId(enrollmentUpdate);
        enrollmentsModalities.setGraduationsId(graduationsUpdate);
        enrollmentsModalities.setModalityId(modalityUpdate);
        enrollmentsModalities.setPlanId(planUpdate);
        return enrollmentsModalities;
    }
}
