package org.sky.hogwarts.service;

import jakarta.annotation.Nullable;
import org.jcp.xml.dsig.internal.DigesterOutputStream;
import org.sky.hogwarts.model.Avatar;
import org.sky.hogwarts.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.sky.hogwarts.model.Student;
import org.sky.hogwarts.repository.StudentRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class StudentService {

    @Value("${avatars.dir.path}")
    private String avatarsDir;

    private StudentRepository studentRepository;
    private AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    public Student addStudent(Student student){
        student.setId(null);
    return studentRepository.save(student);
    }

    public Student findStudent(Long id){
        return studentRepository.findById(id).orElseThrow();
    }

    public Student editStudent (Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(long id){
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findAllByAge(age);
    }

    public List<Student> findByAgeBetween(Integer minAge, Integer maxAge){
        return Collections.singletonList(studentRepository.findByAgeBetween(minAge, maxAge));
    }

    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }

    public void uploadAvatar(Long id, MultipartFile avatar) throws IOException {
        Long studentId = null;
        Student student = findStudent(studentId);

        Path filePath = Path.of(avatarsDir, studentId + "." + getExtension(file.getOrigialFileName()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try(InputStream is = file.getInputStream();
            OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
            ){
            bis.transferTo(bos);
        }

        Avatar avatar1 = avatarRepository.findByStudentId(studentId).orElseGet(Avatar::new);
        avatar1.setStudent(student);
        avatar1.setFilePath(filePath.toString());
        avatar1.setFileSize(file.getSize());
        avatar1.setMediaType(file.getContentType());
        avatar1.setData(file.getBytes());

        avatarRepository.save(avatar1);
    }

    private String getExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
