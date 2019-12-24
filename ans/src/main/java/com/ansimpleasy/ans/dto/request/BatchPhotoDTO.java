package com.ansimpleasy.ans.dto.request;

import java.util.List;

/**
 * @author LiuCan
 * @date 2019/12/20 16:35
 */
public class BatchPhotoDTO {

    private Long albumId;

    private List<Long> fileIds;

    private Long userId;


    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public List<Long> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<Long> fileIds) {
        this.fileIds = fileIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
