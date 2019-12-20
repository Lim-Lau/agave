package com.ansimpleasy.ans.controller.common;

import com.ansimpleasy.ans.common.qiniu.QiniuWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuCan
 * @date 2019/12/16 15:48
 */
@RestController
public class IndexController {

    @Autowired
    private QiniuWrapper qiniuWrapper;

}
