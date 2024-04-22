package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.DepartmentDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.DepartmentRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.universitysimulation.HelperTests.createNewDepartment;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DepartmentServiceTests {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentService departmentService;

    @Test
    public void getAllTest() {
        // Arrange
        departmentRepository.save(createNewDepartment());

        // Act
        List<DepartmentDTO> founded = departmentService.getAll();

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByIdSuccessTest() {
        // Arrange
        Department department = createNewDepartment();
        Department saved = departmentRepository.save(department);

        // Act
        DepartmentDTO founded = departmentService.getById(saved.getId());

        // Assert
        assertNotNull(founded);
        assertEquals(founded.getId(), saved.getId());
    }

    @Test
    public void getByIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentService.getById(Long.MAX_VALUE);
        });
    }

    @Test
    public void createTest() {
        // Arrange
        DepartmentRequest request = new DepartmentRequest();
        request.setName("test_department");
        request.setShortName("test_d");

        // Act
        DepartmentDTO saved = departmentService.create(request);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void deleteSuccessTest() {
        // Arrange
        Department department = createNewDepartment();
        Department saved = departmentRepository.save(department);

        // Act
        departmentService.delete(saved.getId());

        // Assert
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentService.getById(saved.getId());
        });
    }

    @Test
    public void deleteFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentService.delete(Long.MAX_VALUE);
        });
    }

    @Test
    public void updateSuccessTest() {
        // Arrange
        Department department = createNewDepartment();
        Department saved = departmentRepository.save(department);
        DepartmentRequest departmentRequest = new DepartmentRequest();
        departmentRequest.setName("updated_department");

        // Act
        DepartmentDTO updated = departmentService.update(departmentRequest, saved.getId());

        // Assert
        assertEquals(updated.getName(), departmentRequest.getName());
        assertEquals(saved.getId(), updated.getId());
        assertNotEquals(updated.getName(), "name_test");
    }

    @Test
    public void updateFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentService.update(new DepartmentRequest(), Long.MAX_VALUE);
        });
    }

    @Test
    public void getAllMembersSuccessTest() {
        // Act
        List<MemberDTO> founded = departmentService.getAllMembers(1l);

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getAllMembersFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentService.getAllMembers(Long.MAX_VALUE);
        });
    }

    @Test
    public void getAllSubjectsSuccessTest() {
        // Act
        List<SubjectDTO> founded = departmentService.getAllSubjects(1l);

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getAllSubjectsFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentService.getAllSubjects(Long.MAX_VALUE);
        });
    }

    @Test
    public void updateHeadOfDepartmentFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentService.updateHeadOfDepartment(Long.MAX_VALUE, Long.MAX_VALUE);
        });
    }

    @Test
    public void updateSecretaryFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentService.updateHeadOfDepartment(Long.MAX_VALUE, Long.MAX_VALUE);
        });
    }
}
