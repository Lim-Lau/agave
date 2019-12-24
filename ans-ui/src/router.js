import Layout from "@/components/layout/Layout.vue";
import Router from 'vue-router'
import Vue from 'vue'
import installPermissions from "@/common/permissions.js"

Vue.use(Router);
export default new Router({
    routes: [
        {
            path: "/",
            name: "home",
            component: () => import("./views/Login.vue"),
            hidden: true,
            meta: {
                name: "登录",
                title: '登录'
            }
        },
        {
            path: "/register",
            name: "register",
            component: () => import("./views/Register.vue"),
            hidden: true,
            meta: {
                name: "注册",
                title: '注册'
            }
        },
        {
            path: "/",
            name: "Ans",
            meta: {
                icon: "el-icon-menu"
            },
            component: Layout,
            children: [{
                path: "/ans",
                name: "ans",
                meta: {
                    name: "主页",
                    icon: "el-icon-menu"
                    // pn: installPermissions.permissions.member.management
                },
                component: () => import("./views/platform/home/ans")
            }]
        },
        {
            path: "/",
            name: "Blog",
            meta: {
                icon: "el-icon-menu"
            },
            component: Layout,
            children: [{
                path: "/blog",
                name: "blog",
                meta: {
                    name: "日志",
                    icon: "el-icon-reading"
                    // pn: installPermissions.permissions.member.management
                },
                component: () => import("./views/platform/blog")
            }]
        },
        {
            path: "/",
            name: "Verses",
            meta: {
                icon: "el-icon-menu"
            },
            component: Layout,
            children: [{
                path: "/verses",
                name: "verses",
                meta: {
                    name: "说说",
                    icon: "el-icon-chat-dot-square"
                    // pn: installPermissions.permissions.member.management
                },
                component: () => import("./views/platform/verses")
            }]
        },
        {
            path: "/",
            name: "Album",
            meta: {
                icon: "el-icon-menu"
            },
            component: Layout,
            children: [{
                path: "/album",
                name: "album",
                meta: {
                    name: "相册",
                    icon: "el-icon-picture-outline"
                    // pn: installPermissions.permissions.member.management
                },
                component: () => import("./views/platform/album")
            }]
        },
        {
            path: "/",
            name: "Note",
            meta: {
                icon: "el-icon-menu"
            },
            component: Layout,
            children: [{
                path: "/note",
                name: "note",
                meta: {
                    name: "留言板",
                    icon: "el-icon-notebook-2"
                    // pn: installPermissions.permissions.member.management
                },
                component: () => import("./views/platform/note")
            }]
        },
        {
            path: "/",
            name: "Me",
            meta: {
                icon: "el-icon-menu"
            },
            component: Layout,
            children: [{
                path: "/me",
                name: "me",
                meta: {
                    name: "个人中心",
                    icon: "el-icon-s-custom"
                    // pn: installPermissions.permissions.member.management
                },
                component: () => import("./views/platform/me")
            }]
        },
    ]
})