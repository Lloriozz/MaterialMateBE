package com.mm.mm.repository;

import com.mm.mm.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByUserName(String userName);
    Student findByUserName(String userName);

}