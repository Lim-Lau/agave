package com.ansimpleasy.ans.dto.request;

import com.ansimpleasy.ans.entity.Album;
import com.ansimpleasy.ans.entity.common.File;

import java.util.List;

/**
 *  添加照片请求入参
 * @author LiuCan
 * @date 2019/12/20 15:41
 */
public class PhotoDTO extends Album {

    private List<File> files;

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "PhotoDTO{" +
                "files=" + files +
                '}';
    }
}
