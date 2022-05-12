package com.vglaznev.shorturlservice.controller;

import com.vglaznev.shorturlservice.dto.UrlAliasDto;
import com.vglaznev.shorturlservice.dto.WrappedUrlAliasDto.ShortUrlWrapper;
import com.vglaznev.shorturlservice.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/short-url")
@RequiredArgsConstructor
public class ShortUrlController {
    private final ShortUrlService urlService;
    private final ShortUrlWrapper shortUrlWrapper;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> createShortUrl(@RequestBody String originalUrl) {
        UrlAliasDto urlAlias = urlService.create(originalUrl);

        return ResponseEntity.ok(shortUrlWrapper.wrap(urlAlias));
    }
}
