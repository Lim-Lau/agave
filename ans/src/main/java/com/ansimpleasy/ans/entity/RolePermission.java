package com.ansimpleasy.ans.entity;

import com.ansimpleasy.ans.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucan
 * @since 2019-08-11
 */
@TableName("t_role_permission")
public class RolePermission extends BaseEntity {

    private long permissionId;

    private long roleId;

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
