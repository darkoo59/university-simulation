package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.EducationTitleDTO;
import com.example.universitysimulation.dto.ScientificFieldDTO;
import com.example.universitysimulation.dto.request.EducationTitleRequest;
import com.example.universitysimulation.dto.request.ScientificFieldRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.service.ScientificFieldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scientificFields")
public class ScientificFieldController {
    private final ScientificFieldService scientificFieldService;

    @GetMapping
    public ResponseEntity<List<ScientificFieldDTO>> getAll() {
        List<ScientificFieldDTO> fields = scientificFieldService.getAll();
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScientificFieldDTO> getById(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        ScientificFieldDTO field = scientificFieldService.getById(id);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ScientificFieldDTO> create(@Valid @RequestBody ScientificFieldRequest scientificFieldRequest) {
        ScientificFieldDTO field = scientificFieldService.create(scientificFieldRequest);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        scientificFieldService.delete(id);
        return new ResponseEntity<>("Scientific field removed!", HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<ScientificFieldDTO> updateByPut(@Valid @RequestBody ScientificFieldRequest scientificFieldRequest,@PathVariable("id") Long id){
        ScientificFieldDTO field = scientificFieldService.update(scientificFieldRequest, id);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @PatchMapping(path="/{id}")
    public ResponseEntity<ScientificFieldDTO> updateByPatch(@Valid @RequestBody ScientificFieldRequest scientificFieldRequest,@PathVariable("id") Long id){
        ScientificFieldDTO field = scientificFieldService.update(scientificFieldRequest, id);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }
}
