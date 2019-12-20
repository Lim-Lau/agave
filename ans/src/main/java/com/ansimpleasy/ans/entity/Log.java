package com.ansimpleasy.ans.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ansimpleasy.ans.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucan
 * @since 2019-12-17
 */
@TableName("t_log")
@ApiModel(value="Log对象", description="")
public class Log extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "相册名")
    private String name;

    @ApiModelProperty(value = "摘要")
    private String description;

    @ApiModelProperty(value = "内容")
    private byte[] content;


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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Log{" +
        ", name=" + name +
        ", description=" + description +
        ", content=" + content +
        "}";
    }
}
