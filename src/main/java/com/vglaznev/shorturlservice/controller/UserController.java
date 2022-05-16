package com.vglaznev.shorturlservice.controller;

import com.vglaznev.shorturlservice.dto.RegisterRequest;
import com.vglaznev.shorturlservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity registerNewUser(@RequestBody RegisterRequest newUser) {
        userService.registerUser(newUser);
        return new ResponseEntity(HttpStatus.OK);
    }
}
