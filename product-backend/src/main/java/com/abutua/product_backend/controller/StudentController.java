package com.abutua.product_backend.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class StudentController {

    @GetMapping("students")
    public String getStudents() {
        return "Lista de estudantes";
    }
}
