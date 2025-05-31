package com.mm.mm.service;

import com.mm.mm.dto.StudentRequest.StudentCreationRequest;
import com.mm.mm.dto.StudentRequest.StudentLoginRequest;
import com.mm.mm.dto.StudentRequest.StudentProfileSetupRequest;
import com.mm.mm.entity.Student;
import com.mm.mm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean usernameExists(String username) {
        // Handle null or empty username
        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        try {
            return studentRepository.existsByUsername(username.trim());
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
            student.setUsername(request.getUsername().trim());

            // Hash the password before saving
            String hashedPassword = passwordEncoder.encode(request.getPassword());
            student.setPassword(hashedPassword);

            // Set default values
            student.setTotalCredits(0);
            student.setEmail(null); // Will be set later
            student.setFirstName(null);
            student.setLastName(null);
            return studentRepository.save(student);

        } catch (Exception e) {
            System.err.println("Error creating student: " + e.getMessage());
            throw new RuntimeException("Unable to create student account", e);
        }
    }

    public Student updateStudentProfile(StudentProfileSetupRequest request) {
        Student student = studentRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Student not found with username: " + request.getUsername()));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setCountry(request.getCountry());
        student.setUniversity(request.getUniversity());

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found with username: " + username));
    }

    // Method for login verification (you'll need this later)
    public boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public ResponseEntity<String> loginStudent(StudentLoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        Student student = studentRepository.findByUsername(username)
                .orElse(null);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username not existed");
        }

        if (!verifyPassword(password, student.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }

        return ResponseEntity.ok("Login successful");
    }

    public Integer getTotalCreditsByUsername(String username) {
        logger.info("Getting total credits for username: {}", username);

        return studentRepository.findByUsername(username)
                .map(Student::getTotalCredits)
                .orElseThrow(() -> new RuntimeException("Student not found with username: " + username));
    }
}