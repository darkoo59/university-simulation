package com.example.universitysimulation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "education_title")
@Getter
@Setter
@NoArgsConstructor
public class EducationTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title is mandatory field")
    @Size(min = 2, max = 20, message = "Number of characters in title must be between 2 and 20")
    @Column(name = "title")
    private String title;
}