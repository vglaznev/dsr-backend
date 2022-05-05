package com.vglaznev.shorturlservice.mapper;

import com.vglaznev.shorturlservice.dto.UrlAliasDto;
import com.vglaznev.shorturlservice.entity.UrlAliasEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper {

    //TODO: use interface for urlEntity and ShortUlDto and replace these methods with one method

    private String convertIdToUrl(String urlId) {
        //TODO: replace url prefix with environment variables(host, port, endpoint)
        return "localhost:8080/api/v1/short-url/" + urlId;
    }

    @Override
    public UrlAliasDto urlAliasToDto(UrlAliasEntity urlAlias) {
        if (urlAlias == null) {
            return null;
        }
        UrlAliasDto urlAliasDto = new UrlAliasDto();
        urlAliasDto.setShortUrl(convertIdToUrl(urlAlias.getShortUrlId()));
        urlAliasDto.setOriginalUrl(urlAlias.getOriginalUrl());
        return urlAliasDto;
    }

    @Override
    public UrlAliasEntity dtoToUrlAlias(UrlAliasDto urlAliasDto) {
        if (urlAliasDto == null) {
            return null;
        }
        UrlAliasEntity urlAlias = new UrlAliasEntity();
        urlAlias.setShortUrlId(urlAliasDto.getShortUrl());
        urlAlias.setOriginalUrl(urlAliasDto.getOriginalUrl());
        return urlAlias;
    }
}