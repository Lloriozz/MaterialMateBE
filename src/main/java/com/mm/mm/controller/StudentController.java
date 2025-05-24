package com.mm.mm.controller;

import com.mm.mm.dto.StudentRequest.StudentCreationRequest;
import com.mm.mm.entity.Student;
import com.mm.mm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
//@CrossOrigin(origins = "*") // Optional: only if you're working with frontend dev
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping()
    public Student createStudent(@RequestBody StudentCreationRequest request) {
        return studentService.createStudent(request);
    }
//
//    @GetMapping("/{itemID}")
//    public Student getItemById(@PathVariable("itemID") Long id) {
//        return studentService.get(id);
//    }
//
//    @GetMapping("/approved")
//    public List<Item> getApprovedItems() {
//        return itemService.getApprovedItems();
//    }
}
