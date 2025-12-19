package org.sky.hogwarts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sky.hogwarts.model.Student;
import org.sky.hogwarts.service.StudentService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Student>> getStudentInfo(@PathVariable long id){
        Optional<Student> student = studentService.findStudent(id);
        if (student.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("{age-range}")
    public List<Student> findByAgeBetween(@RequestParam Integer minAge, @RequestParam Integer maxAge){
        return studentService.findByAgeBetween(minAge, maxAge);
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PutMapping
    public  ResponseEntity<Student> editStudent (@RequestBody Student student){
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(student);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity.BodyBuilder findStudents(@RequestParam(required = false) int age){
        if (age> 0){
            return (ResponseEntity.BodyBuilder) ResponseEntity.ok(studentService.findByAge(age));
        }
        return (ResponseEntity.BodyBuilder) ResponseEntity.ok(Collections.emptyList());
    }
}
