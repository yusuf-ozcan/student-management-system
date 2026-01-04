package com.example.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.entity.Department;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.repository.DepartmentRepository;
import com.example.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // --- ÖĞRENCİ İŞLEMLERİ ---
    
    // Kullanıcının girdiği tek bir kelimeyi 3 farklı alanda aratır
    public List<Student> searchStudents(String keyword) {
        if (keyword != null && !keyword.isEmpty()) {
            return studentRepository.findByFirstNameContainingOrLastNameContainingOrEmailContaining(keyword, keyword, keyword);
        }
        return studentRepository.findAll();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // --- DEPARTMAN İŞLEMLERİ ---
    
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
}