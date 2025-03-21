package com.abutua.product_backend.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abutua.product_backend.models.Student;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class StudentController {


    //list of Students
       private List<Student> students = new ArrayList<>();




     @PostMapping("students") //salvar os produtos 
    public ResponseEntity<Student> save(@RequestBody Student student){
        student.setId(students.size()+1);
        students.add(student);

        URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(student.getId())
        .toUri(); //construindo uma URI para cada product, junto com o ID


        return ResponseEntity.created(location).body(student);
    }                                            

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
