package org.sky.hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sky.hogwarts.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Faculty findByColorOrNameIgnoreCase(String color, String name);


}
