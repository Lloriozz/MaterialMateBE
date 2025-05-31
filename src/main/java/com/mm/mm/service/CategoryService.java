package com.mm.mm.service;

import com.mm.mm.entity.Category;
import com.mm.mm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        logger.info("Getting all category records");
        return categoryRepository.findAll();
    }

    // You can add other business logic methods here

}
