package com.lab365.app.pcp.controller;

import com.lab365.app.pcp.service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {
    private final SubjectService service;
}
