package com.example.universitysimulation.service.impl;

import com.example.universitysimulation.dto.AcademicTitleHistoryDTO;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.AcademicTitleHistory;
import com.example.universitysimulation.repository.AcademicTitleHistoryRepository;
import com.example.universitysimulation.service.AcademicTitleHistoryService;
import com.example.universitysimulation.utils.ObjectsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AcademicTitleHistoryServiceImpl implements AcademicTitleHistoryService {
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    @Override
    public List<AcademicTitleHistoryDTO> getAll() {
        return academicTitleHistoryRepository
                .findAll()
                .stream()
                .map(ObjectsMapper::convertAcademicTitleHistoryToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AcademicTitleHistoryDTO getById(Long id) {
        Optional<AcademicTitleHistory> optionalAcademicTitleHistory = academicTitleHistoryRepository.findById(id);
        if(optionalAcademicTitleHistory.isEmpty())
            throw new NotFoundInDataBaseException("Academic title history with id " + id + " not found");
        return ObjectsMapper.convertAcademicTitleHistoryToDTO(optionalAcademicTitleHistory.get());
    }

    @Override
    public List<AcademicTitleHistoryDTO> getByMemberId(Long id) {
        return academicTitleHistoryRepository
                .findAllByMemberId(id)
                .stream()
                .map(ObjectsMapper::convertAcademicTitleHistoryToDTO)
                .collect(Collectors.toList());
    }
}
