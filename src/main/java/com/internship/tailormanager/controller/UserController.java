package com.internship.tailormanager.controller;

import com.internship.tailormanager.dto.LoginDto;
import com.internship.tailormanager.dto.UserDto;
import com.internship.tailormanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/admin/register")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto) {
        UserDto userDtoSave = userService.save(userDto);
        return new ResponseEntity<UserDto>(userDtoSave, HttpStatus.CREATED);
    }

    @PostMapping("/api/login")
    public String authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        return userService.validateUser(loginDto);
    }

    @PostMapping("/api/logout")
    public String logoutUser() {
        return userService.logoutUser();
    }
}
