package com.example.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Tüm öğrencileri getir
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Yeni öğrenci kaydet
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // ID'ye göre öğrenci bul
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // Öğrenci sil
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}