package com.mm.mm.service;

import com.mm.mm.dto.ItemRequest.ItemCreationRequest;
import com.mm.mm.dto.ItemRequest.UpdateApprovingStatusRequest;

//import com.mm.mm.dto.request.UserUpdateRequest;
import com.mm.mm.entity.Item;
import com.mm.mm.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ItemService {
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(ItemCreationRequest request) {
        try {
            logger.info("Creating new item with title: {}", request.getTitle());

            Item item = new Item();
            item.setTitle(request.getTitle());
            item.setCategory(request.getCategory());
            item.setDescription(request.getDescription());
            item.setUploaderID(request.getUploaderID());
            item.setApprovingStatus("Pending");

            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            item.setUploadDate(today);

            logger.info("Processing PDF file: {}",
                    request.getPdfFile() != null ? request.getPdfFile().getOriginalFilename() : "null");
            if (request.getPdfFile() != null && !request.getPdfFile().isEmpty()) {
                item.setFileData(request.getPdfFile().getBytes());
            }

            logger.info("Processing image cover: {}",
                    request.getImageCover() != null ? request.getImageCover().getOriginalFilename() : "null");
            if (request.getImageCover() != null && !request.getImageCover().isEmpty()) {
                item.setCoverImage(request.getImageCover().getBytes());
            }

            logger.info("Saving item to database");
            Item savedItem = itemRepository.save(item);
            logger.info("Item saved successfully with ID: {}", savedItem.getItemID());

            return savedItem;
        } catch (Exception e) {
            logger.error("Error creating item: ", e);
            throw new RuntimeException("Failed to create item: " + e.getMessage(), e);
        }
    }

    public Item getItem(String itemID) {
        return itemRepository.findById(itemID)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // Get all items with approvingStatus == "approved"
    public List<Item> getApprovedItems() {
        return itemRepository.findByApprovingStatus("Approved");
    }

    // Get all items by uploaderID (studentID)
    public List<Item> getItemsByUploaderId(String uploaderID) {
        return itemRepository.findByUploaderID(uploaderID);
    }

    public List<Item> getAllItems() {
        logger.info("Getting all items");
        return itemRepository.findAll();
    }

    public Item updateApprovingStatus(String itemID, UpdateApprovingStatusRequest request) {
        logger.info("Updating approving status for item: {} to {}", itemID, request.getApprovingStatus());

        // Validate input
        if (request.getApprovingStatus() == null || request.getApprovingStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Approving status cannot be empty");
        }

        // Validate approving status value
        String status = request.getApprovingStatus().trim();
        if (!status.equals("Approved") && !status.equals("Rejected") && !status.equals("Pending")) {
            throw new IllegalArgumentException("Invalid approving status. Must be one of: Approved, Rejected, Pending");
        }

        // Find and update item
        Item item = itemRepository.findById(itemID)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + itemID));

        // Check if item is already in the requested status
        if (item.getApprovingStatus().equals(status)) {
            logger.info("Item {} is already in status: {}", itemID, status);
            return item;
        }

        // Update item status
        item.setApprovingStatus(status);

        // Save and return updated item
        Item updatedItem = itemRepository.save(item);
        logger.info("Successfully updated item {} approving status to {}", itemID, status);
        return updatedItem;
    }
}