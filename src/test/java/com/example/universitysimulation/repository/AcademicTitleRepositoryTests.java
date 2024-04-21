package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitle;
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
public class AcademicTitleRepositoryTests {

    @Autowired
    private AcademicTitleRepository academicTitleRepository;

    @Test
    public void saveAcademicTitleTest() {
        // Create and save an academic title
        AcademicTitle academicTitle = academicTitleRepository.save(new AcademicTitle("title_test"));

        // Verify that the saved academic title is not null
        assertNotNull(academicTitle);
        assertNotNull(academicTitle.getId()); // Ensure the ID is generated
    }

    @Test
    public void deleteAcademicTitleTest() {
        // Create and save an academic title
        AcademicTitle academicTitle = academicTitleRepository.save(new AcademicTitle("title_test"));
        assertNotNull(academicTitle);

        // Delete the saved academic title
        academicTitleRepository.delete(academicTitle);

        // Verify that the academic title is deleted
        Optional<AcademicTitle> deleted = academicTitleRepository.findById(academicTitle.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Create and save an academic title with a specific ID
        AcademicTitle academicTitle = academicTitleRepository.save(new AcademicTitle(3L, "test_at"));
        assertNotNull(academicTitle);

        // Find the saved academic title by ID
        Optional<AcademicTitle> found = academicTitleRepository.findById(academicTitle.getId());

        // Verify that the academic title is found and matches the saved one
        assertTrue(found.isPresent());
        assertEquals(academicTitle.getId(), found.get().getId());
    }
}
