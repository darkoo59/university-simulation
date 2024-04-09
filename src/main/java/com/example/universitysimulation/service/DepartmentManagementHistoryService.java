package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.DepartmentManagementHistoryDTO;
import com.example.universitysimulation.model.DepartmentManagementHistory;

import java.util.List;

public interface DepartmentManagementHistoryService {
    void save (DepartmentManagementHistory dmh);

    List<DepartmentManagementHistoryDTO> getByDepartmentId(Long id);

    List<DepartmentManagementHistoryDTO> getByMemberId(Long id);
}
