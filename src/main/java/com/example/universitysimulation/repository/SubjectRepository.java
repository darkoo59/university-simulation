package com.example.universitysimulation.repository;

import com.example.universitysimulation.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
