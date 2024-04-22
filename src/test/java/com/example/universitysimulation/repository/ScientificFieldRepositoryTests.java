package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.ScientificField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ScientificFieldRepositoryTests {
    @Autowired
    private ScientificFieldRepository scientificFieldRepository;

    @Test
    public void saveScientificFieldTest() {
        // Arrange
        ScientificField scientificField = new ScientificField("field_test");

        // Act
        ScientificField saved = scientificFieldRepository.save(scientificField);

        // Assert
        assertNotNull(scientificField);
        assertNotNull(scientificField.getId());
    }

    @Test
    public void deleteScientificFieldTest() {
        // Arrange
        ScientificField scientificField = scientificFieldRepository.save(new ScientificField("field_test"));

        // Act
        scientificFieldRepository.delete(scientificField);
        Optional<ScientificField> deleted = scientificFieldRepository.findById(scientificField.getId());

        // Assert
        assertNotNull(scientificField);
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Arrange
        ScientificField scientificField = scientificFieldRepository.save(new ScientificField(3L, "field_test"));

        // Act
        Optional<ScientificField> found = scientificFieldRepository.findById(scientificField.getId());

        // Assert
        assertNotNull(scientificField);
        assertTrue(found.isPresent());
        assertEquals(scientificField.getId(), found.get().getId());
    }
}
