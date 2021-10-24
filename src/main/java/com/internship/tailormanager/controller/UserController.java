package com.internship.tailormanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.tailormanager.dto.*;
import com.internship.tailormanager.service.OtpCodeService;
import com.internship.tailormanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private OtpCodeService otpCodeService;

    @PostMapping(value = "/api/admin/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserGetDto> saveUser(@RequestParam("user") String user, @RequestParam("file") MultipartFile file) throws IOException {
        UserPostDto userPostDto = new ObjectMapper().readValue(user, UserPostDto.class);
        UserGetDto userGetDto = userService.saveUser(userPostDto, file);
        return new ResponseEntity<UserGetDto>(userGetDto, HttpStatus.OK);
    }

    @GetMapping("/api/admin/users/{email}")
    public UserGetDto getUser(@NotNull @PathVariable("email") String email) {
        return userService.getUser(email);
    }

    @GetMapping("/api/admin/users")
    public Page<UserGetDto> getAllUsers(@RequestParam("page") int page) {
        Page<UserGetDto> users = userService.getAllActiveUsers(page);
        return users;
    }

    @Transactional
    @PutMapping("/api/both/updateprofile/{id}")
    public ResponseEntity<UserGetDto> updateUser(@NotNull @PathVariable("id") Long id, String user, @NotNull @RequestParam("file") MultipartFile file) throws IOException {
        UserUpdateDto userUpdateDto = new ObjectMapper().readValue(user, UserUpdateDto.class);
        UserGetDto userGetDto = userService.updateUser(id, userUpdateDto, file);
        return new ResponseEntity<UserGetDto>(userGetDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/admin/users/delete/{id}")
    public ResponseEntity<UserGetDto> deleteUser(@NotNull @PathVariable("id") Long id) {
        UserGetDto userGetDto = userService.deleteUser(id);
        return new ResponseEntity<UserGetDto>(userGetDto, HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity<UserGetDto> authenticateUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        UserGetDto userGetDto = userService.validateUser(userLoginDto);
        return new ResponseEntity<UserGetDto>(userGetDto, HttpStatus.OK);
    }

    @PostMapping("/api/both/logout")
    public String logoutUser() {
        return userService.logoutUser();
    }

    //email form
    @GetMapping("/api/resetpassword/{email}")
    public boolean isEmailExist(@NotNull @PathVariable("email") String email) {
        return userService.isEmailExist(email);
    }

    //if email exist  push this url
    @PostMapping("/api/resetpassword/{email}")
    public OtpCodeGetDto saveOtpCode(@NotNull @PathVariable("email") String email) {
        return otpCodeService.saveOtpCode(email);
    }

    //if otp saved display otp form after click submit ( push this url)
    @Transactional
    @PostMapping("/api/resetpassword/validate/{email}")
    public String validateOtpCode(@NotNull @PathVariable("email") String email, @Valid @RequestBody UserResetPasswordDto userResetPasswordDto) {
        return otpCodeService.validateOtpCode(email, userResetPasswordDto.getOtpCode());
    }

    @Transactional
    @PostMapping("/api/both/changepassword")
    public String changePassword(@Valid @RequestBody UserChangePasswordDto userChangePasswordDto) {
        return userService.changeUserPassword(userChangePasswordDto);
    }

}
