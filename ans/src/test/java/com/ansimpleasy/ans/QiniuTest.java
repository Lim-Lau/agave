package com.ansimpleasy.ans;

import com.ansimpleasy.ans.common.CommonUtils;
import com.ansimpleasy.ans.common.qiniu.QiniuStorage;
import com.ansimpleasy.ans.common.qiniu.ThumbModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiuCan
 * @date 2019/12/13 15:21
 */
public class QiniuTest extends AnsApplicationTest {
    @Autowired
    QiniuStorage qiniuStorage;
    @Test
    public void test() {
        //测试上传图片
        byte[] buff = CommonUtils.fileToBytes("C:\\Users\\Lenovo\\Desktop\\pp.jpg");
        String key = qiniuStorage.uploadImage(buff);
        System.out.println("key = " + key);

        //String key = "/default/all/0/0755ffd19e3e416db0a69ca3b23d744a.jpeg";
        //测试下载图片
        String url = qiniuStorage.getUrl(key);
        System.out.println("url = " + url);

        //测试下载不同大小的图片
        url = qiniuStorage.getUrl(key, ThumbModel.THUMB_32);
        System.out.println("url = " + url);
    }
}
