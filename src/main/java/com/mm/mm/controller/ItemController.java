package com.mm.mm.controller;

import com.mm.mm.dto.ItemRequest.ItemCreationRequest;
import com.mm.mm.entity.Item;
import com.mm.mm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
