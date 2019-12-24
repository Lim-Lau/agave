package com.ansimpleasy.ans.dto.response;

import com.ansimpleasy.ans.entity.Album;

/**
 * @author LiuCan
 * @date 2019/12/24 11:21
 */
public class AlbumDTO extends Album {
    private String headerUrl;

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }
}
