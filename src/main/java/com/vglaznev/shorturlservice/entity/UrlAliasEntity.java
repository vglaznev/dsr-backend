package com.vglaznev.shorturlservice.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "url_table")
@Getter
@Setter
@ToString //for debug
@NoArgsConstructor
@AllArgsConstructor
public class UrlAliasEntity {
    @Id
    private String shortUrlId;

    @Column(nullable = false)
    private String originalUrl;

    @Override
    public int hashCode() {
        return getShortUrlId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UrlAliasEntity other = (UrlAliasEntity) obj;
        return Objects.equals(shortUrlId, other.getShortUrlId());
    }


}
