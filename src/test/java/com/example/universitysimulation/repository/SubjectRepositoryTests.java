package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.model.ScientificField;
import com.example.universitysimulation.model.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.universitysimulation.HelperTests.createNewSubject;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class SubjectRepositoryTests {
    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    @Transactional
    public void saveSubjectTest() {
        // Arrange
        Subject subject = createNewSubject();

        // Act
        Subject saved = subjectRepository.save(subject);

        // Assert
        assertNotNull(subject);
        assertNotNull(subject.getId());
    }

    @Test
    @Transactional
    public void deleteSubjectTest() {
        // Arrange
        Subject subject = subjectRepository.save(createNewSubject());

        // Act
        subjectRepository.delete(subject);
        Optional<Subject> deleted = subjectRepository.findById(subject.getId());

        // Assert
        assertNotNull(subject);
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Arrange
        Subject subject = subjectRepository.save(createNewSubject());

        // Act
        Optional<Subject> found = subjectRepository.findById(subject.getId());

        // Assert
        assertNotNull(subject);
        assertTrue(found.isPresent());
        assertEquals(subject.getId(), found.get().getId());
    }
}
