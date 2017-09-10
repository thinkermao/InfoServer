package net.hashcoding.code.scuinfo.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.hashcoding.code.scuinfo.Result;
import net.hashcoding.code.scuinfo.ValidateUtils;
import net.hashcoding.code.scuinfo.domain.entity.ArticleType;
import net.hashcoding.code.scuinfo.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RequestMapping("/article_type")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService service;

    @ApiOperation(value = "获取文章类型列表")
    @RequestMapping(method = RequestMethod.GET)
    public Result<List<ArticleType>> list() {
        List<ArticleType> articleTypes = service.list();
        return Result.success(articleTypes);
    }

    @ApiOperation(value = "插入新类型")
    @ApiImplicitParam(name = "name", value = "新插入数据名称", paramType = "form", required = true)
    @RequestMapping(method = RequestMethod.POST)
    public Result<String> insert(@RequestParam String name) {
        ValidateUtils.notEmpty("name", name);
        service.insert(name);
        return Result.success("");
    }
}
