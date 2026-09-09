package com.example.Course.Controller;

import com.example.Course.API.APIResponse;
import com.example.Course.DTO.CourseCreateRequest;
import com.example.Course.DTO.CourseResponse;
import com.example.Course.DTO.CourseUpdateRequest;
import com.example.Course.Models.Course;
import com.example.Course.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<Course>> saveCourse(@Valid @RequestBody CourseCreateRequest req) {
        Course course = courseService.createCourse(req);
        return ResponseEntity.ok(new APIResponse<>(true, "Course Created Successfully", course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Course>> updateCourse(@PathVariable int id, @Valid @RequestBody CourseUpdateRequest req) {
        Course course = courseService.updateCourse(id, req);
        return ResponseEntity.ok(new APIResponse<>(true, "Course Updated Successfully", course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(new APIResponse<>(true, "Course Deleted Successfully", null));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<CourseResponse>>> findAll() {
        List<CourseResponse> courseResponses = courseService.findAll();
        return ResponseEntity.ok(new APIResponse<>(true, "Success", courseResponses));
    }

    @GetMapping("/paged")
    public ResponseEntity<APIResponse<com.example.Course.DTO.PageResponse<com.example.Course.DTO.CourseResponseV2>>> findByStatus(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) com.example.Course.Models.CourseStatus status,
            @RequestParam(required = false) String title) {
        com.example.Course.DTO.PageResponse<com.example.Course.DTO.CourseResponseV2> courseResponses = courseService.getPagedCoursesV2(page, size, sortBy, direction, status, title);
        return ResponseEntity.ok(new APIResponse<>(true, "Success", courseResponses));
    }
}
