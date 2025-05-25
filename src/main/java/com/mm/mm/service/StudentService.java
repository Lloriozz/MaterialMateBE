package com.mm.mm.service;

import com.mm.mm.dto.StudentRequest.StudentCreationRequest;
import com.mm.mm.entity.Student;
import com.mm.mm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean usernameExists(String username) {
        // Handle null or empty username
        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        try {
            return studentRepository.existsByUserName(username.trim());
        } catch (Exception e) {
            System.err.println("Error checking username existence: " + e.getMessage());
            throw new RuntimeException("Unable to check username availability", e);
        }
    }

    public Student createStudent(StudentCreationRequest request) {
        // Double-check username doesn't exist
        if (usernameExists(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        try {
            Student student = new Student();
            student.setUserName(request.getUsername().trim());

            // Hash the password before saving
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            student.setPassword(hashedPassword);

            // Set default values
            student.setTotalCredit(0);
            student.setEmail(null); // Will be set later
            student.setFullName(null); // Will be set later

            return studentRepository.save(student);

        } catch (Exception e) {
            System.err.println("Error creating student: " + e.getMessage());
            throw new RuntimeException("Unable to create student account", e);
        }
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student findByUsername(String username) {
        return studentRepository.findByUserName(username);
    }

    // Method for login verification (you'll need this later)
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}