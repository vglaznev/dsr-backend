package com.vglaznev.shorturlservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlAliasDto {
    @JsonProperty()
    private String shortUrl;
    @JsonProperty()
    private String originalUrl;
}
