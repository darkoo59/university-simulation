package com.example.universitysimulation.service;

import com.example.universitysimulation.dto.AcademicTitleHistoryDTO;

import java.util.List;

public interface AcademicTitleHistoryService {
    List<AcademicTitleHistoryDTO> getAll();

    AcademicTitleHistoryDTO getById(Long id);

    List<AcademicTitleHistoryDTO> getByMemberId(Long id);
}
