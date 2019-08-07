package com.ansimpleasy.agave.ans.entity.base;

import com.ansimpleasy.agave.ans.enums.Status;

/**
 * @author LiuCan
 * @date 2019/8/7 9:27
 */
public class StatefulBaseEntity extends BaseEntity {

    private String status = Status.VALID.getCode();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
