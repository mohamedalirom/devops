package tn.esprit.studentmanagement.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.services.IDepartmentService;

import java.util.List;

@RestController
@RequestMapping("/Department")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Log4j2
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping("/getAllDepartment")
    public List<Department> getAllDepartment() {

        log.info("📥 Received request: getAllDepartment()");

        List<Department> departments = departmentService.getAllDepartments();

        log.info("✔ Returned {} departments", departments.size());
        log.debug("Department list: {}", departments);

        return departments;
    }

    @GetMapping("/getDepartment/{id}")
    public Department getDepartment(@PathVariable Long id) {

        log.info("📥 Received request: getDepartment(id = {})", id);

        Department department = departmentService.getDepartmentById(id);

        if (department == null) {
            log.warn("⚠ Department with ID {} not found", id);
        } else {
            log.info("✔ Found department with ID {}", id);
            log.debug("Department data: {}", department);
        }

        return department;
    }

    @PostMapping("/createDepartment")
    public Department createDepartment(@RequestBody Department department) {

        log.info("📥 Received request: createDepartment");
        log.debug("Department received: {}", department);

        Department created = departmentService.saveDepartment(department);

        log.info("✔ Department created with ID {}", created.getIdDepartment());
        log.debug("Created department data: {}", created);

        return created;
    }

    @PutMapping("/updateDepartment")
    public Department updateDepartment(@RequestBody Department department) {

        log.info("📥 Received request: updateDepartment");
        log.debug("Department received for update: {}", department);

        Department updated = departmentService.saveDepartment(department);

        log.info("✔ Department updated with ID {}", updated.getIdDepartment());
        log.debug("Updated department data: {}", updated);

        return updated;
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public void deleteDepartment(@PathVariable Long id) {

        log.info("📥 Received request: deleteDepartment(id = {})", id);

        try {
            departmentService.deleteDepartment(id);
            log.info("✔ Successfully deleted department with ID {}", id);
        } catch (Exception e) {
            log.error("❌ Error deleting department with ID {}: {}", id, e.getMessage(), e);
        }
    }
}
