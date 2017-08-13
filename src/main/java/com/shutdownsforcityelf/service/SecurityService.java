package com.shutdownsforcityelf.service;

import com.shutdownsforcityelf.model.User;
import com.shutdownsforcityelf.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

  @Autowired
  UserRepository userRepository;

  public User getUserFromSession() {
    return userRepository
        .findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
  }
}