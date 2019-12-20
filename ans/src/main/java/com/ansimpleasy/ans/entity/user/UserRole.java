package com.ansimpleasy.ans.entity.user;

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
@TableName("t_user_role")
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
