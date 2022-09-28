package com.membermanager.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin("http://localhost:4201")
public class StudentRegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentRegistrationController.class);

    @Autowired
    private StudentRegistrationService studentRegistrationService;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseRegistrationService courseRegistrationService;

    @RequestMapping(value = {"/ping", "/Ping"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity ping()
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Testing the endpoint");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("PONG");

    }

    @RequestMapping(value = {"/student", "/Student"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllMembers()
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to get all students");
        List<Student> memberList = studentRegistrationService.getAllStudents();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberList);

    }



    @RequestMapping(value = {"/student", "/Student"}, method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity registerNewStudent(@RequestBody Student student)
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to add a new student in repository");
        if (student == null || StringUtils.isEmpty(student.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(student);
        }
        Set<Course> courseList = student.getEnrolledCourses();

        Set<Course> registeredCourses = new HashSet<>();

        courseList.forEach( course -> {
            Course c = courseRegistrationService.getCourse(course.getCourseCode());
            registeredCourses.add(c);
        });
        student.setEnrolledCourses(registeredCourses);
        studentRegistrationService.registerNewStudent(student);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @RequestMapping(value = {"/student", "/Student"}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteStudent(@PathVariable int id)
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to delete a member");
        if (id <1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Student member = studentRegistrationService.getStudentById(id);
        studentRegistrationService.removeMember(member);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
    }

    @RequestMapping(value = {"/course", "/Course"}, method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity registerNewCourse(@RequestBody Course course)
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to add a new student in repository");
        if (course == null || StringUtils.isEmpty(course.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(course);
        }
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }


    @RequestMapping(value = {"/course/{courseCode}", "/Course/{courseCode}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCoursewithCourseCode(@PathVariable String courseCode)
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to get all students");
        Course course = courseRepository.findByCourseCode(courseCode);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(course);

    }


    @RequestMapping(value = {"/course", "/Course"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllCourses()
            throws IOException, ExecutionException, InterruptedException {
        LOGGER.info("Received a request to get all students");
        List<Course> courseList = courseRepository.findAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(courseList);

    }


}