package com.example.universitysimulation.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "academic_title_history")
@Getter
@Setter
@NoArgsConstructor
public class AcademicTitleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    private Member member;

    @ManyToOne
    private AcademicTitle academicTitle;

    @ManyToOne
    private ScientificField scientificField;

    public AcademicTitleHistory(LocalDate startDate, Member member, AcademicTitle academicTitle, ScientificField scientificField) {
        this.startDate = startDate;
        this.member = member;
        this.academicTitle = academicTitle;
        this.scientificField = scientificField;
    }

    public AcademicTitleHistory(LocalDate startDate, LocalDate endDate, Member member, AcademicTitle academicTitle, ScientificField scientificField) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.member = member;
        this.academicTitle = academicTitle;
        this.scientificField = scientificField;
    }
}
