package com.mm.mm.repository;

import com.mm.mm.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    boolean existsByUsername(String username);

    Optional<Student> findByUsername(String username);
}