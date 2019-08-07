package com.ansimpleasy.agave.ans.service.impl;

import com.ansimpleasy.agave.ans.entity.User;
import com.ansimpleasy.agave.ans.mapper.UserMapper;
import com.ansimpleasy.agave.ans.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucan@
 * @since 2019-08-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
