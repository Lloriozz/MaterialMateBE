package com.mm.mm.controller;

import com.mm.mm.dto.ExchangeInfoRequest.ExchangeInfoCreationRequest;
import com.mm.mm.entity.ExchangeInfo;
import com.mm.mm.entity.Item;
import com.mm.mm.entity.Student;
import com.mm.mm.service.ExchangeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
@CrossOrigin(origins = "*")
public class ExchangeInfoController {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeInfoController.class);

    @Autowired
    private ExchangeInfoService exchangeInfoService;

    @PostMapping
    public ResponseEntity<?> createExchange(@RequestBody ExchangeInfoCreationRequest request) {
        try {
            logger.info("Received request to create new exchange info");
            ExchangeInfo createdExchange = exchangeInfoService.createNewExchange(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExchange);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid request data: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error creating exchange info: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while creating exchange info");
        }
    }

    @GetMapping("/downloads/{studentId}")
    public List<ExchangeInfo> getDownloadedItems(@PathVariable String studentId) {
        return exchangeInfoService.getDownloadedItemsByStudent(studentId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllExchangeInfos() {
        try {
            logger.info("Received request to get all exchange info");
            List<ExchangeInfo> exchangeInfos = exchangeInfoService.getAllExchangeInfos();
            return ResponseEntity.ok(exchangeInfos);
        } catch (Exception e) {
            logger.error("Error getting all exchange info: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while getting exchange info");
        }
    }
}