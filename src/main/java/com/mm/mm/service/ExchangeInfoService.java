package com.mm.mm.service;

import com.mm.mm.entity.ExchangeInfo;

import com.mm.mm.entity.Item;
import com.mm.mm.entity.Student;
import com.mm.mm.repository.ExchangeInfoRepository;
import com.mm.mm.repository.ItemRepository;
import com.mm.mm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExchangeInfoService {
    private static final Logger logger = LoggerFactory.getLogger(ExchangeInfoService.class);

    @Autowired
    private ExchangeInfoRepository exchangeInfoRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ItemRepository itemRepository;

    public ExchangeInfo createExchange(String studentId, String itemId) {
        // Fetch the Student and Item entities from the database
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + studentId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item ID: " + itemId));

        // Create and save the ExchangeInfo entity
        ExchangeInfo exchangeInfo = new ExchangeInfo();
        exchangeInfo.setDate(LocalDate.now());
        exchangeInfo.setType("download");
        exchangeInfo.setStudent(student);
        exchangeInfo.setItem(item);

        return exchangeInfoRepository.save(exchangeInfo);
    }

    public List<ExchangeInfo> getDownloadedItemsByStudent(String studentId) {
        return exchangeInfoRepository.findByStudent_StudentIDAndType(studentId, "download");
    }

    public List<ExchangeInfo> getAllExchangeInfos() {
        logger.info("Getting all exchange info records");
        return exchangeInfoRepository.findAll();
    }

    // You can add other business logic methods here
}
