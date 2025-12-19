package org.sky.hogwarts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sky.hogwarts.model.Faculty;
import org.sky.hogwarts.service.FacultyService;

import java.util.Collections;

@RestController
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@RequestParam(required = false) Long id, @RequestParam(required = false) String color, @RequestParam(required = false) String name){
        if (color != null && !color.isBlank()){
            return ResponseEntity.ok(facultyService.findByColorOrNameIgnoreCase(color, name));
        }
        if (name != null && !name.isBlank()){
            return ResponseEntity.ok(facultyService.findByColorOrNameIgnoreCase(color, name));
        }
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty){
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty){
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping
    public ResponseEntity <Void> deleteFaculty(@PathVariable Long id){
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<String> findFaculties(@RequestParam(required = false) String color){
        if (color != null && !color.isBlank()){
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList().toString());
    }
}
