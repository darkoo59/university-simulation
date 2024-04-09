package com.example.universitysimulation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private AcademicTitleDTO academicTitle;
    private ScientificFieldDTO scientificField;
    private EducationTitleDTO educationTitle;
    private String departmentName;
}
