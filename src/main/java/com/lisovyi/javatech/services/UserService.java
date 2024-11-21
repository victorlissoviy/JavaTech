package com.lisovyi.javatech.services;

import com.lisovyi.javatech.models.User;
import com.lisovyi.javatech.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User registerUser(User user) {
    if (userRepository.findByUsername(user.getUsername()) != null) {
      throw new IllegalArgumentException("Username is already taken.");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword())); // Хешування пароля
    user.setRole("ROLE_USER");
    return userRepository.save(user);
  }
}


