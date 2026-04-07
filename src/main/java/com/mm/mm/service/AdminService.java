package com.mm.mm.service;

import com.mm.mm.entity.Admin;
import com.mm.mm.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AdminService {
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        logger.info("Getting all admin records");
        return adminRepository.findAll();
    }

    // You can add other business logic methods here

}
