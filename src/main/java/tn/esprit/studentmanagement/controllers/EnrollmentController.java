package tn.esprit.studentmanagement.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.services.IEnrollment;

import java.util.List;

@RestController
@RequestMapping("/Enrollment")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Log4j2
public class EnrollmentController {

    private final IEnrollment enrollmentService;

    @GetMapping("/getAllEnrollment")
    public List<Enrollment> getAllEnrollment() {

        log.info("📥 Received request: getAllEnrollment()");

        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        log.info("✔ Returned {} enrollments", enrollments.size());
        log.debug("Enrollments list: {}", enrollments);

        return enrollments;
    }

    @GetMapping("/getEnrollment/{id}")
    public Enrollment getEnrollment(@PathVariable Long id) {

        log.info("📥 Received request: getEnrollment(id = {})", id);

        Enrollment enrollment = enrollmentService.getEnrollmentById(id);

        if (enrollment == null) {
            log.warn("⚠ Enrollment with ID {} not found", id);
        } else {
            log.info("✔ Found enrollment with ID {}", id);
            log.debug("Enrollment data: {}", enrollment);
        }

        return enrollment;
    }

    @PostMapping("/createEnrollment")
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {

        log.info("📥 Received request: createEnrollment");
        log.debug("Enrollment received: {}", enrollment);

        Enrollment created = enrollmentService.saveEnrollment(enrollment);

        log.info("✔ Enrollment created with ID {}", created.getIdEnrollment());
        log.debug("Created enrollment data: {}", created);

        return created;
    }


    @PutMapping("/updateEnrollment")
    public Enrollment updateEnrollment(@RequestBody Enrollment enrollment) {

        log.info("📥 Received request: updateEnrollment");
        log.debug("Enrollment received for update: {}", enrollment);

        Enrollment updated = enrollmentService.saveEnrollment(enrollment);

        log.info("✔ Enrollment updated with ID {}", updated.getIdEnrollment());
        log.debug("Updated enrollment data: {}", updated);

        return updated;
    }

    @DeleteMapping("/deleteEnrollment/{id}")
    public void deleteEnrollment(@PathVariable Long id) {

        log.info("📥 Received request: deleteEnrollment(id = {})", id);

        try {
            enrollmentService.deleteEnrollment(id);
            log.info("✔ Successfully deleted enrollment with ID {}", id);
        } catch (Exception e) {
            log.error("❌ Error deleting enrollment with ID {}: {}", id, e.getMessage(), e);
        }
    }
}
