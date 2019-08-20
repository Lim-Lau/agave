import {
    http,
    defaultSuccess,
    defaultError
}
    from "@/plugins/axios";

export default {
    /**
     *
     * @param {Object} user 登录用户信息
     * @param {Function} success 成功回调
     * @param {Function} error 失败回调
     */
    login(user, success = defaultSuccess, error = defaultError) {
        http.post("/user/login", user).then(success).catch(error)
    },
    /**
     * 检查token是否有效
     * @param {Function} success 成功回调
     * @param {Function} error 失败回调
     */
    check(success = defaultSuccess, error = defaultError) {
        http.get("/test").then(success).catch(error)
    },
    /**
     * 用户列表
     * @param page
     * @param success
     * @param error
     */
    list(page, success = defaultSuccess, error = defaultError) {
        http.get("/user/list", {params: {page: page}}).then(success).catch(error)
    },

    /**
     * 查询指定用户的权限
     * @param id
     * @param success
     * @param error
     */
    getUserPermissions(id, success = defaultSuccess, error = defaultError) {
        http.get("/user/permission/" + id).then(success).catch(error)
    },
    /**
     * 设置权限
     * @param param
     * @param success
     * @param error
     */
    grantPermission(param, success = defaultSuccess, error = defaultError) {
        http.post("/user/grant/permission", param).then(success).catch(error);
    }

}