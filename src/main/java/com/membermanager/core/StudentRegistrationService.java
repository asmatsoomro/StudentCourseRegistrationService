package com.membermanager.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentRegistrationService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void registerNewStudent(Student student) {
        studentRepository.save(student);
    }

    public void removeMember(Student member){
        List<Student> studentTobeRemoved = studentRepository.findByName(member.getName());
        studentRepository.delete(member);
    }

    public List<Student> getMember(String firstName) {
        return studentRepository.findByName(firstName);
    }


    public void removeAllMembers(){
        studentRepository.deleteAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id);
    }
}
