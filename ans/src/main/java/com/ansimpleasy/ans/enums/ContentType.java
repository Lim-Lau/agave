package com.ansimpleasy.ans.enums;

import java.io.Serializable;

/**
 * @author LiuCan
 * @date 2019/12/17 11:21
 */
public enum  ContentType implements AnsIEnum<String> {
    LIFE("生活类", "LIFE"),
    EMOTION("情感类", "EMOTION"),;

    String name;

    String code;

    ContentType(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public Serializable getValue() {
        return null;
    }
}
