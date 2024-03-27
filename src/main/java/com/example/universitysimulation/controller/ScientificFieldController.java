package com.example.universitysimulation.controller;

import com.example.universitysimulation.service.ScientificFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scientific-field")
public class ScientificFieldController {
    private final ScientificFieldService scientificFieldService;
}
