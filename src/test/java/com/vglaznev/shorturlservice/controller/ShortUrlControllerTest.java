package com.vglaznev.shorturlservice.controller;

import com.vglaznev.shorturlservice.dto.UrlAliasDto;
import com.vglaznev.shorturlservice.service.ShortUrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ShortUrlControllerTest {
    public static final String API_PREFIX = "/api/v1/short-url/";
    public static final String TEST_SHORT_URL_ID = "3P4vOBBa";
    private MockMvc mvc;
    @Mock
    private ShortUrlService urlService;
    @InjectMocks
    private ShortUrlController urlController;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(urlController)
                .build();
    }

    @Test
    void tryRedirectIfFound() throws Exception {
        String longUrl = "https://google.com";

        when(urlService.getUrlAlias(TEST_SHORT_URL_ID))
                .thenReturn(Optional.of(new UrlAliasDto(API_PREFIX + TEST_SHORT_URL_ID, longUrl)));

        mvc.perform(get(API_PREFIX + TEST_SHORT_URL_ID))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(longUrl));
    }

    @Test
    void tryRedirectIfNotFound() throws Exception {
        when(urlService.getUrlAlias(TEST_SHORT_URL_ID))
                .thenReturn(Optional.empty());

        mvc.perform(get(API_PREFIX + TEST_SHORT_URL_ID))
                .andExpect(status().isNotFound());
    }
}