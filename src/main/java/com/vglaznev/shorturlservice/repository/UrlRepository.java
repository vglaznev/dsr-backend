package com.vglaznev.shorturlservice.repository;

import com.vglaznev.shorturlservice.entity.UrlAliasEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//TODO: replace with db
@Component
public class UrlRepository<T extends UrlAliasEntity> {
    private final Map<String, T> data;

    public UrlRepository() {
        this.data = new HashMap<>();
    }

    public Optional<T> findOne(String id) {
        return Optional.ofNullable(data.get(id));
    }

    public T save(T entity) {
        data.put(entity.getShortUrlId(), entity);
        return entity;
    }

    public boolean exists(String id) {
        return data.containsKey(id);
    }

    public Optional<T> findByOriginalUrl(String originalUrl) {
        for (Map.Entry<String, T> entry : data.entrySet()) {
            if (originalUrl.equals(entry.getValue().getOriginalUrl())) {
                return Optional.ofNullable(entry.getValue());
            }
        }
        return Optional.empty();
    }
}
