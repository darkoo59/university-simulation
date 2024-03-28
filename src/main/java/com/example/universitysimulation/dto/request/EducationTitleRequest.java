package com.example.universitysimulation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EducationTitleRequest {
    @NotNull(message = "Title must not be null")
    @NotEmpty(message = "Title must not be empty")
    @NotBlank(message = "Title must not be blank")
    @Size(min = 2, max = 30, message = "Number of characters in title must be between 2 and 30")
    private String title;
}
