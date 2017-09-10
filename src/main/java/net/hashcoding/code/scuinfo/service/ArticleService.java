package net.hashcoding.code.scuinfo.service;

import net.hashcoding.code.scuinfo.domain.entity.Article;
import net.hashcoding.code.scuinfo.domain.entity.Attachment;

import java.util.Date;
import java.util.List;

public interface ArticleService {
    Long put(String url, String type, String title, String thumb, String content, Date date);
    Article getArticleById(Long id);
    void updateAttachments(Long id, List<Attachment> attachments);

    List<Article> listByType(List<String> types, int page, int limit);

    boolean isUrlExists(String url);
}
