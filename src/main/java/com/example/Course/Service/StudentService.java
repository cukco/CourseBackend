package com.example.Course.Service;

import com.example.Course.DTO.StudentCreateRequest;
import com.example.Course.DTO.StudentUpdateRequest;
import com.example.Course.Models.Student;
import com.example.Course.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElseThrow(()->new RuntimeException("Student not found"));
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student create(StudentCreateRequest req) {
        Student student = new Student();
        student.setName(req.getName());
        student.setEmail(req.getEmail());
        return studentRepository.save(student);
    }

    public Student update(int id,StudentUpdateRequest req) {
        Student student = getStudentById(id);
        student.setName(req.getName());
        student.setEmail(req.getEmail());
        return studentRepository.save(student);
    }

    public Student findByName(String name){
        return studentRepository.findByName(name).orElseThrow(()->new RuntimeException("Student not found"));
    }
}
