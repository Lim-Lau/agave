package com.ansimpleasy.agave.ans.entity.base;

import com.ansimpleasy.agave.ans.enums.Status;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author LiuCan
 * @date 2019/8/7 9:27
 */
@Data
public class StatefulBaseEntity extends BaseEntity {

    @TableField("status")
    private String status = Status.VALID.getCode();

}
