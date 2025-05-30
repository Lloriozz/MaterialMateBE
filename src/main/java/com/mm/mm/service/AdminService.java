package com.mm.mm.service;

import com.mm.mm.entity.Admin;
import com.mm.mm.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;


}

