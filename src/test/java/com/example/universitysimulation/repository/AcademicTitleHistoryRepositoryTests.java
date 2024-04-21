package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.model.AcademicTitleHistory;
import com.example.universitysimulation.model.Member;
import com.example.universitysimulation.model.ScientificField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class AcademicTitleHistoryRepositoryTests {

    @Autowired
    private AcademicTitleHistoryRepository academicTitleHistoryRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void saveAcademicTitleHistoryTest() {
        // Create an academic title history
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();

        // Save the academic title history
        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);

        // Verify that the saved academic title history is not null
        assertNotNull(saved);
    }

    @Test
    public void deleteAcademicTitleHistoryTest() {
        // Create and save an academic title history
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);

        // Delete the saved academic title history
        academicTitleHistoryRepository.delete(saved);

        // Verify that the academic title history is deleted
        Optional<AcademicTitleHistory> deleted = academicTitleHistoryRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Create and save an academic title history
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);

        // Find the saved academic title history by ID
        Optional<AcademicTitleHistory> found = academicTitleHistoryRepository.findById(saved.getId());

        // Verify that the academic title history is found and matches the saved one
        assertTrue(found.isPresent());
        assertEquals(saved.getId(), found.get().getId());
    }

    @Test
    public void findByCurrentAcademicTitleTest() {
        // Create and save an academic title history
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        academicTitleHistory.setEndDate(null);

        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);

        // Find the saved academic title history by current
        List<AcademicTitleHistory> founded = academicTitleHistoryRepository.findByCurrentAcademicTitle(1l, 1l);
        List<AcademicTitleHistory> notFunded = academicTitleHistoryRepository.findByCurrentAcademicTitle(2l, 2l);

        assertFalse(founded.isEmpty());
        assertTrue(notFunded.isEmpty());
    }

    @Test
    public void findByMemberIdTest() {
        // Create and save an academic title history
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);
        List<AcademicTitleHistory> found = academicTitleHistoryRepository.findAllByMemberId(1l);

        assertFalse(found.isEmpty());
    }

    // Helper method to create a sample AcademicTitleHistory object
    private AcademicTitleHistory createAcademicTitleHistory() {
        AcademicTitle academicTitle = new AcademicTitle(1L, "test");
        ScientificField scientificField = new ScientificField(1L, "test");
        Member member = new Member();
        member.setId(1L);

        AcademicTitleHistory academicTitleHistory = new AcademicTitleHistory();
        academicTitleHistory.setAcademicTitle(academicTitle);
        academicTitleHistory.setStartDate(LocalDate.now());
        academicTitleHistory.setEndDate(LocalDate.now().plusDays(1));
        academicTitleHistory.setScientificField(scientificField);
        academicTitleHistory.setMember(member);

        return academicTitleHistory;
    }

//    @AfterEach
//    public void deleteInsertedData() {
//        jdbcTemplate.execute("TRUNCATE TABLE academic_title_history" );
//    }
}
