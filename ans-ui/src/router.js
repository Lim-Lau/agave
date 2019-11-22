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
            name: "home",
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
        }
    ]
})