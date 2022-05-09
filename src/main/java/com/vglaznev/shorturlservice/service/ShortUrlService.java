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
    private final UrlRepository repository;
    private final MapperImpl mapper;

    public Optional<UrlAliasDto> getUrlAlias(String shortUrlId) {
        return repository.findById(shortUrlId).map(mapper::urlAliasToDto);
    }

    public UrlAliasDto create(String originalUrl) {
        //If original url already has a short representation, just return short url
        Optional<UrlAliasEntity> entity = repository.findByOriginalUrl(originalUrl);
        if (entity.isPresent()) {
            return mapper.urlAliasToDto(entity.get());
        }

        String shortUrlId;
        do {
            shortUrlId = UniqueStringGenerator.generate();
            //In case of collision, generate another short url
        } while (repository.existsById(shortUrlId));

        return mapper.urlAliasToDto(repository.save(new UrlAliasEntity(shortUrlId, originalUrl)));
    }
}
