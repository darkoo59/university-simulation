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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DepartmentManagementHistoryRepositoryTests {

    @Autowired
    private DepartmentManagementHistoryRepository departmentManagementHistoryRepository;

    @Test
    public void saveDepartmentManagementHistoryTest() {
        // Create a DepartmentManagementHistory entity
        // Save the DepartmentManagementHistory
        DepartmentManagementHistory savedHistory = departmentManagementHistoryRepository.save(createDepartmentManagementHistory(1l, 1l, 2l));
        // Verify that the saved DepartmentManagementHistory is not null
        assertNotNull(savedHistory.getId());
        assertNotNull(departmentManagementHistoryRepository.findById(savedHistory.getId()));
    }

    @Test
    public void deleteDepartmentManagementHistoryTest() {
        // Create and save an academic title
        DepartmentManagementHistory savedHistory = departmentManagementHistoryRepository.save(createDepartmentManagementHistory(1l, 1l, 2l));
        assertNotNull(savedHistory);

        // Delete the saved academic title
        departmentManagementHistoryRepository.delete(savedHistory);

        // Verify that the academic title is deleted
        Optional<DepartmentManagementHistory> deleted = departmentManagementHistoryRepository.findById(savedHistory.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByIdTest() {
        // Create and save an academic title with a specific ID
        DepartmentManagementHistory savedHistory = departmentManagementHistoryRepository.save(createDepartmentManagementHistory(1l, 1l, 2l));
        assertNotNull(savedHistory);

        // Find the saved academic title by ID
        Optional<DepartmentManagementHistory> found = departmentManagementHistoryRepository.findById(savedHistory.getId());

        // Verify that the academic title is found and matches the saved one
        assertTrue(found.isPresent());
        assertEquals(savedHistory.getId(), found.get().getId());
    }

    @Test
    public void findByDepartmentIdTest() {
        // Create DepartmentManagementHistory entries associated with the department
        DepartmentManagementHistory history1 = createDepartmentManagementHistory(1l, 1l, 2l);
        departmentManagementHistoryRepository.save(history1);

        DepartmentManagementHistory history2 = createDepartmentManagementHistory(1l, 3l, 4l);
        departmentManagementHistoryRepository.save(history2);

        // Find DepartmentManagementHistory entries by department ID
        List<DepartmentManagementHistory> histories = departmentManagementHistoryRepository.findByDepartmentId(1l);

         assertNotEquals(histories.size(), 0);
        for (DepartmentManagementHistory history : histories) {
            assertEquals(1l, history.getDepartment().getId());
        }
    }
//
    @Test
    public void findByMemberIdTest() {
        // Create DepartmentManagementHistory entries associated with the department
        DepartmentManagementHistory history1 = createDepartmentManagementHistory(1l, 1l, 2l);
        departmentManagementHistoryRepository.save(history1);

        DepartmentManagementHistory history2 = createDepartmentManagementHistory(1l, 3l, 4l);
        departmentManagementHistoryRepository.save(history2);

        // Find DepartmentManagementHistory entries by department ID
        List<DepartmentManagementHistory> histories = departmentManagementHistoryRepository.findByMemberId(1l);

        assertNotEquals(histories.size(), 0);
        for (DepartmentManagementHistory history : histories) {
            assertTrue(history.getSecretary().getId() == 1l || history.getHeadOfDepartment().getId() == 1l);
        }
    }

    // Helper method to create a DepartmentManagementHistory with specified department and members
    private DepartmentManagementHistory createDepartmentManagementHistory(Long departmentId, Long secretaryId, Long headId) {
        DepartmentManagementHistory history = new DepartmentManagementHistory();
        Department department = new Department();
        department.setId(departmentId);
        Member secretary = new Member();
        secretary.setId(secretaryId);
        Member head = new Member();
        head.setId(headId);
        history.setDepartment(department);
        history.setSecretary(secretary);
        history.setHeadOfDepartment(head);
        history.setStartDate(LocalDate.now());
        history.setEndDate(LocalDate.now().plusDays(1));
        return history;
    }
}
