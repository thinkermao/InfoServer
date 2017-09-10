package net.hashcoding.code.scuinfo.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.hashcoding.code.scuinfo.Handler.MessageException;
import net.hashcoding.code.scuinfo.Result;
import net.hashcoding.code.scuinfo.ResultCode;
import net.hashcoding.code.scuinfo.ValidateUtils;
import net.hashcoding.code.scuinfo.domain.entity.Article;
import net.hashcoding.code.scuinfo.domain.entity.Attachment;
import net.hashcoding.code.scuinfo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService service;

    @ApiOperation(value = "put 文章")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "type", value = "文章类型", paramType = "form", required = true),
            @ApiImplicitParam(name = "url", value = "文章地址", paramType = "form", required = true, allowableValues = "range[1, 128]"),
            @ApiImplicitParam(name = "title", value = "文章标题", paramType = "form", required = true, allowableValues = "range[1, 512]"),
            @ApiImplicitParam(name = "thumb", value = "封面图片", paramType = "form", allowableValues = "range[1, 512]"),
            @ApiImplicitParam(name = "content", value = "内容", paramType = "form", required = true),
            @ApiImplicitParam(name = "createdAt", value = "创建时间", paramType = "form", required = true)
    })
    @RequestMapping(method = RequestMethod.PUT)
    public Result<Long> put(
            @RequestParam String type,
            @RequestParam String url,
            @RequestParam String title,
            @RequestParam String thumb,
            @RequestParam String content,
            @RequestParam String createdAt) {
        ValidateUtils.notEmpty("type", type);
        ValidateUtils.inRange("url", url, 1, 512);
        ValidateUtils.inRange("title", title, 1, 128);
        ValidateUtils.inRangeOrEmpty("thumb", thumb, 1, 512);
        ValidateUtils.notEmpty("content", content);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = df.parse(createdAt);
        } catch (ParseException e) {
            throw new MessageException(ResultCode.PARAMETER_ERROR,
                    "Date format error, use yyyy-MM-dd HH:mm:ss");
        }

        Long articleId = service.put(url, type, title, thumb, content, date);
        return Result.success(articleId);
    }

    @ApiOperation(value = "更新附件列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "articleId", value = "文章 ID", paramType = "path", required = true, allowableValues = "range[1,infinity)"),
            @ApiImplicitParam(name = "attachments", value = "附件列表", paramType = "body")
    })
    @RequestMapping(value = "/{articleId}/attachments", method = RequestMethod.PUT)
    public Result<String> put(
            @PathVariable Long articleId,
            @RequestBody(required = false) List<Attachment> attachments) {
        ValidateUtils.greatThan("articleId", articleId, 0L);
        if (attachments == null)
            attachments = new ArrayList<>();
        service.updateAttachments(articleId, attachments);
        return Result.success("");
    }

    @ApiOperation(value = "获取指定类型文件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "types[]", value = "需要获取的类型", paramType = "query", required = true),
            @ApiImplicitParam(name = "page", value = "页号", paramType = "query", allowableValues = "range[0,infinity)"),
            @ApiImplicitParam(name = "size", value = "页大小", paramType = "query", allowableValues = "range[0,infinity)")
    })
    @RequestMapping(method = RequestMethod.GET)
    public Result<List<Article>> get(
            @RequestParam("types[]") List<String> types,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        ValidateUtils.greatEqualThan("page", page, 0);
        ValidateUtils.greatEqualThan("size", size, 0);
        return Result.success(service.listByType(types, page, size));
    }

    @ApiOperation(value = "根据 ID 获取文章")
    @ApiImplicitParam(name = "articleId", value = "文章 ID", paramType = "path", required = true, allowableValues = "range[1,infinity)")
    @RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
    public Result<Article> getArticleById(@PathVariable("articleId") Long articleId) {
        ValidateUtils.greatThan("articleId", articleId, 0L);
        Article article = service.getArticleById(articleId);
        return Result.success(article);
    }

    @ApiOperation(value = "url 是否已经存在")
    @ApiImplicitParam(name = "url", value = "需要判断的 url", paramType = "query", required = true)
    @RequestMapping(value = "/url_exists", method = RequestMethod.GET)
    public Result<Boolean> isUrlExists(@RequestParam String url) {
        ValidateUtils.notEmpty("url", url);
        return Result.success(service.isUrlExists(url));
    }
}
