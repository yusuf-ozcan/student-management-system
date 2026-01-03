package com.example.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.entity.Department;
import com.example.studentmanagement.entity.Student; // Bunu ekledik
import com.example.studentmanagement.repository.DepartmentRepository;
import com.example.studentmanagement.repository.StudentRepository; // Bunu ekledik

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository; // Bölüm deposunu bağladık

    // --- ÖĞRENCİ METOTLARI ---
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // --- BÖLÜM (DEPARTMENT) METOTLARI ---
    
    // Arayüzdeki seçim kutusu için tüm bölümleri getirir
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}