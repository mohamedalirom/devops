package tn.esprit.studentmanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;
import tn.esprit.studentmanagement.services.StudentService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @Test
    void shouldReturnAllStudents() {
        when(studentRepository.findAll())
                .thenReturn(List.of(new Student(), new Student()));

        List<Student> students = studentService.getAllStudents();

        assertEquals(2, students.size());
        verify(studentRepository).findAll();
    }

    @Test
    void shouldReturnStudentById() {
        Student s = new Student();
        s.setIdStudent(1L);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(s));

        Student result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdStudent());
    }

    @Test
    void shouldSaveStudent() {
        Student s = new Student();
        s.setIdStudent(10L);

        when(studentRepository.save(s)).thenReturn(s);

        Student result = studentService.saveStudent(s);

        assertEquals(10L, result.getIdStudent());
        verify(studentRepository).save(s);
    }

    @Test
    void shouldDeleteStudent() {
        studentService.deleteStudent(5L);
        verify(studentRepository).deleteById(5L);
    }
}
