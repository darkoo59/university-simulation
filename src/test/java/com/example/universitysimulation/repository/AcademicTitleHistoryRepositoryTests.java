package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitleHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.universitysimulation.HelperTests.createAcademicTitleHistory;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AcademicTitleHistoryRepositoryTests {

    @Autowired
    private AcademicTitleHistoryRepository academicTitleHistoryRepository;

    @Test
    public void saveAcademicTitleHistoryTest() {
        // Arrange
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();

        // Act
        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);

        // Assert
        assertNotNull(saved);
    }

    @Test
    public void deleteAcademicTitleHistoryTest() {
        // Arrange
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);

        // Act
        academicTitleHistoryRepository.delete(saved);

        // Assert
        Optional<AcademicTitleHistory> deleted = academicTitleHistoryRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Arrange
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);

        // Act
        Optional<AcademicTitleHistory> found = academicTitleHistoryRepository.findById(saved.getId());

        // Assert
        assertTrue(found.isPresent());
        assertEquals(saved.getId(), found.get().getId());
    }

    @Test
    public void findByCurrentAcademicTitleTest() {
        // Arrange
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        academicTitleHistory.setEndDate(null);
        academicTitleHistoryRepository.save(academicTitleHistory);

        // Act
        List<AcademicTitleHistory> founded = academicTitleHistoryRepository.findByCurrentAcademicTitle(1l, 1l);
        List<AcademicTitleHistory> notFunded = academicTitleHistoryRepository.findByCurrentAcademicTitle(2l, 2l);

        // Assert
        assertFalse(founded.isEmpty());
        assertTrue(notFunded.isEmpty());
    }

    @Test
    public void findByMemberIdTest() {
        // Arrange
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        academicTitleHistoryRepository.save(academicTitleHistory);

        // Act
        List<AcademicTitleHistory> found = academicTitleHistoryRepository.findAllByMemberId(1l);

        // Assert
        assertFalse(found.isEmpty());
    }
}
