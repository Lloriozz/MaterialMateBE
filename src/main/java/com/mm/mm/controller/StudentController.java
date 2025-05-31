package com.mm.mm.controller;

import com.mm.mm.dto.StudentRequest.StudentCreationRequest;
import com.mm.mm.dto.StudentRequest.StudentLoginRequest;
import com.mm.mm.dto.StudentRequest.StudentProfileSetupRequest;
import com.mm.mm.entity.Student;
import com.mm.mm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*") // Enable CORS for frontend
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

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
            Student createdStudent = studentService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PutMapping("/profile-setup")
    public ResponseEntity<?> updateProfile(@RequestBody StudentProfileSetupRequest request) {
        try {
            Student updatedStudent = studentService.updateStudentProfile(request);
            return ResponseEntity.ok(updatedStudent);  // Return updated profile
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody StudentLoginRequest request) {
        return studentService.loginStudent(request); // Delegate to service
    }

    @GetMapping("/id/{username}")
    public ResponseEntity<?> getStudentIdByUsername(@PathVariable String username) {
        // Find the student by username
        Student student = studentService.findByUsername(username);

        if (student != null) {
            // Return the student ID
            Map<String, Object> response = new HashMap<>();
            response.put("id", student.getStudentID());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found with username: " + username);
        }
    }

    @GetMapping("/{username}/credits")
    public Integer getTotalCredits(@PathVariable String username) {
        logger.info("Received request to get total credits for username: {}", username);
        return studentService.getTotalCreditsByUsername(username);
    }
}