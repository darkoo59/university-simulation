package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
