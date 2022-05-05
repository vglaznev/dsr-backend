package com.vglaznev.shorturlservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlAliasEntity {
    private String shortUrlId;
    private String originalUrl;
}
