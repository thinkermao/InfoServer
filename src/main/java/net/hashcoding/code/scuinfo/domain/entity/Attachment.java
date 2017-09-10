package net.hashcoding.code.scuinfo.domain.entity;

public class Attachment {
    private Long attachId;
    private String name;
    private String url;
    private Long articleId;

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "attachId=" + attachId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
