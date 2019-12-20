import {
    http,
    defaultSuccess,
    defaultError
}
    from "@/plugins/axios";
export default {
    /**
     * @param {String} type 页面类型（相册、博客等）
     * @param {String} fileType 文件类型（图片、文本等）
     * @param {Function} success 成功回调
     * @param {Function} error 失败回调
     */
    files(type,fileType, success = defaultSuccess, error = defaultError) {
        http.get("/common/files", {params: {type: type,fileType:fileType}}).then(success).catch(error)
    },
}