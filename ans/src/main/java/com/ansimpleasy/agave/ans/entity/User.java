package com.ansimpleasy.agave.ans.entity;

import com.ansimpleasy.agave.ans.entity.base.StatefulBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author LiuCan
 * @date 2019/8/7 8:43
 */
@TableName("t_user")
public class User extends StatefulBaseEntity {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
