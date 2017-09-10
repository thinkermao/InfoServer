package net.hashcoding.code.scuinfo.service;

import net.hashcoding.code.scuinfo.domain.entity.ArticleType;

import java.util.List;

public interface ArticleTypeService {
    List<ArticleType> list();
    void insert(String name);
}
