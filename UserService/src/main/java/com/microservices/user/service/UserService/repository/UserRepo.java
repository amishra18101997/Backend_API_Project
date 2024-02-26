package com.microservices.user.service.UserService.repository;

import com.microservices.user.service.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {


}
