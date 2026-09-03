package com.example.Course.Repositories;

import com.example.Course.Models.Student;
import com.example.Course.Models.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Integer> {
    java.util.Optional<StudentEnrollment> findByStudentIdAndCourseId(int studentId, int courseId);

    Optional<List<StudentEnrollment>> findStudentEnrollmentByStudent(Student student);
    Optional<List<StudentEnrollment>> findStudentEnrollmentByCourse(com.example.Course.Models.Course course);
}
