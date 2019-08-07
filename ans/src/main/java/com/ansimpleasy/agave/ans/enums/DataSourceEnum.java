package com.ansimpleasy.agave.ans.enums;

import lombok.Getter;

/**
 * 数据源枚举
 * @author LiuCan
 * @date 2019/8/7 11:49
 */
public enum  DataSourceEnum {

    DEVELOP("develop"),
    RELEASE("release");


    DataSourceEnum(String value) {
        this.value = value;
    }

    @Getter
    private String value;

}
