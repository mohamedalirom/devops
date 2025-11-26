package tn.esprit.studentmanagement.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.services.IStudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Log4j2
public class StudentController {
IStudentService studentService;

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        log.info("📥 Received request: getAllStudents()");

        List<Student> students = studentService.getAllStudents();

        log.info("✔ Returned {} students", students.size());
        log.debug("Students list: {}", students);

        return students; }

    @GetMapping("/getStudent/{id}")
    public Student getStudent(@PathVariable Long id) {
        log.info("📥 Received request: getStudent(id = {})", id);

        Student student = studentService.getStudentById(id);

        if (student == null) {
            log.warn("⚠ Student with ID {} not found", id);
        } else {
            log.info("✔ Found student with ID {}", id);
            log.debug("Student data: {}", student);
        }

        return student; }

    @PostMapping("/createStudent")
    public Student createStudent(@RequestBody Student student) {
        log.info("📥 Received request: createStudent");
        log.debug("Student received: {}", student);

        Student created = studentService.saveStudent(student);

        log.info("✔ Student created with ID {}", created.getIdStudent());
        log.debug("Created student data: {}", created);

        return created;
    }

    @PutMapping("/updateStudent")
    public Student updateStudent(@RequestBody Student student) {

        log.info("📥 Received request: updateStudent");
        log.debug("Student data received for update: {}", student);

        Student updated = studentService.saveStudent(student);

        log.info("✔ Student updated with ID {}", updated.getIdStudent());
        log.debug("Updated student data: {}", updated);

        return updated;
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id) {
        log.info("📥 Received request: deleteStudent(id = {})", id);

        try {
            studentService.deleteStudent(id);
            log.info("✔ Successfully deleted student with ID {}", id);
        } catch (Exception e) {
            log.error("❌ Error deleting student with ID {}: {}", id, e.getMessage(), e);
        }
    }
}

