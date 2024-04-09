package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.EducationTitleDTO;
import com.example.universitysimulation.dto.request.EducationTitleRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.service.EducationTitleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/educationTitles")
public class EducationTitleController {
    private final EducationTitleService educationTitleService;

    @GetMapping
    public ResponseEntity<List<EducationTitleDTO>> getAll() {
        List<EducationTitleDTO> titles = educationTitleService.getAll();
        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationTitleDTO> getById(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        EducationTitleDTO title = educationTitleService.getById(id);
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EducationTitleDTO> create(@Valid @RequestBody EducationTitleRequest educationTitleRequest) {
        EducationTitleDTO title = educationTitleService.create(educationTitleRequest);
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        educationTitleService.delete(id);
        return new ResponseEntity<>("Education title removed!", HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<EducationTitleDTO> updateByPut(@Valid @RequestBody EducationTitleRequest educationTitleRequest,@PathVariable("id") Long id){
        EducationTitleDTO title = educationTitleService.update(educationTitleRequest, id);
        return new ResponseEntity<>(title, HttpStatus.OK);
    }
}
