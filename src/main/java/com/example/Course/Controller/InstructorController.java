package com.example.Course.Controller;

import com.example.Course.API.APIResponse;
import com.example.Course.DTO.CourseCreateRequest;
import com.example.Course.DTO.InstructorCreateRequest;
import com.example.Course.Models.Instructor;
import com.example.Course.Repositories.CourseRepository;
import com.example.Course.Repositories.InstructorRepository;
import com.example.Course.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public  InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<Instructor>> create(@RequestBody InstructorCreateRequest instructorCreateRequest) {
        try {
            Instructor instructor=instructorService.createInstructor(instructorCreateRequest);
            return ResponseEntity.ok(new APIResponse<>(true, "Instructor Created Successfully", instructor));
        }catch (Exception e){
            return ResponseEntity.status(400).body(new APIResponse<>(false,e.getMessage(),null));
        }
    }

    @org.springframework.web.bind.annotation.GetMapping
    public ResponseEntity<APIResponse<java.util.List<Instructor>>> getAll() {
        try {
            return ResponseEntity.ok(new APIResponse<>(true, "Success", instructorService.findAll()));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new APIResponse<>(false, e.getMessage(), null));
        }
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<APIResponse<Instructor>> updateInstructor(@org.springframework.web.bind.annotation.PathVariable int id, @RequestBody com.example.Course.DTO.InstructorUpdateRequest req) {
        try {
            Instructor instructor = instructorService.updateInstructor(id, req);
            return ResponseEntity.ok(new APIResponse<>(true, "Instructor Updated Successfully", instructor));
        } catch(Exception e) {
            return ResponseEntity.status(400).body(new APIResponse<>(false, e.getMessage(), null));
        }
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteInstructor(@org.springframework.web.bind.annotation.PathVariable int id) {
        try {
            instructorService.deleteInstructor(id);
            return ResponseEntity.ok(new APIResponse<>(true, "Instructor Deleted Successfully", null));
        } catch(Exception e) {
            return ResponseEntity.status(400).body(new APIResponse<>(false, e.getMessage(), null));
        }
    }
}
