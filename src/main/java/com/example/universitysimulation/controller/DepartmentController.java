package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.*;
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

    @GetMapping("/{id}/managementHistory")
    public ResponseEntity<List<DepartmentManagementHistoryDTO>> getDepartmentManagementHistory(@PathVariable("id") Long id) {
        List<DepartmentManagementHistoryDTO> history = departmentService.getDepartmentManagementHistory(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<List<MemberDTO>> getAllMembers(@PathVariable("id") Long id) {
        List<MemberDTO> members = departmentService.getAllMembers(id);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<SubjectDTO>> getAllSubjects(@PathVariable("id") Long id) {
        List<SubjectDTO> subjects = departmentService.getAllSubjects(id);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
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

    @PatchMapping(path="/{departmentId}/headOfTheDepartment/{memberId}")
    public ResponseEntity<DepartmentDTO> updateHeadOfTheDepartment(@PathVariable("departmentId") Long departmentId, @PathVariable("memberId") Long memberId){
        DepartmentDTO department = departmentService.updateHeadOfDepartment(departmentId, memberId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    @PatchMapping(path="/{departmentId}/secretary/{memberId}")
    public ResponseEntity<DepartmentDTO> updateSecretary(@PathVariable("departmentId") Long departmentId, @PathVariable("memberId") Long memberId){
        DepartmentDTO department = departmentService.updateSecretary(departmentId, memberId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
}
