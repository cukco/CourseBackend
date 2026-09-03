package com.example.Course.Service;

import com.example.Course.Models.Course;
import com.example.Course.Models.Student;
import com.example.Course.Models.StudentEnrollment;
import com.example.Course.Repositories.CourseRepository;
import com.example.Course.Repositories.StudentEnrollmentRepository;
import com.example.Course.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentEnrollmentService {
    @Autowired
    private final StudentEnrollmentRepository studentEnrollmentRepository;

    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final StudentRepository studentRepository;
    public StudentEnrollmentService(StudentEnrollmentRepository studentEnrollmentRepository,
                                    CourseRepository courseRepository,
                                    StudentRepository studentRepository) {
        this.studentEnrollmentRepository = studentEnrollmentRepository;
        this.courseRepository=courseRepository;
        this.studentRepository=studentRepository;
    }

    public StudentEnrollment enrollStudent(int studentId, int enrollmentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student not found"));
        Course course = courseRepository.findById(enrollmentId).orElseThrow(()->new RuntimeException("Course not found"));
        if(course.getStatus() != com.example.Course.Models.CourseStatus.ACTIVE){
            throw new RuntimeException("Course Not Active");
        }
        StudentEnrollment studentEnrollment=new StudentEnrollment();
        studentEnrollment.setStudent(student);
        studentEnrollment.setCourse(course);
        return studentEnrollmentRepository.save(studentEnrollment);
    }

    public StudentEnrollment dropout(int studentId, int courseId) {
        Course course=courseRepository.findById(courseId).orElseThrow(()->new RuntimeException("Course not found"));
        Student student=studentRepository.findById(studentId).orElseThrow(()->new RuntimeException("Student not found"));
        
        StudentEnrollment enrollment = studentEnrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
                
        studentEnrollmentRepository.delete(enrollment);
        return enrollment;
    }

    public List<StudentEnrollment> findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return studentEnrollmentRepository.findAll();
        }
        Student student = studentRepository.findByName(name).orElseThrow(()->new RuntimeException("Student not found"));
        return studentEnrollmentRepository.findStudentEnrollmentByStudent(student).orElseThrow(()->new RuntimeException("Enrollment not found"));
    }

    public List<Student> findStudentsByCourseId(int courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        List<StudentEnrollment> enrollments = studentEnrollmentRepository.findStudentEnrollmentByCourse(course).orElse(new java.util.ArrayList<>());
        return enrollments.stream().map(StudentEnrollment::getStudent).collect(java.util.stream.Collectors.toList());
    }
}

