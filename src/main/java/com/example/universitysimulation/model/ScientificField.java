package com.example.universitysimulation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scientific-field")
@Getter
@Setter
@NoArgsConstructor
public class ScientificField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Field is mandatory field")
    @Size(min = 2, max = 20, message = "Number of characters in field must be between 2 and 20")
    @Column(name = "field")
    private String field;
}
