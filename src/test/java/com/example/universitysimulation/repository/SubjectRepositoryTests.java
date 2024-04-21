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
        // Create and save an academic title
        Subject subject = subjectRepository.save(createNewSubject());

        // Verify that the saved academic title is not null
        assertNotNull(subject);
        assertNotNull(subject.getId()); // Ensure the ID is generated
    }

    @Test
    @Transactional
    public void deleteSubjectTest() {
        // Create and save an academic title
        Subject subject = subjectRepository.save(createNewSubject());
        assertNotNull(subject);

        // Delete the saved academic title
        subjectRepository.delete(subject);

        // Verify that the academic title is deleted
        Optional<Subject> deleted = subjectRepository.findById(subject.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Create and save an academic title with a specific ID
        Subject subject = subjectRepository.save(createNewSubject());
        assertNotNull(subject);

        // Find the saved academic title by ID
        Optional<Subject> found = subjectRepository.findById(subject.getId());

        // Verify that the academic title is found and matches the saved one
        assertTrue(found.isPresent());
        assertEquals(subject.getId(), found.get().getId());
    }

    private Subject createNewSubject() {
        Subject subject = new Subject();
        Department department = new Department();
        subject.setId(1l);
        subject.setDepartment(department);
        return subject;
    }
}
