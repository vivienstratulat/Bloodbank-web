package com.example.bloodPage.service;

import com.example.bloodPage.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import javax.security.auth.login.CredentialException;
import java.util.List;
import java.util.UUID;


public interface UserService {
    User registerUser(String email, String password, String firstName, String lastName,String role);
    List<User> findAll();
    User findByEmail(String email) throws AuthenticationException;
    User loginUser(String email, String password) throws AuthenticationException, CredentialException;

    @Transactional
    User updateUser(String email,User newUser);
    String userRole(User user);
}
