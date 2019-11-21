package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.common.EntityWrapper;
import com.ansimpleasy.ans.entity.Role;
import com.ansimpleasy.ans.entity.User;
import com.ansimpleasy.ans.entity.UserRole;
import com.ansimpleasy.ans.exception.AnsException;
import com.ansimpleasy.ans.mapper.role.RoleMapper;
import com.ansimpleasy.ans.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nutz.lang.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRoleServiceImpl userRoleService;

    @Override
    public List<Role> getAllByUserId(String name) {
        User user = userService.getByName(name);
        if (user == null) {
            throw Lang.makeThrow(AnsException.class, "用户不存在");
        }

        List<UserRole> userRoles = userRoleService.list(
                new EntityWrapper<UserRole>().eq("user_id" , user.getId()));

        if (userRoles.size() == 0) {
            return Lang.list();
        }
        List<Long> roleIds = Lang.list();
        for (UserRole userRole : userRoles
        ) {
            roleIds.add(userRole.getRoleId());
        }

        return listByIds(roleIds).stream().collect(Collectors.toList());
    }

}
