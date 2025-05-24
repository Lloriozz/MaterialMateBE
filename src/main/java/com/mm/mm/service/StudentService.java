package com.mm.mm.service;

import com.mm.mm.dto.StudentRequest.StudentCreationRequest;

//import com.mm.mm.dto.request.UserUpdateRequest;
import com.mm.mm.entity.Student;
import com.mm.mm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(StudentCreationRequest request) {
        Student stu = new Student();

        stu.setUserName(request.getUsername());
        stu.setPassword(request.getPassword());
        stu.setTotalCredit(0);

        return studentRepository.save(stu);
    }
}