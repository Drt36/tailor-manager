package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.*;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.mapper.UserMapper;
import com.internship.tailormanager.model.User;
import com.internship.tailormanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Data
@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserService implements UserDetails {

    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserService(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
        return Arrays.asList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserGetDto saveUser(UserPostDto userPostDto, MultipartFile file) throws IOException {

        UUID uuid = UUID.randomUUID();
        File saveFile = new ClassPathResource("static/userimage").getFile();
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator+ uuid + file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        User newUser = userMapper.dtoToModel(userPostDto);
        String generatedPassword = passwordService.generatePassword();
        newUser.setPassword(bCryptPasswordEncoder.encode(generatedPassword));
        newUser.setStatus(Status.ACTIVE);
        newUser.setImagePath(uuid + file.getOriginalFilename());

        User user = userRepository.save(newUser);

        //emailService.sendEmail(user.getEmail(), "Your Account has been created. your email or username: " + user.getEmail() +
        //       "  Password : " + generatedPassword + "  please change this password and Do not share with others.", "Account Created.");

        UserGetDto userGetDto = userMapper.modelToGetDto(user);
        userGetDto.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/userimage/").path(userGetDto.getImagePath()).toUriString());
        return userGetDto;
    }

    public UserGetDto getUser(String email) {
        User user = userRepository.getUserByEmailAndStatus(email, Status.ACTIVE);
        user.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/userimage/").path(user.getImagePath()).toUriString());
        return userMapper.modelToGetDto(user);
    }

    public Page<UserGetDto> getAllActiveUsers(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<User> users = userRepository.findUserByStatus(Status.ACTIVE, pageable);
        for (User user : users) {
            user.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/userimage/").path(user.getImagePath()).toUriString());
        }
        Page<UserGetDto> userGetDtos = users.map(user -> userMapper.modelToGetDto(user));
        return userGetDtos;
    }

    public UserGetDto updateUser(Long id, UserUpdateDto userUpdateDto, MultipartFile file) throws IOException {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (!file.isEmpty()) {
            File saveFile = new ClassPathResource("static/userimage").getFile();
            Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + user.getImagePath());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }

        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        user.setGender(userUpdateDto.getGender());
        user.setRole(userUpdateDto.getRole());
        user.setContact(userUpdateDto.getContact());
        user.setAge(userUpdateDto.getAge());
        User userSave = userRepository.save(user);
        UserGetDto userGetDto = userMapper.modelToGetDto(userSave);
        userGetDto.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/userimage/").path(userGetDto.getImagePath()).toUriString());
        return userGetDto;
    }

    public UserGetDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setStatus(Status.REMOVED);
        user.setImagePath(ServletUriComponentsBuilder.fromCurrentContextPath().path("/userimage/").path(user.getImagePath()).toUriString());
        return userMapper.modelToGetDto(userRepository.save(user));
    }

    public UserGetDto validateUser(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        User user = userRepository.getUserByEmailAndStatus(userPrincipal.getUsername(), Status.ACTIVE);
        return userMapper.modelToGetDto(user);
    }

    public String logoutUser() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "Logout Successfully.";
    }

    public boolean isEmailExist(String email) {
        return userRepository.existsUserByEmailAndStatus(email, Status.ACTIVE);
    }

    public void resetUserPassword(String email) {
        String newPassword = passwordService.generatePassword();
        userRepository.updateUserByEmail(email, bCryptPasswordEncoder.encode(newPassword));
        emailService.sendEmail(email, "Your new reseted password is : " + newPassword + " Do not share your credentials with others.", "Password Reseted Successfully.");
    }

    public String changeUserPassword(UserChangePasswordDto userChangePasswordDto) {
        User user = userRepository.getUserByEmail(userChangePasswordDto.getEmail());
        String response = null;
        System.out.println(userChangePasswordDto.getOldPassword());
        System.out.println(user.getPassword());
        if (bCryptPasswordEncoder.matches(userChangePasswordDto.getOldPassword(), user.getPassword())) {
            userRepository.updateUserByEmail(userChangePasswordDto.getEmail(), bCryptPasswordEncoder.encode(userChangePasswordDto.getPassword()));
            response = "Password Changed Successfully.";
        } else {
            response = "Old Password did not matched.";
        }
        return response;
    }

}
