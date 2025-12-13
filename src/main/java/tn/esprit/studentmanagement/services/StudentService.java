package tn.esprit.studentmanagement.services;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class StudentService implements IStudentService {

    @Autowired
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        log.info("Fetching all students");
        List<Student> students = studentRepository.findAll();
        log.info("Returned {} students", students.size());
        log.debug("Students list: {}", students);
        return students;
    }

    @Override
    public Student getStudentById(Long id) {
        log.info("Fetching student with ID {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            log.warn("Student with ID {} not found", id);
        } else {
            log.info("Student found with ID {}", id);
            log.debug("Student data: {}", student);
        }
        return student;
    }

    @Override
    public Student saveStudent(Student student) {
        log.info("Saving student");
        log.debug("Student to save: {}", student);
        try {
            Student saved = studentRepository.save(student);
            log.info("Student saved with ID {}", saved.getIdStudent());
            log.debug("Saved student data: {}", saved);
            return saved;
        } catch (Exception e) {
            log.error("Error saving student: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Deleting student with ID {}", id);
        try {
            studentRepository.deleteById(id);
            log.info("Student with ID {} deleted", id);
        } catch (Exception e) {
            log.error("Error deleting student with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}
