import {
    http,
    defaultSuccess,
    defaultError
}
    from "@/plugins/axios";

export default {
    /**
     * 获取所有码本
     * @param success
     * @param error
     */
    all(success = defaultSuccess, error = defaultError){
        http.get("/code/book/all").then(success).catch(error)
    }
}