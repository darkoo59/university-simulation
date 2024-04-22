package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.EducationTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EducationTitleRepositoryTests {

    @Autowired
    private EducationTitleRepository educationTitleRepository;

    @Test
    public void saveEducationTitleTest() {
        // Arrange
        EducationTitle educationTitle = new EducationTitle("title_test");

        // Act
        EducationTitle saved = educationTitleRepository.save(educationTitle);

        // Assert
        assertNotNull(educationTitle);
        assertNotNull(educationTitle.getId());
    }

    @Test
    public void deleteEducationTitleTest() {
        // Arrange
        EducationTitle educationTitle = educationTitleRepository.save(new EducationTitle("title_test"));

        // Act
        educationTitleRepository.delete(educationTitle);
        Optional<EducationTitle> deleted = educationTitleRepository.findById(educationTitle.getId());

        // Assert
        assertNotNull(educationTitle);
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Arrange
        EducationTitle educationTitle = educationTitleRepository.save(new EducationTitle(3L, "test_at"));

        // Act
        Optional<EducationTitle> found = educationTitleRepository.findById(educationTitle.getId());

        // Assert
        assertNotNull(educationTitle);
        assertTrue(found.isPresent());
        assertEquals(educationTitle.getId(), found.get().getId());
    }
}
