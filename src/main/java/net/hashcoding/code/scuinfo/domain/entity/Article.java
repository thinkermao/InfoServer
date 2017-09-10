package net.hashcoding.code.scuinfo.domain.entity;

import java.util.Date;
import java.util.List;

public class Article {
    private Long articleId;
    private ArticleType type;
    private String title;
    private String originUrl;
    private String content;
    private String thumb;
    private Date createdAt;
    private Date updatedAt;
    private List<Attachment> attachments;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", originUrl='" + originUrl + '\'' +
                ", content='" + content + '\'' +
                ", thumb='" + thumb + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", attachments=" + attachments +
                '}';
    }
}
