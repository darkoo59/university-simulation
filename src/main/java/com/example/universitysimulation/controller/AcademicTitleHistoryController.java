package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.AcademicTitleHistoryDTO;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.service.AcademicTitleHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academicTitlesHistory")
public class AcademicTitleHistoryController {
    private final AcademicTitleHistoryService academicTitleHistoryService;

    @GetMapping
    public ResponseEntity<List<AcademicTitleHistoryDTO>> getAll() {
        List<AcademicTitleHistoryDTO> histories = academicTitleHistoryService.getAll();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicTitleHistoryDTO> getById(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        AcademicTitleHistoryDTO history = academicTitleHistoryService.getById(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<List<AcademicTitleHistoryDTO>> getByMemberId(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        List<AcademicTitleHistoryDTO> histories = academicTitleHistoryService.getByMemberId(id);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
}
