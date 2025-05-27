package com.mm.mm.controller;

import com.mm.mm.dto.StudentRequest.StudentCreationRequest;
import com.mm.mm.dto.StudentRequest.StudentLoginRequest;
import com.mm.mm.dto.StudentRequest.StudentResponseRequest;
import com.mm.mm.entity.Student;
import com.mm.mm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
            Student createdStudent = studentService.createStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
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






}