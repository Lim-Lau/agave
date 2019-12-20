package com.ansimpleasy.ans.service.impl;

import com.ansimpleasy.ans.common.qiniu.QiniuWrapper;
import com.ansimpleasy.ans.entity.Album;
import com.ansimpleasy.ans.entity.user.User;
import com.ansimpleasy.ans.enums.Status;
import com.ansimpleasy.ans.mapper.album.AlbumMapper;
import com.ansimpleasy.ans.service.IAlbumService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
