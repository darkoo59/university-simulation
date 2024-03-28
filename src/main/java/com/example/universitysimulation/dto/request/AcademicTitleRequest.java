package com.example.universitysimulation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AcademicTitleRequest {
    @NotNull(message = "Title must not be null")
    @NotEmpty(message = "Title must not be empty")
    @NotBlank(message = "Title must not be blank")
    @Size(min = 2, max = 20, message = "Number of characters in title must be between 2 and 20")
    private String title;
}
