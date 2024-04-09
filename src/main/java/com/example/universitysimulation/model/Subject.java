package com.example.universitysimulation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is mandatory field")
    @Size(min = 3, max = 50, message = "Number of characters in name must be between 3 and 50")
    @Column(name = "name")
    private String name;

    @Column(name = "espb")
    private Integer espb;

    @ManyToOne
    @JsonManagedReference
    private Department department;
}
