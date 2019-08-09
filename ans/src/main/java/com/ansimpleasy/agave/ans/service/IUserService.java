package com.ansimpleasy.agave.ans.service;

import com.ansimpleasy.agave.ans.entity.User;
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

}
