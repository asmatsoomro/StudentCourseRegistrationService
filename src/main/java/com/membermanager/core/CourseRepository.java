package com.membermanager.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CourseRepository extends JpaRepository<Course, String> {

    Student findById(int id);

    Course findByCourseCode(String courseCode);
}