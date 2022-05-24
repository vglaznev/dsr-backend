package com.vglaznev.shorturlservice.controller;

import com.vglaznev.shorturlservice.dto.UrlAliasDto;
import com.vglaznev.shorturlservice.dto.WrappedUrlAliasDto;
import com.vglaznev.shorturlservice.dto.WrappedUrlAliasDto.ShortUrlWrapper;
import com.vglaznev.shorturlservice.service.ShortUrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/short-url")
@RequiredArgsConstructor
@Tag(name = "Operation with urls")
public class ShortUrlController {
    private final ShortUrlService urlService;
    private final ShortUrlWrapper shortUrlWrapper;
    private final UrlValidator urlValidator;

    @PostMapping(value = "/create")
    @ResponseBody
    @Operation(
            summary = "Create short URL",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Create short url successfully",
                            content = @Content(
                                    schema = @Schema(implementation = WrappedUrlAliasDto.class),
                                    mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Url is not correct",
                            content = @Content(
                                    schema = @Schema()))
            })
    public ResponseEntity<?> createShortUrl(@RequestBody String originalUrl) {
        if (!urlValidator.isValid(originalUrl)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        UrlAliasDto urlAlias = urlService.create(originalUrl);
        return ResponseEntity.ok(shortUrlWrapper.wrap(urlAlias));
    }
}
