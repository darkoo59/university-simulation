package com.example.universitysimulation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentDTO implements Serializable {
    private Long id;
    private String name;
    private String shortName;
    private MemberDTO headOfDepartment;
    private MemberDTO secretary;
}
