package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.DepartmentManagementHistoryDTO;
import com.example.universitysimulation.model.DepartmentManagementHistory;
import com.example.universitysimulation.repository.DepartmentManagementHistoryRepository;
import com.example.universitysimulation.service.DepartmentManagementHistoryService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentManagementHistoryServiceImpl implements DepartmentManagementHistoryService {
    private final DepartmentManagementHistoryRepository departmentManagementHistoryRepository;

    @Override
    public void save(DepartmentManagementHistory dmh) {
        departmentManagementHistoryRepository.save(dmh);
    }

    @Override
    public List<DepartmentManagementHistoryDTO> getByDepartmentId(Long id) {
        return departmentManagementHistoryRepository
                .findByDepartmentId(id)
                .stream()
                .map(ObjectsMapper::convertDepartmentManagementHistoryToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentManagementHistoryDTO> getByMemberId(Long id) {
        return departmentManagementHistoryRepository
                .findByMemberId(id)
                .stream()
                .map(ObjectsMapper::convertDepartmentManagementHistoryToDTO)
                .collect(Collectors.toList());
    }
}
