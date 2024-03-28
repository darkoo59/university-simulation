package com.example.universitysimulation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 2,max = 10, message = "Number of characters [2-10]")
    private String name;


}
