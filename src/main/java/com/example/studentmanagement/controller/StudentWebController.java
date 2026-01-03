package com.example.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;

@Controller
public class StudentWebController {

    @Autowired
    private StudentService studentService;

    // 1. Öğrenci Listesi Sayfası
    @GetMapping("/students")
    public String viewHomePage(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; 
    }

    // 2. Yeni Öğrenci Ekleme Formunu Aç
    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student"; 
    }

    // 3. Yeni Öğrenci Kaydet
    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // 4. Düzenleme Formunu Aç (Mevcut verileri getirir)
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    // 5. Güncellenmiş Bilgileri Kaydet
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student) {
        // Veritabanındaki mevcut öğrenciyi bul
        Student existingStudent = studentService.getStudentById(id);
        
        // Formdan gelen güncel bilgilerle mevcut öğrenciyi güncelle
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        
        // Değişiklikleri kaydet
        studentService.saveStudent(existingStudent);
        return "redirect:/students";
    }

    // 6. Öğrenci Silme
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}