package com.vglaznev.shorturlservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Getter
@Schema(description = "Data for registration")
public class RegisterRequest {
    @Schema(description = "Username")
    private final String username;
    @Schema(description = "User password")
    private final String password;
}
