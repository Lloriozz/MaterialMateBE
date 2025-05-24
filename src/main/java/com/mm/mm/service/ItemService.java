package com.mm.mm.service;

import com.mm.mm.dto.ItemRequest.ItemCreationRequest;

//import com.mm.mm.dto.request.UserUpdateRequest;
import com.mm.mm.entity.Item;
import com.mm.mm.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item createItem(ItemCreationRequest request) {
        Item item = new Item();

        item.setTitle(request.getTitle());
        item.setCategory(request.getCategory());
        item.setDescription(request.getDescription());
        item.setUploadDate(request.getUploadDate());
        item.setApprovingStatus("approved");
        return itemRepository.save(item);
    }

    public Item getItem(Long itemID) {
        return itemRepository.findById(itemID)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // Get all items with approvingStatus == "approved"
    public List<Item> getApprovedItems() {
        return itemRepository.findByApprovingStatus("Approved");
    }

}