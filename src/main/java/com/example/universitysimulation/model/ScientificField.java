package com.example.universitysimulation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scientific_field")
public class ScientificField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Field is mandatory field")
    @Size(min = 2, max = 30, message = "Number of characters in field must be between 2 and 30")
    @Column(name = "field")
    private String field;

    public ScientificField(String field) {
        this.field = field;
    }
}
