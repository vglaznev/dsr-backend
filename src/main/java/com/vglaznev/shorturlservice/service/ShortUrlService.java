package com.vglaznev.shorturlservice.service;

import com.vglaznev.shorturlservice.dto.UrlAliasDto;
import com.vglaznev.shorturlservice.entity.UrlAliasEntity;
import com.vglaznev.shorturlservice.generator.UniqueStringGenerator;
import com.vglaznev.shorturlservice.mapper.MapperImpl;
import com.vglaznev.shorturlservice.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShortUrlService {
    private final UrlRepository<UrlAliasEntity> repository;
    private final MapperImpl mapper;

    public Optional<UrlAliasDto> getUrlAlias(String shortUrl) {
        UrlAliasDto urlDto = mapper.urlAliasToDto(repository.findOne(shortUrl));
        return Optional.ofNullable(urlDto);
    }

    public UrlAliasDto create(String originalUrl) {
        //If original url already has a short representation, just return short url
        Optional<UrlAliasEntity> entity = Optional.ofNullable(repository.findByOriginalUrl(originalUrl));
        if (entity.isPresent()) {
            return mapper.urlAliasToDto(entity.get());
        }

        String shortUrl;
        do {
            shortUrl = UniqueStringGenerator.generate();
            //In case of collision, generate another short url
        } while (repository.exists(shortUrl));

        return mapper.urlAliasToDto(repository.save(new UrlAliasEntity(shortUrl, originalUrl)));
    }
}
