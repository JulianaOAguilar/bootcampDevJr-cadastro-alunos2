package com.abutua.product_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    @GetMapping("courses")
    public String getCourses() {
        return "Lista de cursos";
    }
}
