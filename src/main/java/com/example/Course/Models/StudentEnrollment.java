package com.example.Course.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_enrollments")
public class StudentEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;


    public StudentEnrollment(){}

    public StudentEnrollment(int id, Course course,Student student) {
        this.id = id;
        this.student = student;
        this.course = course;
    }

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Course getCourse() {return course;}
    public void setCourse(Course course) {this.course = course;}
    public Student getStudent() {return student;}
    public void setStudent(Student student) {this.student = student;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}


}
