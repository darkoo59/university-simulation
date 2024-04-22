package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.ScientificFieldDTO;
import com.example.universitysimulation.dto.request.ScientificFieldRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.ScientificField;
import com.example.universitysimulation.repository.ScientificFieldRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ScientificFieldServiceTests {
    @Autowired
    private ScientificFieldRepository scientificFieldRepository;
    @Autowired
    private ScientificFieldService scientificFieldService;

    @Test
    public void getAllTest() {
        // Arrange
        ScientificField scientificField = new ScientificField("field_test");
        scientificFieldRepository.save(scientificField);

        // Act
        List<ScientificFieldDTO> founded = scientificFieldService.getAll();

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByIdSuccessTest() {
        // Arrange
        ScientificField scientificField = new ScientificField("field_test");
        ScientificField saved = scientificFieldRepository.save(scientificField);

        // Act
        ScientificFieldDTO founded = scientificFieldService.getById(saved.getId());

        // Assert
        assertNotNull(founded);
        assertEquals(founded.getId(), saved.getId());
    }

    @Test
    public void getByIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            scientificFieldService.getById(Long.MAX_VALUE);
        });
    }

    @Test
    public void createTest() {
        // Arrange
        ScientificFieldRequest request = new ScientificFieldRequest();
        request.setField("field_test");

        // Act
        ScientificFieldDTO saved = scientificFieldService.create(request);

        // Assert
        assertNotNull(saved);
        assertNotNull(saved.getId());
    }

    @Test
    public void deleteSuccessTest() {
        // Arrange
        ScientificField scientificField = new ScientificField("field_test");
        ScientificField saved = scientificFieldRepository.save(scientificField);

        // Act
        scientificFieldService.delete(saved.getId());

        // Assert
        assertThrows(NotFoundInDataBaseException.class, () -> {
            scientificFieldService.getById(saved.getId());
        });
    }

    @Test
    public void deleteFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            scientificFieldService.delete(Long.MAX_VALUE);
        });
    }

    @Test
    public void updateSuccessTest() {
        // Arrange
        ScientificField scientificField = new ScientificField("field_test");
        ScientificField saved = scientificFieldRepository.save(scientificField);
        ScientificFieldRequest scientificFieldRequest = new ScientificFieldRequest();
        scientificFieldRequest.setField("updated_test");

        // Act
        ScientificFieldDTO updated = scientificFieldService.update(scientificFieldRequest, saved.getId());

        // Assert
        assertEquals(updated.getField(), scientificFieldRequest.getField());
        assertEquals(saved.getId(), updated.getId());
        assertNotEquals(updated.getField(), "field_test");
    }

    @Test
    public void updateFailureTest() {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            scientificFieldService.update(new ScientificFieldRequest(), Long.MAX_VALUE);
        });
    }
}
