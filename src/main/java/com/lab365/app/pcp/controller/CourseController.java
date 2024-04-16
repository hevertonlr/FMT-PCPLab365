package com.lab365.app.pcp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "cursos", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {
}
