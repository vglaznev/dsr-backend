package com.vglaznev.shorturlservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "url_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlAliasEntity {
    @Id
    private String shortUrlId;
    @Column(nullable = false)
    private String originalUrl;
}
