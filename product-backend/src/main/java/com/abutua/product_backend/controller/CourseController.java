package com.abutua.product_backend.controller;

import com.abutua.product_backend.models.Course;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

    @CrossOrigin
    @RestController
    public class CourseController {
    private List<Course> courses = Arrays.asList(new Course(1, "Java"),
                                                new Course(2, "Angular"),
                                                new Course(3, "C#"),
                                                new Course(4, "Python")); //oi


     @GetMapping("courses/{id}") // responseEnstity trata a resposta
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        Course cour = courses.stream() //metodos para filtrar lista
                                .filter(p -> p.getId() == id) //filtrar lista pelo id
                                .findFirst() //se existir, a lista vai guardar o curso
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found")); //lança a exceção

        return ResponseEntity.ok(cour); //se não lançar a exceção, ele devolve ocurso

    }


      //endpoint GET courses
    @GetMapping("courses") //get Courses
    public List<Course> getCourses() {
        return courses;
    }

}
