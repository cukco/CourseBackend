package com.example.Course.DTO;

import com.example.Course.Models.CourseStatus;

public class CourseResponse {
    private int id;
    private String title;
    private CourseStatus status;
    private CourseInstructorResponse instructor;

    public CourseResponse(int id, String title, CourseStatus status, CourseInstructorResponse instructor) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructor = instructor;
    }

    public CourseResponse() {}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public CourseStatus getStatus() {return status;}
    public void setStatus(CourseStatus status) {this.status = status;}
    public CourseInstructorResponse getInstructor() {return instructor;}

}
