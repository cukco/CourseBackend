package com.example.Course.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false,unique = true)
    private String email;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses=new ArrayList<Course>();

    public Instructor(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Instructor() {}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public List<Course> getCourses() {return courses;}
    public void setCourses(List<Course> courses) {this.courses = courses;}

    @Override
    public String toString() {
        return "Instructor{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
    }
}
