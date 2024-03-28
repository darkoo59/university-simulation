package com.example.universitysimulation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is mandatory field")
    @Size(min = 3, max = 30, message = "Number of characters in name must be between 3 and 30")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Short name is mandatory field")
    @Size(min = 1, max = 10, message = "Number of characters in short name must be between 1 and 10")
    @Column(name = "short_name")
    private String shortName;

    @ManyToOne
    private Member headOfDepartment;

    @ManyToOne
    private Member secretary;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "department")
    @JsonIgnore
    private List<Subject> subjects;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "department")
    private List<Member> members;

    public Department(Long id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
    }

    public Department(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }
}