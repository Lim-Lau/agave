package com.ansimpleasy.agave.ans.service.impl;

import com.ansimpleasy.agave.ans.annotation.DataSource;
import com.ansimpleasy.agave.ans.auth.AuthServiceImpl;
import com.ansimpleasy.agave.ans.auth.AuthUser;
import com.ansimpleasy.agave.ans.auth.JwtUtil;
import com.ansimpleasy.agave.ans.common.CredentialsMatcher;
import com.ansimpleasy.agave.ans.entity.User;
import com.ansimpleasy.agave.ans.enums.DataSourceEnum;
import com.ansimpleasy.agave.ans.mapper.user.UserMapper;
import com.ansimpleasy.agave.ans.service.IUserService;
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
    public User saveUser(User user) {
        user.setPassword(CredentialsMatcher.password(
                String.valueOf(user.getName()), user.getPassword()));
        save(user);
        return user;
    }

    @Override
    public AuthUser login(AuthServiceImpl.LoginDto user) {
        //登录逻辑
        User perUser = getByName(user.getName());

        if (perUser == null) {
            throw Lang.makeThrow("用户不存在!");
        }

        if (!Strings.equals(CredentialsMatcher.password(user.getName(),user.getPassword()), perUser.getPassword())) {
            throw Lang.makeThrow("用户名或密码不正确!");
        }


        return new AuthUser(perUser.getName(), perUser.getPassword()).token(JwtUtil.sign(user.getName(), "ans", user.isRememberMe()));

    }

}
