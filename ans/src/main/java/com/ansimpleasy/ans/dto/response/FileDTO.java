package com.ansimpleasy.ans.dto.response;

import com.ansimpleasy.ans.entity.common.File;

/**
 * @author LiuCan
 * @date 2019/12/26 10:28
 */
public class FileDTO extends File {
    private String fileUrl;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
