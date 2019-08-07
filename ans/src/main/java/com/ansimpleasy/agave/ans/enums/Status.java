package com.ansimpleasy.agave.ans.enums;

/**
 * @author LiuCan
 * @date 2019/8/7 9:29
 */
public enum Status {
    /***
     * 有效
     */
    VALID("有效", "valid"),
    /***
     * 删除
     */
    DELETED("已删除", "deleted");

    String name;

    String code;

    Status(String name, String code) {
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
