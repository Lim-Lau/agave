package com.ansimpleasy.agave.ans.entity;

import com.ansimpleasy.agave.ans.entity.base.StatefulBaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucan
 * @since 2019-08-11
 */

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
