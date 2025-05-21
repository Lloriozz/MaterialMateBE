package com.mm.mm.controller;

import com.mm.mm.dto.ItemCreationRequest;
//import com.mm.mm.dto.UserUpdateRequest;
import com.mm.mm.entity.Item;
import com.mm.mm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping
    Item createUser(@RequestBody ItemCreationRequest request){
        return itemService.createUser(request);
    }

//    @GetMapping
//    List<Item> getUsers(){
//        return itemService.getItems();
//    }

//    @GetMapping("/{userId}")
//    User getUser(@PathVariable("userId") String userId){
//        return userService.getUser(userId);
//    }

//    @PutMapping("/{userId}")
//    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
//        return userService.updateUser(userId, request);
//    }
//
//    @DeleteMapping("/{userId}")
//    String deleteUser(@PathVariable String userId){
//        userService.deleteUser(userId);
//        return "User has been deleted";
//    }

    @GetMapping("/approved")
    List<Item> getApprovedItems() {
        return itemService.getApprovedItems();
    }
}
