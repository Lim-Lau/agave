package com.ansimpleasy.ans.service;

import com.ansimpleasy.ans.dto.response.FileDTO;
import com.ansimpleasy.ans.entity.common.File;
import com.ansimpleasy.ans.enums.FileType;
import com.ansimpleasy.ans.enums.TableType;
import com.ansimpleasy.ans.result.FileInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liucan
 * @since 2019-11-22
 */
public interface IFileService extends IService<File> {

    /**
     * 根据文件类型查询文件列表
     *
     * @author LiuCan
     * @date 2019/12/20 11:29
     * @param type
     * @param fileType
     * @param id
     * @return java.util.List<com.ansimpleasy.ans.entity.common.File>
     */
    List<File> queryFilesByType(String type,String fileType , long id);

    /**
     * 上传文件信息
     *
     * @author LiuCan
     * @date 2019/12/20 11:29
     * @param file
     * @param foreignKey
     * @param tableType
     * @return com.ansimpleasy.ans.result.FileInfo
     */
    FileInfo upload(MultipartFile file, long foreignKey, String tableType);

    /**
     * 根据关联id和类型获取文件列表
     *
     * @author LiuCan
     * @date 2019/12/20 14:54
     * @param id
     * @param page
     * @param album
     * @param img
     * @return IPage<File>
     */
    IPage<FileDTO> queryFilesByIdAndType(long id, IPage page, TableType album, FileType img);
}
