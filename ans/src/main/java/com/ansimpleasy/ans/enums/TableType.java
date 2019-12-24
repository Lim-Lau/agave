package com.ansimpleasy.ans.enums;


import org.nutz.lang.Strings;

import java.io.Serializable;

/**
 * @author LiuCan
 * @date 2019/12/20 14:21
 */
public enum TableType implements AnsIEnum<String> {

    ALBUM("相册", "ALBUM"),
    UNKNOWN("未知", "UNKNOWN"),;

    private String name;

    private String code;

    TableType(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Serializable getValue() {
        return this.getCode();
    }

    public static TableType  from(String inputName) {
        for (TableType temp:values()) {
            if (Strings.equalsIgnoreCase(temp.getCode(),inputName)){
                return  temp;
            }

        }
        return  UNKNOWN;
    }
}
