package com.ansimpleasy.ans.entity;

import com.ansimpleasy.ans.entity.base.StatefulBaseEntity;
import com.ansimpleasy.ans.enums.Sex;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author LiuCan
 * @date 2019/8/7 8:43
 */
@TableName("user")
public class User extends StatefulBaseEntity {

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("sex")
    private Sex sex;


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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
