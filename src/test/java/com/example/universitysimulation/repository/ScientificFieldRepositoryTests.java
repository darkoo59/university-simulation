package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.EducationTitle;
import com.example.universitysimulation.model.ScientificField;
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
public class ScientificFieldRepositoryTests {
    @Autowired
    private ScientificFieldRepository scientificFieldRepository;

    @Test
    public void saveScientificFieldTest() {
        // Create and save an academic title
        ScientificField scientificField = scientificFieldRepository.save(new ScientificField("field_test"));

        // Verify that the saved academic title is not null
        assertNotNull(scientificField);
        assertNotNull(scientificField.getId()); // Ensure the ID is generated
    }

    @Test
    public void deleteScientificFieldTest() {
        // Create and save an academic title
        ScientificField scientificField = scientificFieldRepository.save(new ScientificField("field_test"));
        assertNotNull(scientificField);

        // Delete the saved academic title
        scientificFieldRepository.delete(scientificField);

        // Verify that the academic title is deleted
        Optional<ScientificField> deleted = scientificFieldRepository.findById(scientificField.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Create and save an academic title with a specific ID
        ScientificField scientificField = scientificFieldRepository.save(new ScientificField(3L, "field_test"));
        assertNotNull(scientificField);

        // Find the saved academic title by ID
        Optional<ScientificField> found = scientificFieldRepository.findById(scientificField.getId());

        // Verify that the academic title is found and matches the saved one
        assertTrue(found.isPresent());
        assertEquals(scientificField.getId(), found.get().getId());
    }
}
