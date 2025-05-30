package com.mm.mm.controller;

import java.util.List;

import com.mm.mm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*") // Optional: only if you're working with frontend dev
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


}
