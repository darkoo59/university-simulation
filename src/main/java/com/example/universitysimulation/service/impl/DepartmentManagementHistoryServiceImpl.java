package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.DepartmentManagementHistoryDTO;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.DepartmentManagementHistory;
import com.example.universitysimulation.repository.DepartmentManagementHistoryRepository;
import com.example.universitysimulation.repository.DepartmentRepository;
import com.example.universitysimulation.repository.MemberRepository;
import com.example.universitysimulation.service.DepartmentManagementHistoryService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentManagementHistoryServiceImpl implements DepartmentManagementHistoryService {
    private final DepartmentManagementHistoryRepository departmentManagementHistoryRepository;
    private final DepartmentRepository departmentRepository;
    private final MemberRepository memberRepository;

    @Override
    public void save(DepartmentManagementHistory dmh) {
        departmentManagementHistoryRepository.save(dmh);
    }

    @Override
    public List<DepartmentManagementHistoryDTO> getByDepartmentId(Long id) {
        if (departmentRepository.findById(id).isEmpty())
            throw new NotFoundInDataBaseException("Department with id " + id + " not found");
        return departmentManagementHistoryRepository
                .findByDepartmentId(id)
                .stream()
                .map(ObjectsMapper::convertDepartmentManagementHistoryToDTO)
                .toList();
    }

    @Override
    public List<DepartmentManagementHistoryDTO> getByMemberId(Long id) {
        if (memberRepository.findById(id).isEmpty())
            throw new NotFoundInDataBaseException("Member with id " + id + " not found");
        return departmentManagementHistoryRepository
                .findByMemberId(id)
                .stream()
                .map(ObjectsMapper::convertDepartmentManagementHistoryToDTO)
                .toList();
    }
}
