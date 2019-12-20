package com.ansimpleasy.ans.service;

import com.ansimpleasy.ans.entity.Album;
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
     * @author LiuCan
     * @date 2019/12/18 14:49
     * @param page
     * @param key
     * @return IPage
     */
    IPage searchByPageAndKey(IPage page, String key);

    /**
     * @author LiuCan
     * @date 2019/12/18 14:49
     * @param user
     * @return java.util.List<com.ansimpleasy.ans.entity.Album>
     */
    List<Album> list(User user);
}
