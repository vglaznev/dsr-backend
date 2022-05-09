package com.vglaznev.shorturlservice.controller;

import com.vglaznev.shorturlservice.dto.UrlAliasDto;
import com.vglaznev.shorturlservice.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping({"/api/v1/short-url", ""})
@RequiredArgsConstructor
public class ShortUrlController {
    private final ShortUrlService urlService;

    //TODO: move endpoint to root
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

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> createShortUrl(@RequestBody String originalUrl) {
        UrlAliasDto shortUrl = urlService.create(originalUrl);
        return ResponseEntity.ok(shortUrl);
    }
}
