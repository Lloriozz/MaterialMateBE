package com.mm.mm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.mm.dto.ItemRequest.ItemCreationRequest;
import com.mm.mm.entity.Item;
import com.mm.mm.service.ItemService;

@RestController
@RequestMapping("/items")
@CrossOrigin(origins = "*") // Optional: only if you're working with frontend dev
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping()
    public Item createItem(@RequestBody ItemCreationRequest request) {
        return itemService.createItem(request);
    }

    @GetMapping("/{itemID}")
    public Item getItemById(@PathVariable("itemID") Long id) {
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
}
