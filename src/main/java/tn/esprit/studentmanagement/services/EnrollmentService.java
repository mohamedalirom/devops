package tn.esprit.studentmanagement.services;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.studentmanagement.repositories.EnrollmentRepository;
import tn.esprit.studentmanagement.entities.Enrollment;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class EnrollmentService implements IEnrollment {

    @Autowired
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public List<Enrollment> getAllEnrollments() {
        log.info("Fetching all enrollments");
        List<Enrollment> list = enrollmentRepository.findAll();
        log.info("Returned {} enrollments", list.size());
        log.debug("Enrollments list: {}", list);
        return list;
    }

    @Override
    public Enrollment getEnrollmentById(Long idEnrollment) {
        log.info("Fetching enrollment with ID {}", idEnrollment);
        Enrollment e = enrollmentRepository.findById(idEnrollment).orElse(null);
        if (e == null) {
            log.warn("Enrollment with ID {} not found", idEnrollment);
        } else {
            log.info("Enrollment found with ID {}", idEnrollment);
            log.debug("Enrollment data: {}", e);
        }
        return e;
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        log.info("Saving enrollment");
        log.debug("Enrollment to save: {}", enrollment);
        try {
            Enrollment saved = enrollmentRepository.save(enrollment);
            log.info("Enrollment saved with ID {}", saved.getIdEnrollment());
            log.debug("Saved enrollment data: {}", saved);
            return saved;
        } catch (Exception ex) {
            log.error("Error saving enrollment: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public void deleteEnrollment(Long idEnrollment) {
        log.info("Deleting enrollment with ID {}", idEnrollment);
        try {
            enrollmentRepository.deleteById(idEnrollment);
            log.info("Enrollment with ID {} deleted", idEnrollment);
        } catch (Exception ex) {
            log.error("Error deleting enrollment with ID {}: {}", idEnrollment, ex.getMessage(), ex);
            throw ex;
        }
    }
}
