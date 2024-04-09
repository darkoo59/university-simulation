package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.DepartmentManagementHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentManagementHistoryRepository extends JpaRepository<DepartmentManagementHistory, Long> {

    @Query("SELECT dmh FROM DepartmentManagementHistory dmh WHERE dmh.department.id=:id")
    List<DepartmentManagementHistory> findByDepartmentId(@Param("id") Long id);

    @Query("SELECT dmh FROM DepartmentManagementHistory dmh WHERE dmh.headOfDepartment.id=:id OR dmh.secretary.id=:id")
    List<DepartmentManagementHistory> findByMemberId(@Param("id") Long id);
}
