package com.example.Course.Controller;

import com.example.Course.API.APIResponse;
import com.example.Course.DTO.CourseCreateRequest;
import com.example.Course.DTO.InstructorCreateRequest;
import com.example.Course.Models.Instructor;
import com.example.Course.Repositories.CourseRepository;
import com.example.Course.Repositories.InstructorRepository;
import com.example.Course.Service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<Instructor>> create(@Valid @RequestBody InstructorCreateRequest instructorCreateRequest) {
        Instructor instructor = instructorService.createInstructor(instructorCreateRequest);
        return ResponseEntity.ok(new APIResponse<>(true, "Instructor Created Successfully", instructor));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Instructor>>> getAll() {
        return ResponseEntity.ok(new APIResponse<>(true, "Success", instructorService.findAll()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Instructor>> updateInstructor(@PathVariable int id, @Valid @RequestBody com.example.Course.DTO.InstructorUpdateRequest req) {
        Instructor instructor = instructorService.updateInstructor(id, req);
        return ResponseEntity.ok(new APIResponse<>(true, "Instructor Updated Successfully", instructor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteInstructor(@PathVariable int id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.ok(new APIResponse<>(true, "Instructor Deleted Successfully", null));
    }
}
