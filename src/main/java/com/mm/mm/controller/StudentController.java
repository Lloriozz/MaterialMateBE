package com.mm.mm.controller;

import com.mm.mm.dto.StudentRequest.StudentCreationRequest;
import com.mm.mm.entity.Student;
import com.mm.mm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*") // Enable CORS for frontend
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllStudents() {
        try {
            return ResponseEntity.ok(studentService.getAllStudents());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/check-username")
    public ResponseEntity<?> checkUsernameExists(@RequestParam("username") String username) {
        try {
            boolean exists = studentService.usernameExists(username);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error checking username: " + e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createStudent(@RequestBody StudentCreationRequest request) {
        try {
            // Validate request
            if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Username is required");
            }

            if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Password is required");
            }

            // Check if username already exists
            if (studentService.usernameExists(request.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
            }

            // Create student
            Student createdStudent = studentService.createStudent(request);

            // Return success response without password
            StudentResponse response = new StudentResponse();
            response.setId(createdStudent.getId());
            response.setUsername(createdStudent.getUserName());
            response.setTotalCredit(createdStudent.getTotalCredit());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating student: " + e.getMessage());
        }
    }

    // Response DTO to avoid sending password back
    public static class StudentResponse {
        private Long id;
        private String username;
        private Integer totalCredit;

        // Getters and setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public Integer getTotalCredit() { return totalCredit; }
        public void setTotalCredit(Integer totalCredit) { this.totalCredit = totalCredit; }
    }
}