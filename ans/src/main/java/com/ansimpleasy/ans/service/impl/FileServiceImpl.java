package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.common.qiniu.QiniuStorage;
import com.ansimpleasy.ans.entity.common.File;
import com.ansimpleasy.ans.enums.FileType;
import com.ansimpleasy.ans.enums.TableType;
import com.ansimpleasy.ans.exception.AnsException;
import com.ansimpleasy.ans.mapper.file.FileMapper;
import com.ansimpleasy.ans.result.FileInfo;
import com.ansimpleasy.ans.service.IFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nutz.lang.Lang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucan
 * @since 2019-11-22
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    @Autowired
    QiniuStorage qiniuStorage;
    private static final String domain = "";

    @Override
    public List<File> queryFilesByType(String type,String fileType , long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("related_id", id);
        map.put("table_type", type);
        map.put("file_type", fileType);

        return this.baseMapper.selectByMap(map).stream().map(file -> {
            return transferFile(file);}).collect(Collectors.toList());
    }


    @Override
    public FileInfo upload(MultipartFile file, long foreignKey, String tableType) {
        FileInfo fileInfo = new FileInfo();
        try {
            String key = qiniuStorage.uploadImage(file.getBytes());
            File saveFile = new File();
            saveFile.setFileKey(key);
            saveFile.setDescription("文件");
            saveFile.setRelatedId(foreignKey);
            saveFile.setFileType(FileType.IMG);
            saveFile.setTableType(TableType.from(tableType));
            this.baseMapper.insert(saveFile);
            fileInfo.setKey(key);
            fileInfo.setType(FileType.IMG);
            fileInfo.setUrl(qiniuStorage.getUrl(key));
        } catch (IOException e) {
            throw Lang.makeThrow(AnsException.class, "文件上传失败!");
        }

        return fileInfo;
    }

    @Override
    public IPage<File> queryFilesByIdAndType(long id, IPage page, TableType tableType, FileType fileType) {

        return page(page, new QueryWrapper<File>()
                .eq("related_id", id)
                .eq("table_type", tableType)
                .eq("fileType", fileType));

    }

    private File transferFile(File file) {
        file.setFileKey(qiniuStorage.getUrl(file.getFileKey()));
        return file;
    }
}
