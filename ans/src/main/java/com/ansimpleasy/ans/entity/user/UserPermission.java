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
@TableName("t_user_permission")
public class UserPermission extends BaseEntity {
    private long permissionId;

    private long userId;

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
