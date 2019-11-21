package com.ansimpleasy.ans.controller;


import com.ansimpleasy.ans.auth.AuthServiceImpl;
import com.ansimpleasy.ans.entity.User;
import com.ansimpleasy.ans.result.Result;
import com.ansimpleasy.ans.service.acl.AuthService;
import com.ansimpleasy.ans.service.impl.RoleServiceImpl;
import com.ansimpleasy.ans.service.impl.UserServiceImpl;
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
public class UserController extends BaseController{

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody AuthServiceImpl.LoginDto user) {

        return Result.success().addData("user" , userService.login(user)).addData("roles",roleService.getAllByUserId(user.getName()));
    }

    @GetMapping("/check")
    @ApiOperation("检查token有效性")
    public Result check() {
        return user() == null ? Result.fail() : Result.success();
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody User user) {
        //登录逻辑
        User perUser = userService.getByName(user.getName());
        if (perUser != null) {
            return Result.fail("已存在的用户,请直接登录");
        }

        return Result.success().addData("user" , userService.saveUser(user));
    }


    @GetMapping("/fetch")
    @ApiOperation("获取用户信息")
    public Result fetch(@RequestParam("name") String name) {

        return Result.success().addData("user", userService.getByName(name));
    }



}

