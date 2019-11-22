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
@TableName("t_permission")
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
