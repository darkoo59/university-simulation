package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.AcademicTitleHistoryDTO;
import com.example.universitysimulation.dto.ScientificFieldDTO;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.model.AcademicTitleHistory;
import com.example.universitysimulation.model.Member;
import com.example.universitysimulation.model.ScientificField;
import com.example.universitysimulation.repository.AcademicTitleHistoryRepository;
import com.example.universitysimulation.repository.ScientificFieldRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.example.universitysimulation.HelperTests.createAcademicTitleHistory;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class AcademicTitleHistoryServiceTests {
    @Autowired
    private AcademicTitleHistoryRepository academicTitleHistoryRepository;
    @Autowired
    private AcademicTitleHistoryService academicTitleHistoryService;

    @Test
    public void getAllTest() {
        // Arrange
        academicTitleHistoryRepository.save(createAcademicTitleHistory());

        // Act
        List<AcademicTitleHistoryDTO> founded = academicTitleHistoryService.getAll();

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByIdSuccessTest() {
        // Arrange
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);

        // Act
        AcademicTitleHistoryDTO founded = academicTitleHistoryService.getById(saved.getId());

        // Assert
        assertNotNull(founded);
        assertEquals(founded.getId(), saved.getId());
    }

    @Test
    public void getByIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            academicTitleHistoryService.getById(Long.MAX_VALUE);
        });
    }

    @Test
    public void getByMemberIdSuccessTest() {
        // Arrange
        AcademicTitleHistory academicTitleHistory = createAcademicTitleHistory();
        AcademicTitleHistory saved = academicTitleHistoryRepository.save(academicTitleHistory);

        // Act
        List<AcademicTitleHistoryDTO> founded = academicTitleHistoryService.getByMemberId(saved.getMember().getId());

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByMemberIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            academicTitleHistoryService.getById(Long.MAX_VALUE);
        });
    }
}
