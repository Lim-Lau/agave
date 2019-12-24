package com.ansimpleasy.ans.enums;

import org.nutz.lang.Strings;

/**
 * @author LiuCan
 * @date 2019/11/20 9:31
 */
public enum Sex implements AnsIEnum<String> {
    MALE("男", "MALE"),
    FEMALE("女", "FEMALE"),
    UNKNOWN("未知", "UNKNOWN"),;

    String name;

    String code;

    Sex(String name, String code) {
        this.name = name;
        this.code = code;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return this.code;
    }

    public static Sex from(String inputName) {
        for (Sex sex : values()) {
            if (Strings.equalsIgnoreCase(inputName, sex.code)) {
                return sex;
            }
        }
        return UNKNOWN;
    }}
