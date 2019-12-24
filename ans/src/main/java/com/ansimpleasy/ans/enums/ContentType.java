package com.ansimpleasy.ans.enums;

import org.nutz.lang.Strings;

/**
 * @author LiuCan
 * @date 2019/12/17 11:21
 */
public enum  ContentType implements AnsIEnum<String> {
    LIFE("生活类", "LIFE"),
    EMOTION("情感类", "EMOTION"),
    UNKNOWN("未知", "UNKNOWN"),;

    String name;

    String code;

    ContentType(String name, String code) {
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

    public static ContentType from(String inputName) {
        for (ContentType contentType : values()) {
            if (Strings.equalsIgnoreCase(contentType.getCode(), inputName)) {
                return contentType;
            }
        }

        return UNKNOWN;

    }
}
