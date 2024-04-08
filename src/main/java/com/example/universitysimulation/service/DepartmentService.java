package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.DepartmentDTO;
import com.example.universitysimulation.dto.MemberDTO;
import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.dto.request.DepartmentRequest;
import com.example.universitysimulation.model.Department;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAll();

    DepartmentDTO getById(Long id);
    DepartmentDTO create(DepartmentRequest departmentRequest);
    public void delete(Long id);
    public DepartmentDTO update(DepartmentRequest departmentRequest, Long id);

    List<MemberDTO> getAllMembers(Long id);
    List<SubjectDTO> getAllSubjects(Long id);

    DepartmentDTO updateHeadOfDepartment(Long departmentId, Long memberId);

    DepartmentDTO updateSecretary(Long departmentId, Long memberId);
}
