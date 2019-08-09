package com.ansimpleasy.agave.ans.service.impl;

import com.ansimpleasy.agave.ans.annotation.DataSource;
import com.ansimpleasy.agave.ans.entity.User;
import com.ansimpleasy.agave.ans.enums.DataSourceEnum;
import com.ansimpleasy.agave.ans.mapper.UserMapper;
import com.ansimpleasy.agave.ans.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author liucan@
 * @since 2019-08-07
 */
@Service
@DataSource(DataSourceEnum.DEVELOP)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getByName(String name) {

        return userMapper.getByName(name);
    }
}
