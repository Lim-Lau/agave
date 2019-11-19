package com.ansimpleasy.agave.ans.entity.base;

import com.ansimpleasy.agave.ans.enums.Status;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @author LiuCan
 * @date 2019/8/7 9:27
 */

public class StatefulBaseEntity extends BaseEntity {

    @TableField("status")
    private String status = Status.VALID.name();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
