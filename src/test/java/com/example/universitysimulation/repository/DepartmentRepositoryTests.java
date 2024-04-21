package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class DepartmentRepositoryTests {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @Transactional
    public void saveDepartmentTest() {
        // Create and save an academic title
        Department department = departmentRepository.save(createNewDepartment());

        // Verify that the saved academic title is not null
        assertNotNull(department);
        assertNotNull(department.getId()); // Ensure the ID is generated
    }

    @Test
    @Transactional
    public void deleteDepartmentTest() {
        // Create and save an academic title
        Department department = departmentRepository.save(createNewDepartment());
        assertNotNull(department);

        // Delete the saved academic title
        departmentRepository.delete(department);

        // Verify that the academic title is deleted
        Optional<Department> deleted = departmentRepository.findById(department.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Create and save an academic title with a specific ID
        Department department = departmentRepository.save(createNewDepartment());
        assertNotNull(department);

        // Find the saved academic title by ID
        Optional<Department> found = departmentRepository.findById(department.getId());

        // Verify that the academic title is found and matches the saved one
        assertTrue(found.isPresent());
        assertEquals(department.getId(), found.get().getId());
    }

    @Test
    public void isDepartmentMemberApartFromDepartmentTest() {
        Department department = departmentRepository.save(createNewDepartment());
        assertNotNull(department);

        Boolean isApart = departmentRepository.isDepartmentWithMemberApartFromDepartment(5l,  2l);

        assertTrue(isApart);
    }

    private Department createNewDepartment() {
        Department department = new Department();
        department.setId(1l);
        department.setName("name_test");
        department.setShortName("short_test");
        Member secretary = new Member();
        Member head = new Member();
        secretary.setId(1l);
        head.setId(1l);
        department.setSecretary(secretary);
        department.setHeadOfDepartment(head);
        return department;
    }
}
