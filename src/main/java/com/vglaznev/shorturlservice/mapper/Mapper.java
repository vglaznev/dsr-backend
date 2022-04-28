package com.vglaznev.shorturlservice.mapper;

import com.vglaznev.shorturlservice.dto.UrlAliasDto;
import com.vglaznev.shorturlservice.entity.UrlAliasEntity;

public interface Mapper {
    UrlAliasDto urlAliasToDto(UrlAliasEntity urlAlias);

    UrlAliasEntity dtoToUrlAlias(UrlAliasDto urlAliasDto);
}
