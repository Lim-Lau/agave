package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.annotation.DataSource;
import com.ansimpleasy.ans.auth.AuthServiceImpl;
import com.ansimpleasy.ans.auth.AuthUser;
import com.ansimpleasy.ans.auth.JwtUtil;
import com.ansimpleasy.ans.common.CredentialsMatcher;
import com.ansimpleasy.ans.entity.user.User;
import com.ansimpleasy.ans.enums.DataSourceEnum;
import com.ansimpleasy.ans.exception.AnsException;
import com.ansimpleasy.ans.mapper.user.UserMapper;
import com.ansimpleasy.ans.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.springframework.stereotype.Service;

/**
 *
 * @author liucan@
 * @since 2019-08-07
 */
@Service
@DataSource(DataSourceEnum.DEVELOP)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getByName(String name) {

        return this.baseMapper.fetchUserByName(name);
    }

    @Override
    public AuthUser saveUser(User user) {
        user.setPassword(CredentialsMatcher.password(
                String.valueOf(user.getName()), user.getPassword()));
        save(user);
        return new AuthUser(user.getName(), user.getPassword()).token(JwtUtil.sign(user.getName(), "ans", Boolean.FALSE));
    }

    @Override
    public AuthUser login(AuthServiceImpl.LoginDto user) {
        //登录逻辑
        User perUser = getByName(user.getName());

        if (perUser == null) {
            throw Lang.makeThrow(AnsException.class,"用户不存在!");
        }

        if (!Strings.equals(CredentialsMatcher.password(user.getName(),user.getPassword()), perUser.getPassword())) {
            throw Lang.makeThrow(AnsException.class,"用户名或密码不正确!");
        }


        return new AuthUser(perUser.getName(), perUser.getPassword()).token(JwtUtil.sign(user.getName(), "ans", user.isRememberMe()));

    }

}
