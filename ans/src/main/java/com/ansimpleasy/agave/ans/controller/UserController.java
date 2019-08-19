package com.ansimpleasy.agave.ans.controller;


import com.ansimpleasy.agave.ans.auth.AuthServiceImpl;
import com.ansimpleasy.agave.ans.entity.User;
import com.ansimpleasy.agave.ans.result.Result;
import com.ansimpleasy.agave.ans.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author liucan
 * @since 2019-08-07
 */
@RestController
@RequestMapping("/user")
@Api(value = "User" , tags = {"用户相关接口"})
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody AuthServiceImpl.LoginDto user) {

        return Result.success().addData("user" , userService.login(user));
    }


    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody User user) {
        //登录逻辑
        User perUser = userService.getByName(user.getName());
        if (perUser != null) {
            return Result.fail("已存在的用户");
        }

        return Result.success().addData("user" , userService.saveUser(user));
    }


    @GetMapping("/fetch")
    @ApiOperation("获取用户信息")
    public Result fetch(@RequestParam("name") String name) {

        return Result.success().addData("user", userService.getByName(name));
    }



}

