package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitleHistory;
import com.example.universitysimulation.model.DepartmentManagementHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentManagementHistoryRepository extends JpaRepository<DepartmentManagementHistory, Long> {
}
