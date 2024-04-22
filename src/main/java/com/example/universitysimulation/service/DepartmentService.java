package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.DepartmentDTO;
import com.example.universitysimulation.dto.DepartmentManagementHistoryDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.DepartmentRequest;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAll();

    DepartmentDTO getById(Long id);
    DepartmentDTO create(DepartmentRequest departmentRequest);
    void delete(Long id);
    DepartmentDTO update(DepartmentRequest departmentRequest, Long id);

    List<MemberDTO> getAllMembers(Long id);
    List<SubjectDTO> getAllSubjects(Long id);

    DepartmentDTO updateHeadOfDepartment(Long departmentId, Long memberId);

    DepartmentDTO updateSecretary(Long departmentId, Long memberId);

    List<DepartmentManagementHistoryDTO> getDepartmentManagementHistory(Long id);
}
