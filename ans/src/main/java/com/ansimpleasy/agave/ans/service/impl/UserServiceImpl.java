package com.ansimpleasy.agave.ans.service.impl;

import com.ansimpleasy.agave.ans.annotation.DataSource;
import com.ansimpleasy.agave.ans.entity.User;
import com.ansimpleasy.agave.ans.enums.DataSourceEnum;
import com.ansimpleasy.agave.ans.mapper.user.UserMapper;
import com.ansimpleasy.agave.ans.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
