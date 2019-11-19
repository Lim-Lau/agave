package com.ansimpleasy.agave.ans.entity;

import com.ansimpleasy.agave.ans.entity.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucan
 * @since 2019-08-11
 */

public class UserRole extends BaseEntity {

    private long userId;

    private long roleId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }
}
