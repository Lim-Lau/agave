export default {
    // vue-ls options
    storageOptions: {
        namespace: "vux__", // key prefix
        name: "ls", // name variable Vue.[ls] or this.[$ls],
        storage: "local" // storage name session, local, memory
    },
    // axiox options
    http: {
        prefix: process.env.NODE_ENV === "production" ? "./" : "/api",
        timeout: 60 * 1000
    },
    app: {
        ossPrefix: process.env.NODE_ENV === "production"
            ? "https://emas.chinare.com.cn:8123/download/"
            : "/api/download/",
        systemName: "ANS"
    }
};