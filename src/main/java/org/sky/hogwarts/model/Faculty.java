package org.sky.hogwarts.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Entity
public class Faculty {

    private String name;
    private String color;

    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(mappedBy = "student")
    public Faculty(String name, String color, Long id) {
        this.name = name;
        this.color = color;
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(name, faculty.name) && Objects.equals(color, faculty.color) && Objects.equals(id, faculty.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
