package com.ansimpleasy.ans.controller.common;

import com.ansimpleasy.ans.controller.base.BaseController;
import com.ansimpleasy.ans.result.Result;
import com.ansimpleasy.ans.service.impl.FileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author LiuCan
 * @date 2019/12/16 14:50
 */
@RestController
@RequestMapping("/common")
@Api(value = "公共模块", tags = "公共模块")
public class CommonController extends BaseController {
    @Autowired
    private FileServiceImpl fileService;

    @GetMapping("/files")
    @ApiOperation("获取文件列表")
    public Result files(@RequestParam(value = "type" , defaultValue = "ALBUM") String type,
                        @RequestParam(value = "fileType" , defaultValue = "IMG") String fileType) {

        return Result.success().addData("files", fileService.queryFilesByType(type,fileType, user().getId()));
    }

    @PostMapping("/upload")
    @ApiOperation("上传附件")
    public Result upload(MultipartFile file, @RequestParam("foreignKey") long foreignKey,
                         @RequestParam("tableType") String tableType) {
        return Result.success().addData("fileInfo", fileService.upload(file,foreignKey,tableType));
    }


}
