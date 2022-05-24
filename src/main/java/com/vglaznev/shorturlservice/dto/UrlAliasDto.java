package com.vglaznev.shorturlservice.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class UrlAliasDto {
    private final String shortUrlId;
    private final String originalUrl;
}
