package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.repository.DepartmentRepository;
import com.example.universitysimulation.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
}
