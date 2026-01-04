package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.studentmanagement.entity.Department;
import com.example.studentmanagement.repository.DepartmentRepository;

@SpringBootApplication
public class StudentManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner run(DepartmentRepository departmentRepository) {
        return args -> {
            
            if (departmentRepository.count() == 0) {
                departmentRepository.save(new Department("Bilgisayar Mühendisliği"));
                departmentRepository.save(new Department("Yazılım Mühendisliği"));
                departmentRepository.save(new Department("Yapay Zeka Mühendisliği"));
                departmentRepository.save(new Department("Makine Mühendisliği"));
                System.out.println("Başlangıç bölümleri veritabanına eklendi.");
            }
        };
    }
}