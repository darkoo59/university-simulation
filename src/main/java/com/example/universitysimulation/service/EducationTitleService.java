package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.EducationTitleDTO;
import com.example.universitysimulation.dto.request.EducationTitleRequest;

import java.util.List;

public interface EducationTitleService {
    List<EducationTitleDTO> getAll();

    EducationTitleDTO getById(Long id);

    EducationTitleDTO create(EducationTitleRequest educationTitleRequest);

    void delete(Long id);

    EducationTitleDTO update(EducationTitleRequest educationTitleRequest, Long id);
}
