package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.common.qiniu.QiniuWrapper;
import com.ansimpleasy.ans.dto.request.BatchPhotoDTO;
import com.ansimpleasy.ans.dto.request.PhotoDTO;
import com.ansimpleasy.ans.dto.response.AlbumDTO;
import com.ansimpleasy.ans.dto.response.FileDTO;
import com.ansimpleasy.ans.entity.Album;
import com.ansimpleasy.ans.entity.common.File;
import com.ansimpleasy.ans.entity.user.User;
import com.ansimpleasy.ans.enums.FileType;
import com.ansimpleasy.ans.enums.Status;
import com.ansimpleasy.ans.enums.TableType;
import com.ansimpleasy.ans.exception.AnsException;
import com.ansimpleasy.ans.mapper.album.AlbumMapper;
import com.ansimpleasy.ans.service.IAlbumService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liucan
 * @since 2019-12-17
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements IAlbumService {
    @Autowired
    private QiniuWrapper qiniuWrapper;

    @Autowired
    private FileServiceImpl fileService;

    @Override
    public IPage searchByPageAndKey(IPage page, String key) {
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", key);
        return page(page, queryWrapper);
    }

    @Override
    public List<Album> list(User user) {
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        queryWrapper.eq("status", Status.VALID);

        return list(queryWrapper).stream().map(album -> {
            return packageAlbum(album);
        }).collect(Collectors.toList());
    }

    @Override
    public void update(Album album) {
        album.setUpdateTime(Times.now());
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", album.getId());
        queryWrapper.eq("status", Status.VALID);
        update(album, queryWrapper);
    }

    @Override
    public void updateStatus(long id) {
        Album album = getById(id);
        if (album == null) {
            throw Lang.makeThrow(AnsException.class, "无效的相册编号！");
        }
        album.setStatus(Status.DELETED);
        album.setUpdateTime(Times.now());
        update(album, new QueryWrapper<Album>().eq("id", id));
    }

    @Override
    public IPage<FileDTO> queryPhotoByIdAndPage(long id, IPage page) {

        return fileService.queryFilesByIdAndType(id, page, TableType.ALBUM, FileType.IMG);
    }

    @Override
    public void addPhoto(PhotoDTO photoDTO) {
        // 查询相册信息
        isExistAlbum(new QueryWrapper<Album>().eq("user_id", photoDTO.getUserId()).eq("id", photoDTO.getId()));
        // 保存照片信息
        List<File> files = photoDTO.getFiles().stream().map(file -> {
            file.setRelatedId(photoDTO.getId());
            file.setTableType(TableType.ALBUM);
            file.setFileType(FileType.IMG);
            file.setDescription(TableType.ALBUM + FileType.IMG.getName());
            return file;
        }).collect(Collectors.toList());
        fileService.saveBatch(files);
    }

    @Override
    public void batchDelete(BatchPhotoDTO batchPhotoDTO) {
        isExistAlbum(new QueryWrapper<Album>().eq("user_id", batchPhotoDTO.getUserId()).eq("id", batchPhotoDTO.getAlbumId()));

    }

    @Override
    public AlbumDTO get(long id) {
        Album album = getById(id);
        AlbumDTO albumDTO = Json.fromJson(AlbumDTO.class, Json.toJson(album));
        albumDTO.setHeaderUrl(qiniuWrapper.getUrl(album.getHeaderKey()));
        return albumDTO;
    }

    @Override
    public void updatePhoto(File file) {
        fileService.updateById(file);
    }


    /**
     * @author LiuCan
     * @date 2019/12/20 16:55
     * @param queryWrapper
     * @return com.ansimpleasy.ans.entity.Album
     */
    private Album isExistAlbum(QueryWrapper queryWrapper) {
        // 查询相册信息
        Album album = getOne(queryWrapper);
        if (album == null) {
            throw Lang.makeThrow(AnsException.class, "无效的相册信息");
        }

        return album;
    }


    /**
     * @author LiuCan
     * @date 2019/12/18 14:46
     * @param album
     * @return com.ansimpleasy.ans.entity.Album
     */
    private Album packageAlbum(Album album) {
        album.setHeaderKey(qiniuWrapper.getUrl(album.getHeaderKey()));
        return album;

    }
}
