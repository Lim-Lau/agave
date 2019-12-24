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
        http.get("/album/list").then(success).catch(error);
    },
    /**
     *详情
     * @param success
     * @param error
     */
    detail(id,success = defaultSuccess, error = defaultError) {
        http.get("/album/detail", {params: {id: id}}).then(success).catch(error);
    },
    /**
     *详情
     * @param success
     * @param error
     */
    photos(page, id, success = defaultSuccess, error = defaultError) {
        http.get("/album/photos", {params: {page: page, id: id}}).then(success).catch(error);
    },
    /**
     *
     * @param album
     * @param success
     * @param error
     */
    update(album, success = defaultSuccess, error = defaultError) {
        http.post("/album/update", album).then(success).catch(error);
    },
    /**
     *
     * @param album
     * @param success
     * @param error
     */
    save(album, success = defaultSuccess, error = defaultError) {
        http.post("/album/add",  album).then(success).catch(error);
    }
}