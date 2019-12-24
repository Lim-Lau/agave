package com.ansimpleasy.ans.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @author LiuCan
 * @date 2019/8/7 9:29
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface AnsIEnum <T extends Serializable> extends IEnum {

    public String getName();

    public String getCode();
}
