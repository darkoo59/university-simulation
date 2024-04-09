package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.SubjectRequest;

import java.util.List;

public interface SubjectService {
    List<SubjectDTO> getAll();

    SubjectDTO getById(Long id);

    SubjectDTO create(SubjectRequest subjectRequest);

    void delete(Long id);

    SubjectDTO update(SubjectRequest subjectRequest, Long id);

    void deleteDepartment(Long id);

    SubjectDTO updateDepartment(Long subjectId, Long departmentId);
}
