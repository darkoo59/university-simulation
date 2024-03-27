package com.example.universitysimulation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subject")
@Getter
@Setter
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is mandatory field")
    @Size(min = 3, max = 30, message = "Number of characters in name must be between 3 and 30")
    @Column(name = "name")
    private String name;

    @Column(name = "espb")
    private Integer espb;

    @ManyToOne
    @JsonBackReference
    private Department department;
}
