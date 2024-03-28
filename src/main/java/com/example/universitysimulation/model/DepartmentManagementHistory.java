package com.example.universitysimulation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "department_management_history")
@Getter
@Setter
@NoArgsConstructor
public class DepartmentManagementHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Department department;

    @ManyToOne
    private Member secretary;

    @ManyToOne
    private Member headOfDepartment;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

}
