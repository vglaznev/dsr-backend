package com.vglaznev.shorturlservice.mapper;

import com.vglaznev.shorturlservice.dto.UrlAliasDto;
import com.vglaznev.shorturlservice.entity.UrlAliasEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper {

    private String convertIdToUrl(String urlId) {
        //TODO: replace url prefix with environment variables(host, port, endpoint)
        return "localhost:8080/" + urlId;
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

}
