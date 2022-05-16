package com.vglaznev.shorturlservice.mapper;

import com.vglaznev.shorturlservice.dto.UrlAliasDto;
import com.vglaznev.shorturlservice.entity.UrlAliasEntity;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper {

    @Override
    public UrlAliasDto urlAliasToDto(UrlAliasEntity urlAlias) {
        if (urlAlias == null) {
            return null;
        }

        return UrlAliasDto.builder()
                .shortUrlId(urlAlias.getShortUrlId())
                .originalUrl(urlAlias.getOriginalUrl())
                .build();
    }

}
