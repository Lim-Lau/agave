package com.ansimpleasy.ans.controller;


import com.ansimpleasy.ans.controller.base.BaseController;
import com.ansimpleasy.ans.result.Result;
import com.ansimpleasy.ans.service.impl.AlbumServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liucan
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/album")
public class AlbumController extends BaseController {
    @Autowired
    private AlbumServiceImpl albumService;

    @GetMapping("/list")
    @ApiOperation("相册列表")
    public Result search() {

        return user() == null ? Result.fail("用户未登录!"):Result.success().addData("albums",albumService.list(user()));
    }

    @PostMapping("/add")
    @ApiOperation("添加相册")
    public Result add() {
        return Result.success();
    }


}

