package com.example.Course.DTO;

import com.example.Course.Models.CourseStatus;

public class CourseResponseV2 {
    private int id;
    private String title;
    private CourseStatus status;
    private String instructorName;

    public CourseResponseV2() {}
    public CourseResponseV2(int id, String title, CourseStatus status, String instructorName) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.instructorName = instructorName;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public CourseStatus getStatus() {return status;}
    public void setStatus(CourseStatus status) {this.status = status;}
    public String getInstructorName() {return instructorName;}
    public void setInstructorName(String instructorName) {this.instructorName = instructorName;}
}
