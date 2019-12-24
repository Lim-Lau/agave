package com.ansimpleasy.ans.enums;

import org.nutz.lang.Strings;

/**
 * @author LiuCan
 * @date 2019/8/7 9:29
 */
public enum Status implements AnsIEnum<String> {
    /***
     * 有效
     */
    VALID("有效", "VALID"),
    /***
     * 删除
     */
    DELETED("已删除", "DELETED"),

    UNKNOWN("未知", "UNKNOWN"),;

    String name;

    String code;

    Status(String name, String code) {
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

    public Status from(String inputName) {
            for (Status temp:values()) {
                if (Strings.equalsIgnoreCase(temp.getCode(),inputName)){
                    return  temp;
                }

            }
            return  UNKNOWN;
        }
    }

