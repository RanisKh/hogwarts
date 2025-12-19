package org.sky.hogwarts.service;

import org.springframework.stereotype.Service;
import org.sky.hogwarts.model.Student;
import org.sky.hogwarts.repository.StudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){
    return studentRepository.save(student);
    }

    public Optional<Student> findStudent(Long id){

        return Optional.of(studentRepository.getById(id));
    }

    public Student editStudent (Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(long id){
        studentRepository.deleteById(id);
    }

    public int findByAge(int age) {
        return age;
    }

    public List<Student> findByAgeBetween(Integer minAge, Integer maxAge){
        return Collections.singletonList(studentRepository.findByAgeBetween(minAge, maxAge));
    }

//    public @Nullable Collection<Student> findByAge(int age) {
//        ArrayList<Student> result = new ArrayList<>();
//        for (Student student : student.values()){
//            if (student.getAge() == age){
//                result.add(student);
//            }
//        }
//        return result;
//    }
}
