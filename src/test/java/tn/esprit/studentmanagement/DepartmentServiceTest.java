package tn.esprit.studentmanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;
import tn.esprit.studentmanagement.services.DepartmentService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    DepartmentRepository departmentRepository;

    @InjectMocks
    DepartmentService departmentService;

    @Test
    void shouldReturnAllDepartments() {
        when(departmentRepository.findAll())
                .thenReturn(List.of(new Department(), new Department()));

        List<Department> list = departmentService.getAllDepartments();

        assertEquals(2, list.size());
        verify(departmentRepository).findAll();
    }

    @Test
    void shouldReturnDepartmentById() {
        Department d = new Department();
        d.setIdDepartment(7L);

        when(departmentRepository.findById(7L)).thenReturn(Optional.of(d));

        Department result = departmentService.getDepartmentById(7L);

        assertNotNull(result);
        assertEquals(7L, result.getIdDepartment());
    }

    @Test
    void shouldSaveDepartment() {
        Department d = new Department();
        d.setIdDepartment(2L);

        when(departmentRepository.save(d)).thenReturn(d);

        Department result = departmentService.saveDepartment(d);

        assertEquals(2L, result.getIdDepartment());
        verify(departmentRepository).save(d);
    }

    @Test
    void shouldDeleteDepartment() {
        departmentService.deleteDepartment(9L);

        verify(departmentRepository).deleteById(9L);
    }
}
