package com.vglaznev.shorturlservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Schema(description = "Data for registration")
public class RegisterRequest {
    @Schema(description = "User name")
    private final String username;
    @Schema(description = "User password")
    private final String password;
}
