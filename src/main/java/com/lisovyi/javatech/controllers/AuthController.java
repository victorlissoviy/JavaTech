package com.lisovyi.javatech.controllers;

import com.lisovyi.javatech.components.JwtTokenProvider;
import com.lisovyi.javatech.converters.UserConverter;
import com.lisovyi.javatech.dto.UserDTO;
import com.lisovyi.javatech.models.JwtResponse;
import com.lisovyi.javatech.models.LoginRequest;
import com.lisovyi.javatech.models.User;
import com.lisovyi.javatech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;
  private final UserService userService;

  @Autowired
  public AuthController(UserService userService,
                        AuthenticationManager authenticationManager,
                        JwtTokenProvider jwtTokenProvider) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  /**
   * Register new user in system.
   *
   * @param user new user
   * @return new user data.
   */
  @PostMapping("/register")
  public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user) {
    User registeredUser = userService.registerUser(UserConverter.toModel(user));
    return ResponseEntity.ok(UserConverter.toDTO(registeredUser));
  }

  /**
   * Authorize exist user in system.
   *
   * @param loginRequest data for Login
   * @return token for user
   */
  @PostMapping("/login")
  public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtTokenProvider.generateToken(authentication);

    return ResponseEntity.ok(new JwtResponse(token));
  }

  @ExceptionHandler
  public ResponseEntity<String> handleException(IllegalArgumentException exception) {
    return ResponseEntity.badRequest().body(exception.getMessage());
  }
}

