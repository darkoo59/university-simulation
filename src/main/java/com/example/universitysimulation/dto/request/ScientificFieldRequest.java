package com.example.universitysimulation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ScientificFieldRequest {
    @NotNull(message = "Field must not be null")
    @NotEmpty(message = "Field must not be empty")
    @NotBlank(message = "Field must not be blank")
    @Size(min = 2, max = 30, message = "Number of characters in field must be between 2 and 30")
    private String field;
}
