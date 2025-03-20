package com.abutua.product_backend.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abutua.product_backend.models.Student;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class StudentController {


    //list of Students
    private List<Student> students = Arrays.asList(new Student(1, "student 1", "student1@gmail.com", "number1", 2, 1 ),
                                                new Student(2, "student 2", "student2@gmail.com", "number2", 1, 2 ),
                                                new Student(3, "student 3", "student3@gmail.com", "number3", 1, 2 ));

    //endpoint GET students
    @GetMapping("students") //get students
    public List<Student> getStudents() {
        return students;
    }


    //endpoint GET students by ID
     @GetMapping("students/{id}") // responseEnstity trata a resposta
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Student stud = students.stream() //method to filter the list
                                .filter(p -> p.getId() == id) //filter the list by ID
                                .findFirst() //if ID exists, the list will store the student
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found :(")); //exception

        return ResponseEntity.ok(stud); //if not exeption, throw the student!

    }


}
