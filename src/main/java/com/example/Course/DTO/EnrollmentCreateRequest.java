package com.example.Course.DTO;

import jakarta.validation.constraints.NotNull;

public class EnrollmentCreateRequest {
    @NotNull(message = "Student ID is mandatory")
    private Integer studentID;
    public EnrollmentCreateRequest(Integer studentID){
        this.studentID = studentID;
    }

    public Integer getStudentID() {return studentID;}
    public void setStudentID(Integer studentID) {this.studentID = studentID;}
}
