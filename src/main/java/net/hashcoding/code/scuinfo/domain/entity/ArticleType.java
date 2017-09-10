package net.hashcoding.code.scuinfo.domain.entity;

public class ArticleType {
    private Long typeId;
    private String name;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ArticleType{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }
}
