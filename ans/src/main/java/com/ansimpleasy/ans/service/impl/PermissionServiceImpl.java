package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.entity.Permission;
import com.ansimpleasy.ans.mapper.permission.PermissionMapper;
import com.ansimpleasy.ans.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
