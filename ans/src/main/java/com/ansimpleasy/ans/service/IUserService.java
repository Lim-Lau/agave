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
    /**
     * 根据用户名查询用户信息
     *
     * @author LiuCan
     * @date 2019/12/20 11:29
     * @param name
     * @return com.ansimpleasy.ans.entity.user.User
     */
    User getByName(String name);
    /**
     * 保存用户信息
     *
     * @author LiuCan
     * @date 2019/12/20 11:30
     * @param user
     * @return com.ansimpleasy.ans.auth.AuthUser
     */
    AuthUser saveUser(User user);

    /**
     * 登录
     *
     * @author LiuCan
     * @date 2019/12/20 11:30
     * @param user
     * @return com.ansimpleasy.ans.auth.AuthUser
     */
    AuthUser login(AuthServiceImpl.LoginDto user);

}
