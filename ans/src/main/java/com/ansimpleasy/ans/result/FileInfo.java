package com.ansimpleasy.ans.result;

import com.ansimpleasy.ans.enums.FileType;
import org.nutz.lang.util.NutMap;

/**
 * @author LiuCan
 * @date 2019/12/16 15:25
 */
public class FileInfo {
    String url;
    String key;
    FileType type = FileType.PNG;
    NutMap ext = NutMap.NEW();

    public NutMap getExt() {
        return ext;
    }

    public void setExt(NutMap ext) {
        this.ext = ext;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public FileInfo setUrl(String url) {
        this.url = url;
        return this;
    }


    public FileInfo type(FileType type) {
        this.type = type;
        return this;
    }

    public FileInfo ext(String key, Object obj) {
        this.ext.setv(key, obj);
        return this;
    }


    public String getKey() {
        return key;
    }

    public FileInfo setKey(String key) {
        this.key = key;
        return this;
    }

    public static FileInfo create() {
        return new FileInfo();
    }

    public static FileInfo create(String url, String key) {
        return create().setUrl(url).setKey(key);
    }
}
