package com.example.universitysimulation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberAcademicTitleHistoryDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private AcademicTitleDTO academicTitle;
}
