package com.membermanager.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRegistrationService {

    @Autowired
    CourseRepository courseRepository;

    public Course getCourse(String courseCode){
        return courseRepository.findByCourseCode(courseCode);
    }
}
