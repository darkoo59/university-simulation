package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.model.EducationTitle;
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
public class EducationTitleRepositoryTests {

    @Autowired
    private EducationTitleRepository educationTitleRepository;

    @Test
    public void saveEducationTitleTest() {
        // Create and save an academic title
        EducationTitle educationTitle = educationTitleRepository.save(new EducationTitle("title_test"));

        // Verify that the saved academic title is not null
        assertNotNull(educationTitle);
        assertNotNull(educationTitle.getId()); // Ensure the ID is generated
    }

    @Test
    public void deleteEducationTitleTest() {
        // Create and save an academic title
        EducationTitle educationTitle = educationTitleRepository.save(new EducationTitle("title_test"));
        assertNotNull(educationTitle);

        // Delete the saved academic title
        educationTitleRepository.delete(educationTitle);

        // Verify that the academic title is deleted
        Optional<EducationTitle> deleted = educationTitleRepository.findById(educationTitle.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Create and save an academic title with a specific ID
        EducationTitle educationTitle = educationTitleRepository.save(new EducationTitle(3L, "test_at"));
        assertNotNull(educationTitle);

        // Find the saved academic title by ID
        Optional<EducationTitle> found = educationTitleRepository.findById(educationTitle.getId());

        // Verify that the academic title is found and matches the saved one
        assertTrue(found.isPresent());
        assertEquals(educationTitle.getId(), found.get().getId());
    }
}
