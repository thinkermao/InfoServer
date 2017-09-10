package net.hashcoding.code.scuinfo.service.impl;

import net.hashcoding.code.scuinfo.domain.entity.ArticleType;
import net.hashcoding.code.scuinfo.domain.mapper.ArticleTypeMapper;
import net.hashcoding.code.scuinfo.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

    @Autowired
    private ArticleTypeMapper mapper;

    @Override
    public List<ArticleType> list() {
        return mapper.list();
    }

    @Override
    public void insert(String name) {
        ArticleType type = new ArticleType();
        type.setName(name);
        mapper.insert(type);
    }
}
