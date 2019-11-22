package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.entity.File;
import com.ansimpleasy.ans.mapper.file.FileMapper;
import com.ansimpleasy.ans.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
