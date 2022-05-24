package com.vglaznev.shorturlservice.controller;

import com.vglaznev.shorturlservice.dto.RegisterRequest;
import com.vglaznev.shorturlservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User management")
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    @Operation(
            summary = "New user registration",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Create user successfully",
                            content = @Content()),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Username is already taken",
                            content = @Content())}
    )
    public ResponseEntity registerNewUser(@RequestBody RegisterRequest newUser) {
        boolean successful = userService.registerUser(newUser);
        return new ResponseEntity(successful ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
