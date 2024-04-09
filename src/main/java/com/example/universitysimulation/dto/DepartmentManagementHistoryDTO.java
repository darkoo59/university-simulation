package com.example.universitysimulation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentManagementHistoryDTO {
    private Long id;
    private DepartmentDTO department;
    private MemberDTO headOfDepartment;
    private MemberDTO secretary;
    private LocalDate startDate;
    private LocalDate endDate;
}
