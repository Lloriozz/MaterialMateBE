package com.mm.mm.service;

import com.mm.mm.dto.ItemCreationRequest;

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

    public Item createUser(ItemCreationRequest request){
        Item item = new Item();

        item.setTitle(request.getTitle());
        item.setCategory(request.getCategory());
        item.setDescription(request.getDescription());
        item.setUploadDate(request.getUploadDate());


        return itemRepository.save(item);
    }

//    public Item updateUser(String userId, UserUpdateRequest request) {
//        User user = getUser(userId);
//
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());
//
//        return userRepository.save(user);
//    }

//    public void deleteUser(String userId){
//        userRepository.deleteById(userId);
//    }
//
//    public List<User> getUsers(){
//        return userRepository.findAll();
//    }

    public Item getUser(String id){
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Get all items with approvingStatus == "approved"
    public List<Item> getApprovedItems() {
        return itemRepository.findByApprovingStatus("approved");
    }


}