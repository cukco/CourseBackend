package com.example.Course.Controller;

import com.example.Course.API.APIResponse;
import com.example.Course.DTO.StudentCreateRequest;
import com.example.Course.Models.Student;
import com.example.Course.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<StudentCreateRequest>> addStudent(@Valid @RequestBody StudentCreateRequest req){
        Student student = studentService.create(req);
        return ResponseEntity.ok(new APIResponse<>(true, "Student Created Sucessfully", req));
    }

    @Autowired
    private com.example.Course.Repositories.StudentRepository studentRepository;
    
    @Autowired
    private com.example.Course.Repositories.StudentEnrollmentRepository studentEnrollmentRepository;

    @org.springframework.web.bind.annotation.GetMapping("/me/courses")
    public ResponseEntity<?> getMyCourses() {
        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
                
        java.util.List<com.example.Course.Models.StudentEnrollment> enrollments = studentEnrollmentRepository.findStudentEnrollmentByStudent(student)
                .orElse(new java.util.ArrayList<>());
        
        java.util.List<com.example.Course.DTO.CourseResponseV2> myCourses = enrollments.stream()
                .map(com.example.Course.Models.StudentEnrollment::getCourse)
                .map(course -> new com.example.Course.DTO.CourseResponseV2(
                    course.getId(), 
                    course.getTitle(), 
                    course.getStatus(), 
                    course.getInstructor() != null ? course.getInstructor().getName() : "Chưa cập nhật"
                ))
                .collect(java.util.stream.Collectors.toList());
                
        return ResponseEntity.ok(new APIResponse<>(true, "Success", myCourses));
    }

    @org.springframework.web.bind.annotation.GetMapping("/me")
    public ResponseEntity<?> getMyProfile() {
        org.springframework.security.core.Authentication auth = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
                
        return ResponseEntity.ok(new APIResponse<>(true, "Success", student));
    }
}
