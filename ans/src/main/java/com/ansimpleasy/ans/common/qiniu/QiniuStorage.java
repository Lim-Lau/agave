package com.ansimpleasy.ans.common.qiniu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 七牛云存储管理器
 *  上传图像和查看图像
 * @author LiuCan
 * @date 2019/12/13 14:42
 *
 */
@Component
public class QiniuStorage {

    @Autowired
    QiniuWrapper qiniuWrapper;
    /**
     * 上传单张图片；返回上传图片的key
     * @param buff
     */
    public  String uploadImage(byte[] buff){
        String key = QiniuKeyGenerator.generateKey();
        key = qiniuWrapper.upload(buff, key,false);
        return key;
    }

    /**
     * 上传单张图片；返回上传图片的url，此url会过期，切记不要存储在数据库中；
     * @param buff
     * @param img
     * @return QiniuImg
     */
    public  QiniuImg uploadImage(byte[] buff,QiniuImg img){
        String key = qiniuWrapper.upload(buff, img.getUploadKey(),false);
        img.setKey(key);
        return img;
    }


    /**
     * 上传多个图片
     * @param imageBuffs 图片字节数组
     * @param img 分组图片的属性
     * @return
     */
    public  QiniuImg[] uploadImages(byte[][] imageBuffs,QiniuImg img){
        QiniuImg[] images = new QiniuImg[imageBuffs.length];
        for(int i = 0; i < imageBuffs.length; i++){
            QiniuImg newImg = new QiniuImg();
            uploadImage(imageBuffs[i],img);
            newImg.setKey(img.getKey());
            images[i] = newImg;
        }
        return images;
    }


    /**
     * 获取图片链接
     * @param key
     * @return
     */
    public  String getUrl(String key){
        return qiniuWrapper.getUrl(key);
    }

    /**
     * 获取有有效期的图片链接
     * @param key
     * @param expires 单位：秒
     * @return
     */
    public  String getUrl(String key,long expires){
        return qiniuWrapper.getUrl(key,expires);
    }


    /**
     * 获取图片链接
     * @param key
     * @return
     */
    public  String getUrl(String key,ThumbModel model){
        return qiniuWrapper.getUrl(key,model.getValue());
    }

    /**
     * 获取有有效期的图片链接
     * @param key
     * @param expires 单位：秒
     * @return
     */
    public  String getUrl(String key,ThumbModel model,long expires){
        return qiniuWrapper.getUrl(key,model.getValue(),expires);
    }
}
