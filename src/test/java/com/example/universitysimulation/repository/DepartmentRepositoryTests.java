package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.universitysimulation.HelperTests.createNewDepartment;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DepartmentRepositoryTests {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @Transactional
    public void saveDepartmentTest() {
        // Arrange
        Department department = createNewDepartment();

        // Act
        Department saved = departmentRepository.save(department);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId()); // Ensure the ID is generated
    }

    @Test
    @Transactional
    public void deleteDepartmentTest() {
        // Arrange
        Department department = departmentRepository.save(createNewDepartment());

        // Act
        departmentRepository.delete(department);
        Optional<Department> deleted = departmentRepository.findById(department.getId());

        // Assert
        assertNotNull(department);
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Arrange
        Department department = departmentRepository.save(createNewDepartment());

        // Act
        Optional<Department> found = departmentRepository.findById(department.getId());

        // Assert
        assertNotNull(department);
        assertTrue(found.isPresent());
        assertEquals(department.getId(), found.get().getId());
    }

    @Test
    public void isDepartmentMemberApartFromDepartmentTest() {
        // Arrange
        Department department = departmentRepository.save(createNewDepartment());

        // Act
        Boolean isApart = departmentRepository.isDepartmentWithMemberApartFromDepartment(5l,  2l);

        // Assert
        assertNotNull(department);
        assertTrue(isApart);
    }
}
