package com.membermanager.core;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {


    @Id
    @GeneratedValue
    private int id;
    private String name;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "course_enrolled",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> enrolledCourses;


    public Student(){

    }

    public Student(String firstName, String lastName) {
        this.name = firstName;
    }
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(Set<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
