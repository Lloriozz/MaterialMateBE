package com.mm.mm.controller;

import com.mm.mm.dto.ExchangeInfoRequest.ExchangeInfoCreationRequest;
import com.mm.mm.entity.ExchangeInfo;
import com.mm.mm.entity.Item;
import com.mm.mm.entity.Student;
import com.mm.mm.service.ExchangeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
@CrossOrigin(origins = "*")
public class ExchangeInfoController {

    @Autowired
    private ExchangeInfoService exchangeInfoService;

    @PostMapping
    public ExchangeInfo createExchange(@RequestBody ExchangeInfoCreationRequest request) {
        return exchangeInfoService.createExchange(
                request.getStudentId(),
                request.getItemId()
        );
    }

    @GetMapping("/downloads/{studentId}")
    public List<ExchangeInfo> getDownloadedItems(@PathVariable String studentId) {
        return exchangeInfoService.getDownloadedItemsByStudent(studentId);
    }
}