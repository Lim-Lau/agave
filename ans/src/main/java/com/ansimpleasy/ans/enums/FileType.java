package com.ansimpleasy.ans.enums;

/**
 * @author LiuCan
 * @date 2019/12/13 14:33
 */
public enum FileType {

    JPEG("FFD8FF"),
    PNG("89504E47"),

    GIF("47494638");

    private String value = "";

    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
