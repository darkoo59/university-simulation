package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.AcademicTitleHistory;
import com.example.universitysimulation.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Department d WHERE d.headOfDepartment.id<>:memberId " +
            "AND d.secretary.id<>:memberId AND d.id<>:departmentId")
    boolean isDepartmentWithMemberApartFromDepartment(@Param("memberId") Long memberId, @Param("departmentId") Long departmentId);
}
