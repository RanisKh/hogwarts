package org.sky.hogwarts.service;

import org.springframework.stereotype.Service;
import org.sky.hogwarts.model.Faculty;
import org.sky.hogwarts.repository.FacultyRepository;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id){
        return facultyRepository.getById(id);
    }

    public Faculty editFaculty (Faculty faculty){
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id){
        facultyRepository.deleteById(id);
    }


    public String findByColor(String color) {
        return color;
    }

    public Faculty findByColorOrNameIgnoreCase(String color, String name){
        return facultyRepository.findByColorOrNameIgnoreCase(color, name);
    }
}
