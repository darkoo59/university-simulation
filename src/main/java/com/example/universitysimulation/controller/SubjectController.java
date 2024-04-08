package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.ScientificFieldDTO;
import com.example.universitysimulation.dto.SubjectDTO;
import com.example.universitysimulation.dto.request.ScientificFieldRequest;
import com.example.universitysimulation.dto.request.SubjectRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.Subject;
import com.example.universitysimulation.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAll() {
        List<SubjectDTO> subjects = subjectService.getAll();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getById(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        SubjectDTO subject = subjectService.getById(id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SubjectDTO> create(@Valid @RequestBody SubjectRequest subjectRequest) {
        SubjectDTO subject = subjectService.create(subjectRequest);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        subjectService.delete(id);
        return new ResponseEntity<>("Subject removed!", HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<SubjectDTO> updateByPut(@Valid @RequestBody SubjectRequest subjectRequest, @PathVariable("id") Long id){
        SubjectDTO subject = subjectService.update(subjectRequest, id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PatchMapping(path="/{id}")
    public ResponseEntity<SubjectDTO> updateByPatch(@Valid @RequestBody SubjectRequest subjectRequest,@PathVariable("id") Long id){
        SubjectDTO subject = subjectService.update(subjectRequest, id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }
}
