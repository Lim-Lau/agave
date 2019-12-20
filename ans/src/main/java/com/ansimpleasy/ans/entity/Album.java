package com.ansimpleasy.ans.entity;

import com.ansimpleasy.ans.entity.base.StatefulBaseEntity;
import com.ansimpleasy.ans.enums.ContentType;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author liucan
 * @since 2019-12-17
 */
@TableName("t_album")
@ApiModel(value="Album对象", description="Album对象")
public class Album extends StatefulBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "相册名")
    private String name;

    @ApiModelProperty(value = "摘要")
    private String description;

    @ApiModelProperty(value = "相册类型")
    private ContentType type;

    @ApiModelProperty(value = "封面key")
    private String headerKey;

    @ApiModelProperty(value = "用户id")
    private long userId;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Album{" +
        ", name=" + name +
        ", description=" + description +
        ", type=" + type +
        ", headerKey=" + headerKey +
        ", userId=" + userId +
        "}";
    }
}
