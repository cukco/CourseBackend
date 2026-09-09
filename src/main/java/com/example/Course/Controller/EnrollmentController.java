package com.example.Course.Controller;

import com.example.Course.API.APIResponse;
import com.example.Course.DTO.EnrollmentCreateRequest;
import com.example.Course.DTO.EnrollmentResponse;
import com.example.Course.Models.Student;
import com.example.Course.Models.StudentEnrollment;
import com.example.Course.Repositories.StudentRepository;
import com.example.Course.Service.StudentEnrollmentService;
import com.example.Course.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/courses/{courseId}")
public class EnrollmentController {
    private final StudentEnrollmentService studentEnrollmentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public EnrollmentController(StudentEnrollmentService studentEnrollmentService) {
        this.studentEnrollmentService = studentEnrollmentService;
    }

    @PostMapping("/enrollments")
    public ResponseEntity<?> enrollStudent(@PathVariable int courseId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ học viên"));
        StudentEnrollment studentEnrollment = studentEnrollmentService.enrollStudent(student.getId(), courseId);
        EnrollmentResponse enrollmentResponse = new EnrollmentResponse(student.getId(), courseId, studentEnrollment.getCreatedAt());

        return ResponseEntity.ok(new APIResponse<>(true, "Đăng ký thành công", enrollmentResponse));
    }

    @DeleteMapping("/enrollments/students/{studentID}")
    public ResponseEntity<APIResponse<Object>> dropout(@PathVariable int courseId, @PathVariable int studentID) {
        studentEnrollmentService.dropout(studentID, courseId);
        return ResponseEntity.ok(new APIResponse<>(true, "Student successfully dropped out", null));
    }

    @GetMapping("/enrollments/students")
    public ResponseEntity<APIResponse<List<StudentEnrollment>>> findByName(@RequestParam(value = "search",required=false) String search) {
        List<StudentEnrollment> studentEnrollments = studentEnrollmentService.findByName(search);
        return ResponseEntity.ok(new APIResponse<>(true, "Search successful", studentEnrollments));
    }

    @GetMapping("/students")
    public ResponseEntity<APIResponse<List<Student>>> getStudentsByCourse(@PathVariable int courseId) {
        List<Student> students = studentEnrollmentService.findStudentsByCourseId(courseId);
        return ResponseEntity.ok(new APIResponse<>(true, "Success", students));
    }
}
