package net.hashcoding.code.scuinfo.domain.mapper;

import net.hashcoding.code.scuinfo.domain.entity.ArticleType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleTypeMapper {
    void insert(ArticleType type);
    ArticleType selectById(Long typId);
    ArticleType selectByName(String name);
    List<ArticleType> list();
    List<ArticleType> listByName(@Param("names") List<String> names);
}
