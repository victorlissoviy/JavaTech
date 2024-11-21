package com.lisovyi.javatech.converters;

import com.lisovyi.javatech.dto.UserDTO;
import com.lisovyi.javatech.models.User;

public class UserConverter {
  private UserConverter() {  }

  public static User toModel(UserDTO userDTO) {
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(userDTO.getPassword());
    user.setRole(userDTO.getRole());

    return user;
  }

  public static UserDTO toDTO(User user) {
    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setUsername(user.getUsername());
    userDTO.setRole(user.getRole());
    return userDTO;
  }
}
