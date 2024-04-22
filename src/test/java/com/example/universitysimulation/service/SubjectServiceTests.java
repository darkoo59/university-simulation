package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.SubjectRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.Subject;
import com.example.universitysimulation.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.universitysimulation.HelperTests.createNewSubject;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SubjectServiceTests {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectService subjectService;

    @Test
    public void getAllTest() {
        // Arrange
        Subject subject = createNewSubject();
        subjectRepository.save(subject);

        // Act
        List<SubjectDTO> founded = subjectService.getAll();

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByIdSuccessTest() {
        // Arrange
        Subject subject = createNewSubject();
        Subject saved = subjectRepository.save(subject);

        // Act
        SubjectDTO founded = subjectService.getById(saved.getId());

        // Assert
        assertNotNull(founded);
        assertEquals(founded.getId(), saved.getId());
    }

    @Test
    public void getByIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            subjectService.getById(Long.MAX_VALUE);
        });
    }

    @Test
    public void createSuccessTest() {
        // Arrange
        SubjectRequest request = new SubjectRequest();
        request.setName("New test subjecy");
        request.setEspb(9);
        request.setDepartmentId(1l);

        // Act
        SubjectDTO saved = subjectService.create(request);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void createFailureTest() {
        // Arrange
        SubjectRequest request = new SubjectRequest();
        request.setName("New test subject");
        request.setEspb(9);
        request.setDepartmentId(Long.MAX_VALUE);

        // Act & Assert
        assertThrows(NotFoundInDataBaseException.class, () -> {
            subjectService.create(request);
        });
    }

    @Test
    public void deleteSuccessTest() {
        // Arrange
        Subject subject = createNewSubject();
        Subject saved = subjectRepository.save(subject);

        // Act
        subjectService.delete(saved.getId());

        // Assert
        assertThrows(NotFoundInDataBaseException.class, () -> {
            subjectService.getById(saved.getId());
        });
    }

    @Test
    public void deleteFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            subjectService.delete(Long.MAX_VALUE);
        });
    }

    @Test
    public void updateSuccessTest() {
        // Arrange
        Subject subject = createNewSubject();
        Subject saved = subjectRepository.save(subject);
        SubjectRequest request = new SubjectRequest();
        request.setName("Updated name");
        request.setEspb(9);
        request.setDepartmentId(2l);

        // Act
        SubjectDTO updated = subjectService.update(request, saved.getId());

        // Assert
        assertEquals(updated.getDepartment().getId(), request.getDepartmentId());
        assertEquals(saved.getId(), updated.getId());
        assertEquals(updated.getName(), request.getName());
    }

    @Test
    public void updateFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            subjectService.update(new SubjectRequest(), Long.MAX_VALUE);
        });
    }

    @Test
    public void deleteDepartmentSuccessTest() {
        // Arrange
        Subject subject = createNewSubject();
        Subject saved = subjectRepository.save(subject);

        // Act
        subjectService.deleteDepartment(saved.getId());
        Subject found = subjectRepository.findById(saved.getId()).get();

        // Assert
        assertNull(found.getDepartment());
    }

    @Test
    public void deleteDepartmentFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            subjectService.deleteDepartment(Long.MAX_VALUE);
        });
    }

    @Test
    public void updateDepartmentSuccessTest() {
        // Arrange
        Subject subject = createNewSubject();
        Subject saved = subjectRepository.save(subject);

        // Act
        SubjectDTO updated = subjectService.updateDepartment(saved.getId(), 2l);

        // Assert
        assertNotNull(updated);
        assertEquals(updated.getDepartment().getId(), 2l);
    }

    @Test
    public void updateDepartmentFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            subjectService.updateDepartment(Long.MAX_VALUE, Long.MAX_VALUE);
        });
    }
}
