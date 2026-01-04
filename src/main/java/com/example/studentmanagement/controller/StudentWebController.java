package com.example.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.service.StudentService;
import com.example.studentmanagement.service.DepartmentService;

import jakarta.validation.Valid;

@Controller
public class StudentWebController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DepartmentService departmentService;

    // 1. Öğrenci Listesi + ARAMA ÖZELLİĞİ
    @GetMapping("/students")
    public String viewHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword != null) {
            // StudentService içinde searchStudents metodu olduğunu varsayıyoruz
            model.addAttribute("students", studentService.searchStudents(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("students", studentService.getAllStudents());
        }
        return "students";
    }

    // 2. Yeni Öğrenci Ekleme Formu
    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "create_student";
    }

    // 3. Yeni Öğrenci Kaydet + DOĞRULAMA (Validation)
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "create_student"; // Hata varsa forma geri dön
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // 4. Öğrenci Güncelleme Formu
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "edit_student";
    }

    // 5. Güncellenmiş Bilgileri Kaydet + DOĞRULAMA (Validation)
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "edit_student";
        }

        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDepartment(student.getDepartment());

        studentService.saveStudent(existingStudent);
        return "redirect:/students";
    }

    // 6. Öğrenci Sil
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}