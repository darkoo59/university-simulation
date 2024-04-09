package com.example.universitysimulation.controller;

import com.example.universitysimulation.dto.DepartmentManagementHistoryDTO;
import com.example.universitysimulation.exception.NotFoundInDataBaseException;
import com.example.universitysimulation.service.DepartmentManagementHistoryService;
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
@RequestMapping("/departmentManagementHistory")
public class DepartmentManagementHistoryController {
    private final DepartmentManagementHistoryService departmentManagementHistoryService;

    @GetMapping("/department/{id}")
    public ResponseEntity<List<DepartmentManagementHistoryDTO>> getByDepartmentId(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        List<DepartmentManagementHistoryDTO> histories = departmentManagementHistoryService.getByDepartmentId(id);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<List<DepartmentManagementHistoryDTO>> getByMemberId(@PathVariable("id") Long id) throws NotFoundInDataBaseException {
        List<DepartmentManagementHistoryDTO> histories = departmentManagementHistoryService.getByMemberId(id);
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }
}
