package com.example.Course.Repositories;

import com.example.Course.DTO.CourseResponseV2;
import com.example.Course.Models.Course;
import com.example.Course.Models.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT new com.example.Course.DTO.CourseResponseV2(c.id, c.title, c.status, c.instructor.name) FROM Course c WHERE " +
            "(:title IS NULL OR c.title LIKE %:title%) AND " +
            "(:status IS NULL OR c.status = :status)")
    Page<CourseResponseV2> findAllByStatusV2(@Param("title") String title, @Param("status") CourseStatus status, Pageable pageable);
}
