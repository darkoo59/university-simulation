package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.AcademicTitleDTO;
import com.example.universitysimulation.dto.DepartmentDTO;
import com.example.universitysimulation.dto.request.AcademicTitleRequest;
import com.example.universitysimulation.dto.request.DepartmentRequest;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.model.Department;
import com.example.universitysimulation.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        List<DepartmentDTO> departments = departmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getById(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        DepartmentDTO department = departmentService.getById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<DepartmentDTO> create(@Valid @RequestBody DepartmentRequest departmentRequest) {
        DepartmentDTO department = departmentService.create(departmentRequest);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        departmentService.delete(id);
        return new ResponseEntity<>("Department removed!", HttpStatus.OK);
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<DepartmentDTO> updateByPut(@Valid @RequestBody DepartmentRequest departmentRequest,@PathVariable("id") Long id){
        DepartmentDTO department = departmentService.update(departmentRequest, id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PatchMapping(path="/{id}")
    public ResponseEntity<DepartmentDTO> updateByPatch(@Valid @RequestBody DepartmentRequest departmentRequest,@PathVariable("id") Long id){
        DepartmentDTO department = departmentService.update(departmentRequest, id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
