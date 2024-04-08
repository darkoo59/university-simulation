package com.example.universitysimulation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberRequest {
    @NotNull(message = "Firstname must not be null")
    @NotEmpty(message = "Firstname must not be empty")
    @NotBlank(message = "Firstname must not be blank")
    @Size(min = 2, max = 30, message = "Number of characters in firstname must be between 2 and 30")
    private String firstname;
    @NotNull(message = "Lastname must not be null")
    @NotEmpty(message = "Lastname must not be empty")
    @NotBlank(message = "Lastname must not be blank")
    @Size(min = 2, max = 30, message = "Number of characters in lastname must be between 2 and 30")
    private String lastname;
    private Long academicTitleId;
    private Long educationTitleId;
    private Long scientificTitleId;
    private Long departmentId;
}
