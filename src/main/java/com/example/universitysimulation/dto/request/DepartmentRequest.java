package com.example.universitysimulation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentRequest {
    @NotNull(message = "Name must not be null")
    @NotEmpty(message = "Name must not be empty")
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, max = 50, message = "Number of characters in department name must be between 3 and 50")
    private String name;
    @NotNull(message = "Short name must not be null")
    @NotEmpty(message = "Short name must not be empty")
    @NotBlank(message = "Short name must not be blank")
    @Size(min = 1, max = 10, message = "Number of characters in department short name must be between 1 and 10")
    private String shortName;
}
