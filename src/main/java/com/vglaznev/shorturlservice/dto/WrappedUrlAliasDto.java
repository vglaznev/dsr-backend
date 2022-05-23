package com.vglaznev.shorturlservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class WrappedUrlAliasDto {
    private final UrlAliasDto urlAliasDto;
    private final String serviceUrl;

    @JsonProperty
    public String getShortUrl() {
        return serviceUrl + "/" + urlAliasDto.getShortUrlId();
    }

    @JsonProperty
    public String getOriginalUrl() {
        return urlAliasDto.getOriginalUrl();
    }

    @Component
    public static class ShortUrlWrapper {
        private final Supplier<String> currentLocation;

        public ShortUrlWrapper(Supplier<String> currentLocation) {
            this.currentLocation = currentLocation;
        }

        public WrappedUrlAliasDto wrap(UrlAliasDto urlAliasDto) {
            return new WrappedUrlAliasDto(urlAliasDto, currentLocation.get());
        }
    }
}
