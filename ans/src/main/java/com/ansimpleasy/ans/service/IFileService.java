package com.ansimpleasy.ans.service;

import com.ansimpleasy.ans.entity.common.File;
import com.ansimpleasy.ans.result.FileInfo;
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

    List<File> queryFilesByType(String type,String fileType , long id);

    FileInfo upload(MultipartFile file, long foreignKey, String tableType);
}
