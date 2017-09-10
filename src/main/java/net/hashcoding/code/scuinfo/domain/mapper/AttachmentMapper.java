package net.hashcoding.code.scuinfo.domain.mapper;

import net.hashcoding.code.scuinfo.domain.entity.Attachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttachmentMapper {
    List<Attachment> selectByArticleId(Long articleId);

    void insert(@Param("attachments") List<Attachment> attachments);
    void update(@Param("attachments") List<Attachment> attachments);

    void deleteById(@Param("ids") List<Long> ids);
}
