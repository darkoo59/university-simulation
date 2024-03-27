package com.example.universitysimulation.controller;

import com.example.universitysimulation.service.EducationTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/education-title")
public class EducationTitleController {
    private final EducationTitleService educationTitleService;
}
