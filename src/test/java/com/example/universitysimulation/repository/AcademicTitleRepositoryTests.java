package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AcademicTitleRepositoryTests {

    @Autowired
    private AcademicTitleRepository academicTitleRepository;

    @Test
    public void saveAcademicTitleTest() {
        // Arrange
        AcademicTitle academicTitle = new AcademicTitle("title_test");

        // Act
        AcademicTitle saved = academicTitleRepository.save(new AcademicTitle("title_test"));

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void deleteAcademicTitleTest() {
        // Arrange
        AcademicTitle academicTitle = academicTitleRepository.save(new AcademicTitle("title_test"));
        assertNotNull(academicTitle);

        // Act
        academicTitleRepository.delete(academicTitle);
        Optional<AcademicTitle> deleted = academicTitleRepository.findById(academicTitle.getId());

        // Assert
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Arrange
        AcademicTitle academicTitle = academicTitleRepository.save(new AcademicTitle(3L, "test_at"));

        // Act
        Optional<AcademicTitle> found = academicTitleRepository.findById(academicTitle.getId());

        // Assert
        assertNotNull(academicTitle);
        assertTrue(found.isPresent());
        assertEquals(academicTitle.getId(), found.get().getId());
    }
}
