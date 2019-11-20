package com.ansimpleasy.ans.entity;

import com.ansimpleasy.ans.entity.base.StatefulBaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucan
 * @since 2019-08-11
 */

public class Permission extends StatefulBaseEntity {

    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
