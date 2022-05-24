package com.vglaznev.shorturlservice.controller;

import com.vglaznev.shorturlservice.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Redirect to original url",
            responses = {
                    @ApiResponse(
                            responseCode = "302",
                            description = "Find successfully",
                            content = @Content()),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Short url not exist",
                            content = @Content())}
    )
    public ResponseEntity<?> redirect(@PathVariable String shortUrlId) {
        return urlService.getUrlAlias(shortUrlId)
                .map(url ->
                        ResponseEntity
                                .status(HttpStatus.FOUND)
                                .location(URI.create(url.getOriginalUrl()))
                                .build())
                .orElse(ResponseEntity.notFound().build());
    }
}
