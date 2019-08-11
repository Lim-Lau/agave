package com.ansimpleasy.agave.ans.entity;

import com.ansimpleasy.agave.ans.entity.base.StatefulBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author LiuCan
 * @date 2019/8/7 8:43
 */
@TableName("user")
@Data
public class User extends StatefulBaseEntity{

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;


}
