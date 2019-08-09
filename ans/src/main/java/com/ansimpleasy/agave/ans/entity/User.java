package com.ansimpleasy.agave.ans.entity;

import com.ansimpleasy.agave.ans.enums.Status;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author LiuCan
 * @date 2019/8/7 8:43
 */
@TableName("t_user")
@Data
public class User {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    private String name;

    private String password;

    private String status = Status.VALID.getCode();

    private Date createTime ;

    private Date updateTime;

}
