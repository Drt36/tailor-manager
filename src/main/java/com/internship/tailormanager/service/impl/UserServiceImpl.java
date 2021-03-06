package com.internship.tailormanager.service.impl;

import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.model.User;
import com.internship.tailormanager.repository.UserRepository;
import com.internship.tailormanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmailAndStatus(email,Status.ACTIVE);
        if (user == null) {
            throw new UsernameNotFoundException("Could not found user");
        }
        return new UserService(user);
    }
}
