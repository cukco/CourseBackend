package com.example.Course.DTO;

import java.time.LocalDateTime;

public class EnrollmentResponse {
    private int studentId;
    private int courseId;
    private LocalDateTime enrolledAt;

    public EnrollmentResponse(int studentId, int courseId, LocalDateTime enrolledAt) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrolledAt = enrolledAt;
    }

    public int getStudentId() {return studentId;}
    public int getCourseId() {return courseId;}
    public LocalDateTime getEnrolledAt() {return enrolledAt;}
    public void setStudentId(int studentId) {this.studentId = studentId;}
    public void setCourseId(int courseId) {this.courseId = courseId;}

}
