package com.ansimpleasy.ans.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author LiuCan
 * @date 2019/11/20 9:31
 */
public enum Sex implements AnsIEnum<String> {
    MALE("男", "MALE"),
    FEMALE("女", "FEMALE"),;

    String name;

    String code;

    Sex(String name, String code) {
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
    public String getValue() {
        return this.code;
    }}
