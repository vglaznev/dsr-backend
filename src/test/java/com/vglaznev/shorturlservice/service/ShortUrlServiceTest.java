package com.vglaznev.shorturlservice.service;

import com.vglaznev.shorturlservice.entity.UrlAliasEntity;
import com.vglaznev.shorturlservice.mapper.MapperImpl;
import com.vglaznev.shorturlservice.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShortUrlServiceTest {

    @Mock
    private UrlRepository urlRepository;
    @Mock
    private MapperImpl mapper;
    @InjectMocks
    private ShortUrlService testService;

    @Test
    void isSameShortUrlReturnedOnSameRequest() {
        //given
        String longUrl = "https://google.com";
        //when
        when(urlRepository.save(any(UrlAliasEntity.class)))
                .then(returnsFirstArg());
        //can be replaced with mapper instance
        when(mapper.urlAliasToDto(any(UrlAliasEntity.class)))
                .thenCallRealMethod();

        var firstRequestedDto = testService.create(longUrl);

        //then
        var urlCaptor = ArgumentCaptor.forClass(UrlAliasEntity.class);
        verify(urlRepository).save(urlCaptor.capture());

        //when
        when(urlRepository.findByOriginalUrl(longUrl)).
                thenReturn(Optional.of(urlCaptor.getValue()));

        var secondRequestedDto = testService.create(longUrl);

        assertEquals(firstRequestedDto, secondRequestedDto);
    }

}