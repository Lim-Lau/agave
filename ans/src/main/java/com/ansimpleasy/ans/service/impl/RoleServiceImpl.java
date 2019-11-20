package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.entity.Role;
import com.ansimpleasy.ans.mapper.role.RoleMapper;
import com.ansimpleasy.ans.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucan
 * @since 2019-08-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
