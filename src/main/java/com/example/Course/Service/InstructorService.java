package com.example.Course.Service;

import com.example.Course.DTO.InstructorCreateRequest;
import com.example.Course.DTO.InstructorUpdateRequest;
import com.example.Course.Models.Instructor;
import com.example.Course.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor findInstructorByID(int id){
        return instructorRepository.findById(id).orElseThrow(()->new RuntimeException("Instructor not found"));
    }

    public List<Instructor> findAll(){
        return instructorRepository.findAll();
    }

    public Instructor createInstructor(InstructorCreateRequest req){
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        return instructorRepository.save(instructor);
    }

    public Instructor updateInstructor(int id,InstructorUpdateRequest req){
        Instructor instructor = findInstructorByID(id);
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(int id) {
        if (!instructorRepository.existsById(id)) {
            throw new RuntimeException("Instructor not found");
        }
        instructorRepository.deleteById(id);
    }
}
