package org.sky.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sky.hogwarts.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByAgeBetween(int minAge, int maxAge);

    Collection<Student> findAllByAge(int age);
}
