package com.vglaznev.shorturlservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
public class UrlAliasDto {
    @JsonProperty()
    private String shortUrl;
    @JsonProperty()
    private String originalUrl;
}
