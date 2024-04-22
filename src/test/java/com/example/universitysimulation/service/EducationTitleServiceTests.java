package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.EducationTitleDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.dto.request.EducationTitleRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.model.EducationTitle;
import com.example.universitysimulation.repository.AcademicTitleRepository;
import com.example.universitysimulation.repository.EducationTitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class EducationTitleServiceTests {
    @Autowired
    private EducationTitleRepository educationTitleRepository;
    @Autowired
    private EducationTitleService educationTitleService;

    @Test
    public void getAllTest() {
        // Arrange
        EducationTitle educationTitle = new EducationTitle("title_test");
        educationTitleRepository.save(educationTitle);

        // Act
        List<EducationTitleDTO> founded = educationTitleService.getAll();

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByIdSuccessTest() {
        // Arrange
        EducationTitle educationTitle = new EducationTitle("title_test");
        EducationTitle saved = educationTitleRepository.save(educationTitle);

        // Act
        EducationTitleDTO founded = educationTitleService.getById(saved.getId());

        // Assert
        assertNotNull(founded);
        assertEquals(founded.getId(), saved.getId());
    }

    @Test
    public void getByIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            educationTitleService.getById(Long.MAX_VALUE);
        });
    }

    @Test
    public void createTest() {
        // Arrange
        EducationTitleRequest request = new EducationTitleRequest();
        request.setTitle("title_test");

        // Act
        EducationTitleDTO saved = educationTitleService.create(request);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void deleteSuccessTest() {
        // Arrange
        EducationTitle educationTitle = new EducationTitle("title_test");
        EducationTitle saved = educationTitleRepository.save(educationTitle);

        // Act
        educationTitleService.delete(saved.getId());

        // Assert
        assertThrows(NotFoundInDataBaseException.class, () -> {
            educationTitleService.getById(saved.getId());
        });
    }

    @Test
    public void deleteFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            educationTitleService.delete(Long.MAX_VALUE);
        });
    }

    @Test
    public void updateSuccessTest() {
        // Arrange
        EducationTitle educationTitle = new EducationTitle("title_test");
        EducationTitle saved = educationTitleRepository.save(educationTitle);
        EducationTitleRequest educationTitleRequest = new EducationTitleRequest();
        educationTitleRequest.setTitle("updated_test");

        // Act
        EducationTitleDTO updated = educationTitleService.update(educationTitleRequest, saved.getId());

        // Assert
        assertEquals(updated.getTitle(), educationTitleRequest.getTitle());
        assertEquals(saved.getId(), updated.getId());
        assertNotEquals(updated.getTitle(), "title_test");
    }

    @Test
    public void updateFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            educationTitleService.update(new EducationTitleRequest(), Long.MAX_VALUE);
        });
    }
}
