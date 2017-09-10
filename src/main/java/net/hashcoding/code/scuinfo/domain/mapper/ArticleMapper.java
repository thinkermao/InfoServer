package net.hashcoding.code.scuinfo.domain.mapper;

import net.hashcoding.code.scuinfo.domain.entity.Article;
import net.hashcoding.code.scuinfo.domain.entity.ArticleType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    // select
    Article selectByUrl(String url);

    Article selectById(Long articleId);

    List<Article> listByTypes(@Param("types") List<ArticleType> types);

    // insert
    void insert(Article article);

    // update
    void update(Article article);

    void deleteById(Long articleId);
}
