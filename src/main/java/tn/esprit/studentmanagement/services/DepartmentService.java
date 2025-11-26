package tn.esprit.studentmanagement.services;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class DepartmentService implements IDepartmentService {

    @Autowired
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        log.info("Fetching all departments");
        List<Department> list = departmentRepository.findAll();
        log.info("Returned {} departments", list.size());
        log.debug("Departments list: {}", list);
        return list;
    }

    @Override
    public Department getDepartmentById(Long idDepartment) {
        log.info("Fetching department with ID {}", idDepartment);
        Department dept = departmentRepository.findById(idDepartment).orElse(null);
        if (dept == null) {
            log.warn("Department with ID {} not found", idDepartment);
        } else {
            log.info("Department found with ID {}", idDepartment);
            log.debug("Department data: {}", dept);
        }
        return dept;
    }

    @Override
    public Department saveDepartment(Department department) {
        log.info("Saving department");
        log.debug("Department to save: {}", department);
        try {
            Department saved = departmentRepository.save(department);
            log.info("Department saved with ID {}", saved.getIdDepartment());
            log.debug("Saved department data: {}", saved);
            return saved;
        } catch (Exception ex) {
            log.error("Error saving department: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public void deleteDepartment(Long idDepartment) {
        log.info("Deleting department with ID {}", idDepartment);
        try {
            departmentRepository.deleteById(idDepartment);
            log.info("Department with ID {} deleted", idDepartment);
        } catch (Exception ex) {
            log.error("Error deleting department with ID {}: {}", idDepartment, ex.getMessage(), ex);
            throw ex;
        }
    }
}
