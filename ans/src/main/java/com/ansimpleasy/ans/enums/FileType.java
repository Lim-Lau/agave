package com.ansimpleasy.ans.enums;


import org.nutz.lang.Strings;

/**
 * @author LiuCan
 * @date 2019/12/13 14:33
 */
public enum FileType implements AnsIEnum<String>{

    JPEG("图片","JPEG","FFD8FF"),
    PNG("图片","PNG","89504E47"),
    GIF("图片", "GIF", "47494638"),
    IMG("图片", "IMG", ""),
    PDF("文档", "PDF", ""),
    DOC("文档", "DOC", ""),
    PPT("文档", "PPT", ""),
    UNKNOWN("未知", "UNKNOWN", ""),
    ;

    String name;

    String code;

    String val;

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

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    FileType(String name, String code, String val) {
        this.name = name;
        this.code = code;
        this.val = val;
    }

    @Override
    public String getValue() {
        return code;
    }
    public static FileType from(String inputName) {
        for (FileType temp : values()) {
            if (Strings.equalsIgnoreCase(temp.getCode(), inputName)) {
                return temp;
            }
        }

        return UNKNOWN;

    }
}
