package com.book.libary.controller;

import com.book.libary.model.request.CreateUserRequest;
import com.book.libary.model.request.LoginRequest;
import com.book.libary.model.response.LoginResponse;
import com.book.libary.model.response.UserResponse;
import com.book.libary.security.AllowAll;
import com.book.libary.security.Authenticated;
import com.book.libary.security.SecurityContext;
import com.book.libary.service.UserService;
import com.book.libary.util.Constants;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Transactional
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final SecurityContext securityContext;


    @PostMapping("/login")
    @AllowAll
    @Transactional
    public LoginResponse login(@Valid final @RequestBody LoginRequest loginRequest){
        return userService.loginResponseByCredentialsOrFail(loginRequest.getEmail(), loginRequest.getEncodePasword());
    }

    @PostMapping("/register")
    @AllowAll
    @Transactional
    public UserResponse createUser(@Valid final @RequestBody CreateUserRequest createUserRequest){

        return userService.createUser(createUserRequest);
    }

    @GetMapping("/current_user")
    @Authenticated
    public UserResponse getCurrentUser() {
        return new UserResponse(securityContext.getCurrentUser());
    }

}
