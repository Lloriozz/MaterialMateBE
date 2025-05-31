package com.mm.mm.controller;

import java.util.List;

import com.mm.mm.dto.ItemRequest.UpdateApprovingStatusRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mm.mm.dto.ItemRequest.ItemCreationRequest;
import com.mm.mm.entity.Item;
import com.mm.mm.service.ItemService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*") // Optional: only if you're working with frontend dev
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = { "multipart/form-data" })
    public Item createItem(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("uploaderID") String uploaderID,
            @RequestParam("pdfFile") MultipartFile pdfFile,
            @RequestParam("imageCover") MultipartFile imageCover) {
        try {
            logger.info("Received upload request - Title: {}, Category: {}, UploaderID: {}",
                    title, category, uploaderID);
            logger.info("PDF File: {} ({} bytes)",
                    pdfFile.getOriginalFilename(), pdfFile.getSize());
            logger.info("Image Cover: {} ({} bytes)",
                    imageCover.getOriginalFilename(), imageCover.getSize());

            ItemCreationRequest request = new ItemCreationRequest();
            request.setTitle(title);
            request.setDescription(description);
            request.setCategory(category);
            request.setUploaderID(uploaderID);
            request.setPdfFile(pdfFile);
            request.setImageCover(imageCover);

            Item createdItem = itemService.createItem(request);
            logger.info("Item created successfully with ID: {}", createdItem.getItemID());
            return createdItem;
        } catch (Exception e) {
            logger.error("Error creating item: ", e);
            throw new RuntimeException("Failed to create item: " + e.getMessage(), e);
        }
    }

    @GetMapping("/{itemID}")
    public Item getItemById(@PathVariable("itemID") String id) {
        return itemService.getItem(id);
    }

    @GetMapping("/approved")
    public List<Item> getApprovedItems() {
        return itemService.getApprovedItems();
    }

    @GetMapping("/student/{uploaderID}")
    public List<Item> getItemsByUploaderId(@PathVariable("uploaderID") String uploaderID) {
        return itemService.getItemsByUploaderId(uploaderID);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllItems() {
        try {
            logger.info("Received request to get all items");
            List<Item> items = itemService.getAllItems();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            logger.error("Error getting all items: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while getting items");
        }
    }

    @PutMapping("/{itemID}/status")
    public ResponseEntity<?> updateApprovingStatus(
            @PathVariable("itemID") String itemID,
            @RequestBody UpdateApprovingStatusRequest request) {
        try {
            Item updatedItem = itemService.updateApprovingStatus(itemID, request);
            return ResponseEntity.ok(updatedItem);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid request to update approving status: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        } catch (RuntimeException e) {
            logger.error("Error updating approving status: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error updating approving status: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while updating approving status");
        }
    }
}
