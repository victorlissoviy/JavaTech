package com.lisovyi.javatech.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
  @Id
  private String id;
  private String username;
  private String password; // Зберігати хешований пароль
  private String role; // Наприклад, ROLE_USER, ROLE_ADMIN
}
