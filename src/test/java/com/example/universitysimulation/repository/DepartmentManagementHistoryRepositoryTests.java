package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitle;
import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.model.DepartmentManagementHistory;
import com.example.universitysimulation.model.Member;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.universitysimulation.HelperTests.createDepartmentManagementHistory;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DepartmentManagementHistoryRepositoryTests {

    @Autowired
    private DepartmentManagementHistoryRepository departmentManagementHistoryRepository;

    @Test
    public void saveDepartmentManagementHistoryTest() {
        // Arrange
        DepartmentManagementHistory departmentManagementHistory = createDepartmentManagementHistory(1l, 1l, 2l);

        // Act
        DepartmentManagementHistory savedHistory = departmentManagementHistoryRepository.save(departmentManagementHistory);

        // Assert
        assertNotNull(savedHistory.getId());
        assertNotNull(departmentManagementHistoryRepository.findById(savedHistory.getId()));
    }

    @Test
    public void deleteDepartmentManagementHistoryTest() {
        // Arrange
        DepartmentManagementHistory savedHistory = departmentManagementHistoryRepository.save(createDepartmentManagementHistory(1l, 1l, 2l));

        // Act
        departmentManagementHistoryRepository.delete(savedHistory);
        Optional<DepartmentManagementHistory> deleted = departmentManagementHistoryRepository.findById(savedHistory.getId());

        // Assert
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Arrange
        DepartmentManagementHistory savedHistory = departmentManagementHistoryRepository.save(createDepartmentManagementHistory(1l, 1l, 2l));

        // Act
        Optional<DepartmentManagementHistory> found = departmentManagementHistoryRepository.findById(savedHistory.getId());

        // Assert
        assertNotNull(savedHistory);
        assertTrue(found.isPresent());
        assertEquals(savedHistory.getId(), found.get().getId());
    }

    @Test
    public void findByDepartmentIdTest() {
        // Arrange
        DepartmentManagementHistory history1 = createDepartmentManagementHistory(1l, 1l, 2l);
        departmentManagementHistoryRepository.save(history1);
        DepartmentManagementHistory history2 = createDepartmentManagementHistory(1l, 3l, 4l);
        departmentManagementHistoryRepository.save(history2);

        // Act
        List<DepartmentManagementHistory> histories = departmentManagementHistoryRepository.findByDepartmentId(1l);

        // Assert
        assertNotEquals(histories.size(), 0);
        for (DepartmentManagementHistory history : histories) {
            assertEquals(1l, history.getDepartment().getId());
        }
    }
//
    @Test
    public void findByMemberIdTest() {
        // Arrange
        DepartmentManagementHistory history1 = createDepartmentManagementHistory(1l, 1l, 2l);
        departmentManagementHistoryRepository.save(history1);
        DepartmentManagementHistory history2 = createDepartmentManagementHistory(1l, 3l, 4l);
        departmentManagementHistoryRepository.save(history2);

        // Act
        List<DepartmentManagementHistory> histories = departmentManagementHistoryRepository.findByMemberId(1l);

        // Assert
        assertNotEquals(histories.size(), 0);
        for (DepartmentManagementHistory history : histories) {
            assertTrue(history.getSecretary().getId() == 1l || history.getHeadOfDepartment().getId() == 1l);
        }
    }
}
