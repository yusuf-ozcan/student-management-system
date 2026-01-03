package com.example.studentmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Burada hiçbir metod yazmasan bile JpaRepository sayesinde
    // save(), findAll(), findById(), delete() gibi tüm metodlar hazır gelir.
}