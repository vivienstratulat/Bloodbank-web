package com.example.bloodPage.service.impl;

import com.example.bloodPage.entity.User;
import com.example.bloodPage.repository.UserRepository;
import com.example.bloodPage.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import javax.security.auth.login.CredentialException;
import java.beans.Encoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
   final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

        @Override
        public User registerUser(String email, String password, String firstName, String lastName,String role) {
        if(userRepository.existsByEmail(email))
            throw new RuntimeException("This email is already taken");

            User u=new User();
            u.setEmail(email);
            u.setPassword(password);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setRole(role);
           return userRepository.save(u);
        }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email)  {
        Optional<User> foundUser= userRepository.findByEmail(email);
        if(foundUser.isPresent())
            return foundUser.get();
        else
            return null;
    }





    @Override
    public User loginUser(String email, String password) throws CredentialException {
        Optional<User> u=userRepository.findByEmailAndPassword(email,password);
        if(u.isPresent())
            return u.get();
        else
            throw new CredentialException("Wrong credentials");
    }


    @Override
    @Transactional
    public User updateUser(String email,User newUser){
        User user = userRepository.findByEmail(
                email).get();

        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setRole(newUser.getRole());
        return userRepository.save(user);

    }

    @Override
    public String userRole(User user) {
        if(user.getRole().equals("ADMIN"))
            return "ADMIN";
        else if(user.getRole().equals("DOCTOR"))
            return "DOCTOR";
        else if(user.getRole().equals("DONOR"))
            return "DONOR";
        else
            return "USER";
    }

}
