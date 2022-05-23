package com.vglaznev.shorturlservice.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class RegisterRequest {
    private final String username;
    private final String password;
}
