package com.membermanager.core;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findByName(String name);

//    @Modifying
//    @Query("UPDATE Member m SET m.firstName=:firstName, m.lastName=:lastName, m.dateOfBirth = :dateOfBirth, m.zipCode = :zipCode WHERE m.id = :id")
//    int updateMember(@Param("id") int id, @Param("firstName") String firstName, @Param("lastName") String lastName,
//                      @Param("dateOfBirth") Date dateOfBirth, @Param("zipCode") int zipCode );

    Student findById(int id);
}