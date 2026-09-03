package com.example.Course.DTO;

public class EnrollmentCreateRequest {
    private int studentID;
    public EnrollmentCreateRequest(int studentID){
        this.studentID = studentID;
    }

    public int getStudentID() {return studentID;}
    public void setStudentID(int studentID) {this.studentID = studentID;}
}
