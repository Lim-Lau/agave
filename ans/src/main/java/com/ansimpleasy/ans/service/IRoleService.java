package com.ansimpleasy.ans.service;

import com.ansimpleasy.ans.entity.user.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liucan
 * @since 2019-08-11
 */
public interface IRoleService extends IService<Role> {

    List<Role> getAllByUserId(String name);
}
