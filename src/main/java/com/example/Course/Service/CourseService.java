package com.example.Course.Service;

import com.example.Course.DTO.CourseCreateRequest;
import com.example.Course.DTO.CourseInstructorResponse;
import com.example.Course.DTO.CourseResponse;
import com.example.Course.DTO.CourseUpdateRequest;
import com.example.Course.Models.Course;
import com.example.Course.Models.Instructor;
import com.example.Course.Repositories.CourseRepository;
import com.example.Course.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course findCourseByID(int id){
        return courseRepository.findById(id).orElseThrow(()->new RuntimeException("Course not found"));
    }

    public List<CourseResponse> findAll(){

        return courseRepository.findAll().stream().map(course -> new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getStatus(),
                new CourseInstructorResponse(
                        course.getInstructor().getId(),
                        course.getInstructor().getName()
                ))
        ).toList();
    }

    public Course createCourse(CourseCreateRequest req){
        Instructor instructor=instructorRepository.findById(req.getInstructorId()).
                orElseThrow(()->new RuntimeException("Instructor not found"));
        Course course=new Course();
        course.setStatus(req.getStatus());
        course.setTitle(req.getTitle());
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public Course updateCourse(int id, CourseUpdateRequest req){
        Course course=findCourseByID(id);
        course.setTitle(req.getTitle());
        course.setStatus(req.getStatus());
        return courseRepository.save(course);
    }

    public void deleteCourse(int id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found");
        }
        courseRepository.deleteById(id);
    }

    public com.example.Course.DTO.PageResponse<com.example.Course.DTO.CourseResponseV2> getPagedCoursesV2(int page,
                                                                                                            int size,
                                                                                                            String sortBy,
                                                                                                            String direction,
                                                                                                            com.example.Course.Models.CourseStatus status,
                                                                                                            String title) {
        if (page < 0) page = 0;
        if (sortBy == null || sortBy.trim().equals("")) sortBy = "id";

        org.springframework.data.domain.Sort sort;

        if (direction == null || direction.trim().equals("")) {
            sort = org.springframework.data.domain.Sort.unsorted();
        } else {
            sort = org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.fromString(direction), sortBy);
        }

        org.springframework.data.domain.Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, sort);
        org.springframework.data.domain.Page<com.example.Course.DTO.CourseResponseV2> page1 = courseRepository.findAllByStatusV2(title, status, pageable);

        com.example.Course.DTO.PageResponse<com.example.Course.DTO.CourseResponseV2> pageResponse = new com.example.Course.DTO.PageResponse<>();

        pageResponse.setPage(page1.getNumber());
        pageResponse.setSize(page1.getSize());
        pageResponse.setItems(page1.getContent());
        pageResponse.setTotalPages(page1.getTotalPages());
        pageResponse.setTotalItems(page1.getTotalElements());
        pageResponse.setLast(page1.isLast());

        return pageResponse;
    }
}
