package com.example.Course.DTO;

public class InstructorUpdateRequest {
    private String name;
    private String email;

    public InstructorUpdateRequest(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}

}
