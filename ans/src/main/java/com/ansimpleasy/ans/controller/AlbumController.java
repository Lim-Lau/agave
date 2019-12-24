package com.ansimpleasy.ans.controller;


import com.ansimpleasy.ans.controller.base.BaseController;
import com.ansimpleasy.ans.dto.request.BatchPhotoDTO;
import com.ansimpleasy.ans.dto.request.PhotoDTO;
import com.ansimpleasy.ans.entity.Album;
import com.ansimpleasy.ans.result.Result;
import com.ansimpleasy.ans.service.impl.AlbumServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liucan
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/album")
public class AlbumController extends BaseController {
    @Autowired
    private AlbumServiceImpl albumService;

    @GetMapping("/list")
    @ApiOperation("相册列表")
    public Result search() {

        return user() == null ? Result.fail("用户未登录!"):Result.success().addData("albums",albumService.list(user()));
    }

    @PostMapping("/add")
    @ApiOperation("添加相册")
    public Result add(@RequestBody Album album) {
        album.setUserId(user().getId());
        albumService.save(album);
        return Result.success();
    }


    @PostMapping("/update")
    @ApiOperation("编辑相册")
    public Result update(@RequestBody Album album) {
        albumService.updateName(album);
        return Result.success();
    }


    @GetMapping("/delete")
    @ApiOperation("删除相册")
    public Result delete(@RequestParam("id") long id) {
        albumService.updateStatus(id);
        return Result.success();

    }

    @GetMapping("/detail")
    @ApiOperation("详情")
    public Result detail(@RequestParam("id") long id) {

        return Result.success().addData("album", albumService.get(id));
    }

    @GetMapping("/photos")
    @ApiOperation("相册照片列表")
    public Result photos(@RequestParam(value = "page", defaultValue = "1") @ApiParam(value = "页码", defaultValue = "1",
            example = "1") int page,@RequestParam(value = "id", defaultValue = "0") @ApiParam(value = "相册id", defaultValue = "0",
            example = "1") long id) {
        return Result.success().addData("photos", albumService.queryPhotoByIdAndPage(id, packagePage(page)));
    }

    @PostMapping("/add/photo")
    @ApiOperation("添加照片")
    public Result addPhoto(@RequestBody PhotoDTO photoDTO) {
        photoDTO.setUserId(user().getId());
        albumService.addPhoto(photoDTO);
        return Result.success();

    }


    @PostMapping("/batch/delete")
    @ApiOperation("批量删除照片信息")
    public Result deletePhoto(@RequestBody BatchPhotoDTO batchPhotoDTO) {
        albumService.batchDelete(batchPhotoDTO);
        return Result.success();
    }

}

