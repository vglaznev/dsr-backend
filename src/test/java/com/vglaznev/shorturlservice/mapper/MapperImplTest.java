package com.vglaznev.shorturlservice.mapper;

import com.vglaznev.shorturlservice.entity.UrlAliasEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MapperImplTest {
    private MapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new MapperImpl();
    }

    @Test
    void mapUrlAliasToDto() {
        String shortUrlId = "3P4vOBBa";
        String originalUrl = "https://google.com";

        var urlAlias = new UrlAliasEntity(shortUrlId, originalUrl);
        var urlAliasDto = mapper.urlAliasToDto(urlAlias);

        assertEquals(originalUrl, urlAliasDto.getOriginalUrl());
        assertEquals("localhost:8080/api/v1/short-url" + shortUrlId, urlAliasDto.getShortUrl());
    }

    @Test
    void onNullTest() {
        assertNull(mapper.urlAliasToDto(null));
    }
}