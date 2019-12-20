package com.ansimpleasy.ans.service;

import com.ansimpleasy.ans.auth.AuthServiceImpl;
import com.ansimpleasy.ans.auth.AuthUser;
import com.ansimpleasy.ans.entity.user.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liucan@
 * @since 2019-08-07
 */
public interface IUserService extends IService<User> {
    User getByName(String name);

    AuthUser saveUser(User user);

    AuthUser login(AuthServiceImpl.LoginDto user);

}
