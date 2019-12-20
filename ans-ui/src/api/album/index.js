import {
    http,
    defaultSuccess,
    defaultError
}
    from "@/plugins/axios";

export default {
    /**
     * @param {Function} success 成功回调
     * @param {Function} error 失败回调
     */
    list(success = defaultSuccess, error = defaultError) {
        http.get("/album/list").then(success).catch(error)
    },
}