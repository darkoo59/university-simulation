package com.example.universitysimulation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Firstname is mandatory field")
    @Size(min = 2, max = 30, message = "Number of characters in firstname must be between 2 and 30")
    @Column(name = "firstname")
    private String firstname;

    @NotEmpty(message = "Lastname is mandatory field")
    @Size(min = 2, max = 30, message = "Number of characters in lastname must be between 2 and 30")
    @Column(name = "lastname")
    private String lastname;

    @ManyToOne
    private AcademicTitle academicTitle;

    @ManyToOne
    private EducationTitle educationTitle;

    @ManyToOne
    private ScientificField scientificField;

}
