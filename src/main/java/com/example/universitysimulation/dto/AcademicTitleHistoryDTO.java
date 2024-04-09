package com.example.universitysimulation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicTitleHistoryDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private MemberDTO memberDTO;
    private AcademicTitleDTO academicTitleDTO;
    private ScientificFieldDTO scientificFieldDTO;
}
