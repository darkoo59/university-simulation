package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.service.AcademicTitleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/academicTitles")
public class AcademicTitleController {
    private final AcademicTitleService academicTitleService;

    @GetMapping
    public ResponseEntity<List<AcademicTitleDTO>> getAll() {
        List<AcademicTitleDTO> titles = academicTitleService.getAll();
        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicTitleDTO> getById(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        AcademicTitleDTO title = academicTitleService.getById(id);
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<AcademicTitleDTO> create(@Valid @RequestBody AcademicTitleRequest academicTitleRequest) {
        AcademicTitleDTO title = academicTitleService.create(academicTitleRequest);
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        academicTitleService.delete(id);
        return new ResponseEntity<>("Academic title removed!", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AcademicTitleDTO> update(@Valid @RequestBody AcademicTitleRequest academicTitleRequest, @PathVariable("id") Long id) {
        AcademicTitleDTO title = academicTitleService.update(academicTitleRequest, id);
        return new ResponseEntity<>(title, HttpStatus.OK);
    }
}
