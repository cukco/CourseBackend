package com.example.Course.DTO;

public class CourseInstructorResponse {
    private int id;
    private String name;

    public CourseInstructorResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CourseInstructorResponse() {}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
