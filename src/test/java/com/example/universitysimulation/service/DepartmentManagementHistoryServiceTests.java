package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.AcademicTitleHistoryDTO;
import com.example.universitysimulation.dto.DepartmentManagementHistoryDTO;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.AcademicTitleHistory;
import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.model.DepartmentManagementHistory;
import com.example.universitysimulation.model.Member;
import com.example.universitysimulation.repository.AcademicTitleHistoryRepository;
import com.example.universitysimulation.repository.DepartmentManagementHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.example.universitysimulation.HelperTests.createDepartmentManagementHistory;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DepartmentManagementHistoryServiceTests {
    @Autowired
    private DepartmentManagementHistoryRepository departmentManagementHistoryRepository;
    @Autowired
    private DepartmentManagementHistoryService departmentManagementHistoryService;

    @Test
    public void getByDepartmentIdSuccessTest() {
        // Arrange
        DepartmentManagementHistory savedHistory = departmentManagementHistoryRepository.save(createDepartmentManagementHistory(1l, 1l, 2l));

        // Act
        List<DepartmentManagementHistoryDTO> founded = departmentManagementHistoryService.getByDepartmentId(savedHistory.getDepartment().getId());

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByDepartmentIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentManagementHistoryService.getByDepartmentId(Long.MAX_VALUE);
        });
    }

    @Test
    public void getByMemberIdSuccessTest() {
        // Arrange
        DepartmentManagementHistory savedHistory = departmentManagementHistoryRepository.save(createDepartmentManagementHistory(1l, 1l, 2l));

        // Act
        List<DepartmentManagementHistoryDTO> founded = departmentManagementHistoryService.getByMemberId(savedHistory.getSecretary().getId());

        // Assert
        assertNotNull(founded);
        assertTrue(founded.size() > 0);
    }

    @Test
    public void getByMemberIdFailureTest() throws NotFoundInDataBaseException {
        assertThrows(NotFoundInDataBaseException.class, () -> {
            departmentManagementHistoryService.getByMemberId(Long.MAX_VALUE);
        });
    }
}
