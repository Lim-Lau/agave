package com.ansimpleasy.ans.common.qiniu;

import com.ansimpleasy.ans.common.helper.FileTypeHelper;
import com.ansimpleasy.ans.enums.FileType;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import org.nutz.lang.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 七牛SDK的包装类，以便于业务使用
 *
 * @author LiuCan
 * @date 2019/12/13 14:39
 */
@EnableConfigurationProperties(QiniuConfigProperties.class)
@org.springframework.context.annotation.Configuration
public class QiniuWrapper {

    @Autowired
    private QiniuConfigProperties qiniuConfigProperties;

    private static final Logger logger = LoggerFactory.getLogger(QiniuWrapper.class);

    private static final UploadManager uploadManager;

    private static final String SAVE_AS = "|saveas/";
    /**
     * 设置转码的队列（"simpleasy是刚刚设置的队列名称"）
     */
    String pipeline = "simpleasy";


    /**
     * 从外部文件中初始化七牛存储相关的配置信息
     */
    static {
        /**
         * 设置空间上传域名
         */
        Configuration cfg = new Configuration(Zone.zone2());
        uploadManager = new UploadManager(cfg);
    }

    /**
     * 获取更新资源的token，只能用于更新参数key所代表的资源
     *
     * @param key 存储空间中已存在的资源key
     * @return
     */
    public String getUploadToken(String key, boolean update) {


        /**
         ------------------图片水印(水印样式来源于mybucket中的sy1样式)-------------------
         */
        /**
         *可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
         * saveBucket+":"+saveKey
         * bucketname为空间的名称，key为保存的文件名
         */
        String urlbase64 = UrlSafeBase64.encodeToString(qiniuConfigProperties.getBucket() + ":" + qiniuConfigProperties.getKey());
        String pfops = qiniuConfigProperties.fops + SAVE_AS + urlbase64;
        StringMap stringMap = new StringMap().putNotEmpty("persistentOps", pfops).putNotEmpty("persistentPipeline", pipeline);
        return update ? auth().uploadToken(qiniuConfigProperties.getBucket(), key, 3600, stringMap)
                : auth().uploadToken(qiniuConfigProperties.getBucket(), "", 3600, stringMap);
    }

    /**
     * 获取上传资源的token
     *
     * @return
     */
    public String getUploadToken() {
        return auth().uploadToken(qiniuConfigProperties.getBucket());
    }

    /**
     * 上传文件
     *
     * @param data   二进制格式的文件内容
     * @param key    文件在七牛中的key
     * @param update 是否是更新
     * @return
     */
    public String upload(byte[] data, String key, boolean update) {
        try {
            String token = update?auth().uploadToken(qiniuConfigProperties.getBucket(),key)
                    :auth().uploadToken(qiniuConfigProperties.getBucket());
            Response response = uploadManager.put(data, getFullKey(data, key), token);
            DefaultPutRet ret = response.jsonToObject(DefaultPutRet.class);
            return ret.key;
        } catch (QiniuException e) {
            logger.error("upload file to qiniu cloud storage failed", e);
        }
        return null;
    }

    private String getFullKey(byte[] data, String key) {
        FileType type = FileTypeHelper.getType(data);
        if (type != null) {
            return key + "." + type.name().toLowerCase();
        } else {
            return key;
        }
    }

    public String getUrl(String key, String model) {
        return getUrl(key, model, 3600);
    }

    /**
     * 获取多个key图片；
     *
     * @param keys  逗号隔开的多个key;
     * @param model
     * @return
     */
    public List<String> getUrls(String keys, String model) {
        List<String> list = null;
        if (Strings.isNotBlank(keys)) {
            list = new ArrayList<String>();
            for (String key : keys.split(",")) {
                list.add(getUrl(key, model, 3600));
            }
        }
        return list;
    }

    public String getUrl(String key) {
        if (!StringUtils.isEmpty(key)) {
            return auth().privateDownloadUrl("http://" + qiniuConfigProperties.getCdns() + "/@" + key);
        }
        return null;
    }

    /**
     * @param key
     * @param expires 单位：秒
     * @return
     */
    public String getUrl(String key, long expires) {
        if (!Strings.isEmpty(key)) {
            long time = System.currentTimeMillis() / 1000 + expires;
            return auth().privateDownloadUrl("http://" + qiniuConfigProperties.getCdns() + "/@" + key, time);
        }
        return null;
    }

    /**
     * @param key
     * @param model
     * @param expires 单位：秒
     * @return
     */
    public String getUrl(String key, String model, long expires) {
        if (!Strings.isEmpty(key)) {
            long time = System.currentTimeMillis() / 1000 + expires;
            return auth().privateDownloadUrl("http://" + qiniuConfigProperties.getCdns() + "/@" + key + "?" + model, time);
        }
        return null;
    }


    public Auth auth() {
        return Auth.create(qiniuConfigProperties.getAccesskey(), qiniuConfigProperties.getSecretkey());
    }
}
