package tn.esprit.studentmanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.repositories.EnrollmentRepository;
import tn.esprit.studentmanagement.services.EnrollmentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    public void testGetAllEnrollments() {
        when(enrollmentRepository.findAll()).thenReturn(
                Arrays.asList(new Enrollment(), new Enrollment())
        );

        List<Enrollment> list = enrollmentService.getAllEnrollments();

        assertEquals(2, list.size());
        verify(enrollmentRepository).findAll();
    }

    @Test
    public void testGetEnrollmentById() {
        Enrollment e = new Enrollment();
        e.setIdEnrollment(3L);

        when(enrollmentRepository.findById(3L)).thenReturn(Optional.of(e));

        Enrollment result = enrollmentService.getEnrollmentById(3L);

        assertNotNull(result);
        assertEquals(3L, result.getIdEnrollment());
    }

    @Test
    public void testSaveEnrollment() {
        Enrollment e = new Enrollment();
        e.setIdEnrollment(4L);

        when(enrollmentRepository.save(e)).thenReturn(e);

        Enrollment result = enrollmentService.saveEnrollment(e);

        assertEquals(4L, result.getIdEnrollment());
    }

    @Test
    public void testDeleteEnrollment() {
        enrollmentService.deleteEnrollment(11L);

        verify(enrollmentRepository).deleteById(11L);
    }
}
