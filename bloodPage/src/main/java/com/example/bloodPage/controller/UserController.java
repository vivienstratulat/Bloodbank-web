package com.example.bloodPage.controller;

import com.example.bloodPage.dto.UserDTO;
import com.example.bloodPage.entity.User;
import com.example.bloodPage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.security.auth.login.CredentialException;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

        @PostMapping("/register")
        ResponseEntity<User> registerUser(@RequestBody User user){
           User registeredUser= userService.registerUser(user.email, user.password, user.firstName, user.lastName,user.role);
            return ResponseEntity.ok(registeredUser);
        }

        @GetMapping("/allUsers")
        ResponseEntity<List<User>> getAll(){
            List<User> allUsers = userService.findAll();
            return ResponseEntity.ok(allUsers);
        }

        @PostMapping("/login")
        ResponseEntity<User> loginUser(@RequestBody User user)  throws CredentialException, AuthenticationException {
            System.out.println(user.email+" "+user.password);
            User foundUser = userService.loginUser(user.email,user.password);

            if (foundUser != null)
                return ResponseEntity.ok(foundUser);
            else
                return (ResponseEntity<User>) ResponseEntity.notFound();
        }

        @GetMapping("/{email}")
        ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) throws AuthenticationException {
            User foundUser = userService.findByEmail(email);
            if (foundUser != null)
                return ResponseEntity.ok(foundUser);
            else
                throw new AuthenticationException("User not found");
        }

        @PostMapping("/update/{email}")
        ResponseEntity<User> updateUser(@PathVariable String email,@RequestBody User user){
            User updatedUser = userService.updateUser(email,user);
            if (updatedUser != null)
                return ResponseEntity.ok(updatedUser);
            else
                return (ResponseEntity<User>) ResponseEntity.notFound();
        }

        @GetMapping("/home")
        String Home(){
        return "hello";
        }


}
