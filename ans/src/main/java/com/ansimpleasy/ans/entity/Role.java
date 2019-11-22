package com.ansimpleasy.ans.entity;

import com.ansimpleasy.ans.entity.base.StatefulBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucan
 * @since 2019-08-11
 */
@TableName("t_role")
public class Role extends StatefulBaseEntity {
    private String description;

    private String role;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
