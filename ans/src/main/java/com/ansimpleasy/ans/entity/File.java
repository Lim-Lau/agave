package com.ansimpleasy.ans.entity;

import com.ansimpleasy.ans.entity.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liucan
 * @since 2019-11-22
 */
@TableName("t_file")
public class File extends BaseEntity implements Serializable  {

    private static final long serialVersionUID = 1L;

    /**
     * 关联id
     */
    private Integer relatedId;

    /**
     * 文件key
     */
    private String fileKey;

    /**
     * 描述
     */
    private String description;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 关联的表名
     */
    private String tableType;



    public Integer getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Integer relatedId) {
        this.relatedId = relatedId;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

}
