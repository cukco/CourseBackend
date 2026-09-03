package com.example.Course.DTO;

import com.example.Course.Models.CourseStatus;

public class CourseUpdateRequest {
    private String title;
    private CourseStatus status;
    private int instructorId;

    public String getTitle() {return this.title;}
    public void setTitle(String title) {this.title = title;}
    public CourseStatus getStatus() {return this.status;}
    public void setStatus(CourseStatus status) {this.status = status;}
    public int getInstructorId() {return this.instructorId;}
    public void setInstructorId(int instructorId) {this.instructorId = instructorId;}

    public CourseUpdateRequest(String title, CourseStatus status, int instructorId) {
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }
    public CourseUpdateRequest() {}
}
