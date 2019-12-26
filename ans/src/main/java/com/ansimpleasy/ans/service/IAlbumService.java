package com.ansimpleasy.ans.service;

import com.ansimpleasy.ans.dto.request.BatchPhotoDTO;
import com.ansimpleasy.ans.dto.request.PhotoDTO;
import com.ansimpleasy.ans.dto.response.AlbumDTO;
import com.ansimpleasy.ans.dto.response.FileDTO;
import com.ansimpleasy.ans.entity.Album;
import com.ansimpleasy.ans.entity.common.File;
import com.ansimpleasy.ans.entity.user.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liucan
 * @since 2019-12-17
 */
public interface IAlbumService extends IService<Album> {

    /**
     * 分页查询相册列表
     *
     * @author LiuCan
     * @date 2019/12/18 14:49
     * @param page
     * @param key
     * @return IPage
     */
    @Deprecated
    IPage searchByPageAndKey(IPage page, String key);

    /**
     *  相册列表
     *
     * @author LiuCan
     * @date 2019/12/18 14:49
     * @param user
     * @return java.util.List<com.ansimpleasy.ans.entity.Album>
     */
    List<Album> list(User user);

    /**
     *  更新相册
     *
     * @author LiuCan
     * @date 2019/12/20 11:27
     * @param album
     * @return void
     */
    void update(Album album);

    /**
     * 更新相册状态
     *
     * @author LiuCan
     * @date 2019/12/20 11:27
     * @param id
     * @return void
     */
    void updateStatus(long id);

    /**
     * 根据相册id获取相册图片信息
     * @author LiuCan
     * @date 2019/12/20 14:03
     * @param id
     * @param packagePage
     * @return Page<File>
     */
    IPage<FileDTO>  queryPhotoByIdAndPage(long id, IPage packagePage);

    /**
     *  添加 照片信息
     * @author LiuCan
     * @date 2019/12/20 15:43
     * @param photoDTO
     * @return void
     */
    void addPhoto(PhotoDTO photoDTO);

    /**
     * 批量删除照片信息
     * @author LiuCan
     * @date 2019/12/20 16:33
     * @param batchPhotoDTO
     * @return void
     */
    void batchDelete(BatchPhotoDTO batchPhotoDTO);

    /**
     * 根据相册id获取相册信息并组装封面图片url
     * @author LiuCan
     * @date 2019/12/24 14:08
     * @param id
     * @return com.ansimpleasy.ans.dto.response.AlbumDTO
     */
    AlbumDTO get(long id);

    /**
     * @author LiuCan
     * @date 2019/12/26 10:44
     * @param file
     * @return void
     */
    void updatePhoto(File file);
}
