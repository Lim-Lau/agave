package com.ansimpleasy.agave.ans.enums;

/**
 * @author LiuCan
 * @date 2019/11/20 9:31
 */
public enum Sex {
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

}