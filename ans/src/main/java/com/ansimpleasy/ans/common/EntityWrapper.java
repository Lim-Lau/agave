package com.ansimpleasy.ans.common;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author LiuCan
 * @date 2019/11/21 10:26
 */
public class EntityWrapper<T> extends QueryWrapper {

    public AbstractWrapper eq(String column, Object val) {
        return super.eq(true, column, val);
    }
}


