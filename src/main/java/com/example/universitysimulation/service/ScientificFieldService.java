package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.EducationTitleDTO;
import com.example.universitysimulation.dto.ScientificFieldDTO;
import com.example.universitysimulation.dto.request.EducationTitleRequest;
import com.example.universitysimulation.dto.request.ScientificFieldRequest;

import java.util.List;

public interface ScientificFieldService {
    List<ScientificFieldDTO> getAll();

    ScientificFieldDTO getById(Long id);

    ScientificFieldDTO create(ScientificFieldRequest scientificFieldRequest);

    void delete(Long id);

    ScientificFieldDTO update(ScientificFieldRequest scientificFieldRequest, Long id);
}
