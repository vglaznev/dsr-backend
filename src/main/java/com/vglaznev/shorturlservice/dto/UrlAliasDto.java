package com.vglaznev.shorturlservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class UrlAliasDto {
    private String shortUrlId;
    private String originalUrl;
}
