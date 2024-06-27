package com.nicolascruz.push.domain.services.impl;

import com.nicolascruz.push.domain.exceptions.DataIntegrityException;
import com.nicolascruz.push.domain.model.User;
import com.nicolascruz.push.domain.repositories.UserRepository;
import com.nicolascruz.push.domain.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DataIntegrityException(STR. "User with email \{ user.getEmail() } already exists" );
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
