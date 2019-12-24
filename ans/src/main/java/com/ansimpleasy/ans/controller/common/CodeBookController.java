package com.ansimpleasy.ans.controller.common;

import com.ansimpleasy.ans.result.Result;
import com.ansimpleasy.ans.service.ICodeBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuCan
 * @date 2019/12/23 14:47
 */
@RestController
@RequestMapping("/code/book")
@Api(value = "codeBook", tags = {"码本模块"})
public class CodeBookController {
    @Autowired
    ICodeBookService codeBookService;

    @GetMapping("/all")
    @ApiOperation("获取所有码本")
    public Result all() {
        return Result.success().addData("data", codeBookService.getCodeBook());
    }
}
