package com.ansimpleasy.agave.ans.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import org.nutz.lang.Times;

import java.io.Serializable;
import java.util.Date;

/**
 * @author LiuCan
 * @date 2019/8/7 8:50
 */

public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private long id;
    @TableField("create_time")
    private Date createTime = Times.now();
    @TableField("update_time")
    private Date updateTime = Times.now();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
