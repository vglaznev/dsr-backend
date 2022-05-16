package com.vglaznev.shorturlservice.controller;

import com.vglaznev.shorturlservice.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private final ShortUrlService urlService;

    @GetMapping("/{shortUrlId}")
    public ResponseEntity<?> redirect(@PathVariable String shortUrlId) {
        return urlService.getUrlAlias(shortUrlId)
                .map(url ->
                        ResponseEntity
                                .status(HttpStatus.FOUND)
                                .location(URI.create(url.getOriginalUrl()))
                                .build())
                .orElseGet(() ->
                        ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                );
    }
}
