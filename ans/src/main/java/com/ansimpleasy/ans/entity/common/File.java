package com.ansimpleasy.ans.entity.common;

import com.ansimpleasy.ans.entity.base.BaseEntity;
import com.ansimpleasy.ans.enums.FileType;
import com.ansimpleasy.ans.enums.TableType;
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
    private long relatedId;

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
    private FileType fileType;

    /**
     * 关联的表名
     */
    private TableType tableType;



    public long getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(long relatedId) {
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

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public TableType getTableType() {
        return tableType;
    }

    public void setTableType(TableType tableType) {
        this.tableType = tableType;
    }
}
