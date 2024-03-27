package com.example.universitysimulation.controller;

import com.example.universitysimulation.service.AcademicTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/academic-title")
public class AcademicTitleController {
    private final AcademicTitleService academicTitleService;
}
