package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.repository.AcademicTitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class AcademicTitleServiceTests {
    @Autowired
    private AcademicTitleRepository academicTitleRepository;
    @Autowired
    private AcademicTitleService academicTitleService;

    @Test
    public void getAllTest() {
        // Arrange
        AcademicTitle academicTitle = new AcademicTitle("title_test");
        academicTitleRepository.save(academicTitle);

        // Act
        List<AcademicTitleDTO> founded = academicTitleService.getAll();

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByIdSuccessTest() {
        // Arrange
        AcademicTitle academicTitle = new AcademicTitle("title_test");
        AcademicTitle saved = academicTitleRepository.save(academicTitle);

        // Act
        AcademicTitleDTO founded = academicTitleService.getById(saved.getId());

        // Assert
        assertNotNull(founded);
        assertEquals(founded.getId(), saved.getId());
    }

    @Test
    public void getByIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            academicTitleService.getById(Long.MAX_VALUE);
        });
    }

    @Test
    public void createTest() {
        // Arrange
        AcademicTitleRequest request = new AcademicTitleRequest();
        request.setTitle("title_test");

        // Act
        AcademicTitleDTO saved = academicTitleService.create(request);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void deleteSuccessTest() {
        // Arrange
        AcademicTitle academicTitle = new AcademicTitle("title_test");
        AcademicTitle saved = academicTitleRepository.save(academicTitle);

        // Act
        academicTitleService.delete(saved.getId());

        // Assert
        assertThrows(NotFoundInDataBaseException.class, () -> {
            academicTitleService.getById(saved.getId());
        });
    }

    @Test
    public void deleteFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            academicTitleService.delete(Long.MAX_VALUE);
        });
    }

    @Test
    public void updateSuccessTest() {
        // Arrange
        AcademicTitle academicTitle = new AcademicTitle("title_test");
        AcademicTitle saved = academicTitleRepository.save(academicTitle);
        AcademicTitleRequest academicTitleRequest = new AcademicTitleRequest();
        academicTitleRequest.setTitle("updated_test");

        // Act
        AcademicTitleDTO updated = academicTitleService.update(academicTitleRequest, saved.getId());

        // Assert
        assertEquals(updated.getTitle(), academicTitleRequest.getTitle());
        assertEquals(saved.getId(), updated.getId());
        assertNotEquals(updated.getTitle(), "title_test");
    }

    @Test
    public void updateFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            academicTitleService.update(new AcademicTitleRequest(), Long.MAX_VALUE);
        });
    }
}
