package com.eraasoft.spring.controller;

import com.eraasoft.spring.dto.UserDto;
import com.eraasoft.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save-user")
    ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PutMapping("/update-user")
    ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @DeleteMapping("/delete-user/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
