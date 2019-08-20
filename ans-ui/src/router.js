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
        /*{
            path: "/",
            name: "members",
            hidden: true,
            meta: {
                icon: "fa fa-user-secret"
            },
            component: Layout,
            children: [{
                path: "/member",
                name: "member",
                meta: {
                    name: "成员管理",
                    icon: "fa fa-user-secret",
                    pn: installPermissions.permissions.member.management
                },
                component: () => import("./views/platform/user/member")
            }]
        }*/
    ]
})