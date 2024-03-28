package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;

import java.util.List;

public interface AcademicTitleService {
    List<AcademicTitleDTO> getAll();

    AcademicTitleDTO getById(Long id);

    AcademicTitleDTO create(AcademicTitleRequest academicTitleRequest);

    void delete(Long id);

    AcademicTitleDTO update(AcademicTitleRequest academicTitleRequest, Long id);
}
