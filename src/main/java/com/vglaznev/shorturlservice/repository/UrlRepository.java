package com.vglaznev.shorturlservice.repository;

import com.vglaznev.shorturlservice.entity.UrlAliasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlAliasEntity, String> {
    Optional<UrlAliasEntity> findByOriginalUrl(String originalUrl);
}