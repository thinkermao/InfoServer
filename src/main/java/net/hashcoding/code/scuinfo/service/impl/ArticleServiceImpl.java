package net.hashcoding.code.scuinfo.service.impl;

import com.github.pagehelper.PageHelper;
import net.hashcoding.code.scuinfo.Handler.MessageException;
import net.hashcoding.code.scuinfo.ResultCode;
import net.hashcoding.code.scuinfo.domain.entity.Article;
import net.hashcoding.code.scuinfo.domain.entity.ArticleType;
import net.hashcoding.code.scuinfo.domain.entity.Attachment;
import net.hashcoding.code.scuinfo.domain.mapper.ArticleMapper;
import net.hashcoding.code.scuinfo.domain.mapper.ArticleTypeMapper;
import net.hashcoding.code.scuinfo.domain.mapper.AttachmentMapper;
import net.hashcoding.code.scuinfo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTypeMapper typeMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
        isolation = Isolation.READ_COMMITTED)
    public Long put(String url, String type,
                    String title, String thumb, String content, Date createdAt) {
        ArticleType articleType = typeMapper.selectByName(type);
        if (articleType == null) {
            throw new MessageException(
                    ResultCode.PARAMETER_ERROR, "article type not support");
        }

        Article article = articleMapper.selectByUrl(url);
        boolean exists = article != null;
        if (!exists) {
            article = new Article();
            article.setOriginUrl(url);
        }
        article.setTitle(title);
        article.setType(articleType);
        article.setThumb(thumb);
        article.setContent(content);
        article.setCreatedAt(createdAt);
        if (exists) {
            articleMapper.update(article);
        } else {
            articleMapper.insert(article);
        }

        return article.getArticleId();
    }

    @Override
    public Article getArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        if (article == null)
            throw new MessageException(ResultCode.NOT_FOUND, "article not exists");
        return article;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
        isolation = Isolation.READ_COMMITTED)
    public void updateAttachments(Long id, List<Attachment> attachments) {
        Article article = articleMapper.selectById(id);
        if (article == null)
            throw new MessageException(ResultCode.NOT_FOUND, "article not exists");

        List<Attachment> originAttachments = article.getAttachments();
        List<Attachment> update = new ArrayList<>();
        int i = 0;
        for (; i < originAttachments.size() && i < attachments.size(); ++i) {
            Attachment attach = attachments.get(i);
            attach.setArticleId(article.getArticleId());
            attach.setAttachId(originAttachments.get(i).getAttachId());
            update.add(attach);
        }

        if (originAttachments.size() - i > 0) {
            List<Long> ids = new ArrayList<>();
            for (; i < originAttachments.size(); ++i) {
                ids.add(originAttachments.get(i).getAttachId());
            }
            attachmentMapper.deleteById(ids);
        } else if (attachments.size() - i > 0) {
            List<Attachment> insert = new ArrayList<>();
            for (; i < attachments.size(); ++i) {
                Attachment attach = attachments.get(i);
                attach.setArticleId(article.getArticleId());
                insert.add(attach);
            }
            attachmentMapper.insert(insert);
        }

        if (update.size() > 0)
            attachmentMapper.update(update);
    }

    @Override
    public List<Article> listByType(List<String> typeNames, int page, int limit) {
        List<ArticleType> types = typeMapper.listByName(typeNames);

        PageHelper.startPage(page, limit);
        return articleMapper.listByTypes(types);
    }

    private void delete(Article article) {
        List<Long> ids = new ArrayList<>();
        for (Attachment attach : article.getAttachments())
            ids.add(attach.getAttachId());
        if (ids.size() > 0)
            attachmentMapper.deleteById(ids);
        articleMapper.deleteById(article.getArticleId());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,
        isolation = Isolation.READ_COMMITTED)
    public boolean isUrlExists(String url) {
        Article article = articleMapper.selectByUrl(url);
        if (article == null)
            return false;

        long begin = article.getUpdatedAt().getTime();
        long end = new Date().getTime();
        long diffHours = (end - begin) / (1000 * 60 * 60);
        if (diffHours == 0)
            diffHours++;
        if (diffHours > 6)
            return true;
        // TODO: 过夜的怎么办？
        int expr = (int) Math.pow(2, diffHours);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -expr);
        Date timeout = calendar.getTime();
        if (article.getUpdatedAt().compareTo(timeout) <= 0) {
//            calendar.add(Calendar.HOUR, -expr);
//            timeout = calendar.getTime();
//            if (article.getCreatedAt().compareTo(timeout) <= 0)
//                delete(article);
            return false;
        }
        return true;
    }
}
