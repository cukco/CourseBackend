package com.example.Course.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.example.Course.Models.CourseStatus;

public class CourseUpdateRequest {
    @NotBlank(message = "Title is mandatory")
    private String title;
    
    @NotNull(message = "Status is mandatory")
    private CourseStatus status;
    
    @NotNull(message = "Instructor ID is mandatory")
    private Integer instructorId;

    public String getTitle() {return this.title;}
    public void setTitle(String title) {this.title = title;}
    public CourseStatus getStatus() {return this.status;}
    public void setStatus(CourseStatus status) {this.status = status;}
    public Integer getInstructorId() {return this.instructorId;}
    public void setInstructorId(Integer instructorId) {this.instructorId = instructorId;}

    public CourseUpdateRequest(String title, CourseStatus status, Integer instructorId) {
        this.title = title;
        this.status = status;
        this.instructorId = instructorId;
    }
    public CourseUpdateRequest() {}
}
