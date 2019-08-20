"use strict";

import Vue from 'vue';
import axios from "axios";
import settings from "@/config/defaultSettings";

import {
    Loading,
    Message,
    MessageBox
} from "element-ui";
import router from '@/router'
import Router from "vue-router";
import store from "@/store";

Vue.use(Router);

export function defaultSuccess(data) {
    console.log(data)
}

export function defaultError(error) {
    console.log(error);
    Message.error({
        duration: 5000,
        message: error,
        center: true
    })
}

let loading;

function startLoading() {
    //使用Element loading-start 方法
    loading = Loading.service({
        lock: true,
        text: "加载中……",
        background: "rgba(0, 0, 0, 0.7)"
    });
}

function endLoading() {
    //使用Element loading-close 方法
    loading.close();
}

let needLoadingRequestCount = 0;

export function showFullScreenLoading() {
    if (needLoadingRequestCount === 0) {
        startLoading();
    }
    needLoadingRequestCount++;
}

export function tryHideFullScreenLoading() {
   // if (needLoadingRequestCount <= 0) return;
   // needLoadingRequestCount--;
   // if (needLoadingRequestCount === 0) {
        endLoading();
   // }
}

let config = {
    baseURL: settings.http.prefix,
    timeout: settings.http.timeout
};

const _axios = axios.create(config);

_axios.interceptors.request.use(
    function (config) {
        var token =
            store && store.state && store.state.token && store.state.token.token ?
            store.state.token.token :
            "";
        if (token && config.url != "/user/login") {
            config.headers.Authorization = token;
        }
        startLoading();
        return config;
    },
    function (error) {
        console.log(error)
        tryHideFullScreenLoading();
        return Promise.reject("请求配置失败!");
    }
);

// Add a response interceptor
_axios.interceptors.response.use(
    function (response) {
        tryHideFullScreenLoading();
        if (response.data.operationState == "SUCCESS") {
            //数据成功
            return Promise.resolve(response.data.data);
        } else {
            console.log(222, response);
            //数据失败直接reject
            return Promise.reject(response.data.errors[0]);
        }
    },
    function (error) {
        console.log(error)
        tryHideFullScreenLoading();
        switch (error.response.status) {
            case 403:
                MessageBox.alert("您没有权限操作此功能,请联系平台管理员", "提示", {
                    confirmButtonText: "确定",
                    callback: () => {
                        localStorage.clear();
                        new Vue({
                            router
                        }).$router.push({
                            path: "/"
                        });
                    }
                });
                break;
            case 401:
                MessageBox.alert("请重新登录", "提示", {
                    confirmButtonText: "确定",
                    callback: () => {
                        localStorage.clear();
                        new Vue({
                            router
                        }).$router.push({
                            path: "/"
                        });
                    }
                });
                break;
            case 404:
                return Promise.reject("不存在的接口");
            default:
                console.log(error);
                return Promise.reject(error.response.data.errors[0]);
        }
    }
);
let Plugin = {}
Plugin.install = function (Vue) {
    Vue.axios = _axios;
    window.axios = _axios;
    Object.defineProperties(Vue.prototype, {
        axios: {
            get() {
                return _axios;
            }
        },
        $axios: {
            get() {
                return _axios;
            }
        },
    });
};

Vue.use(Plugin)

export {
    _axios as http,
    Plugin
};
