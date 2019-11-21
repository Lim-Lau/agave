<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">
                <img class="login-img" src="@/assets/timg.jpg">
            </div>
            <el-form
                    ref="registerForm"
                    :model="user"
                    :rules="rules"
            >
                <el-form-item prop="name" label="用户名" label-width="80px" :label-position="labelPosition">
                    <el-input v-model="user.name"
                              placeholder="请输入用户名"
                              suffix-icon="fa fa-mobile">
                    </el-input>
                </el-form-item>
                <el-form-item prop="password" label="密码" label-width="80px" :label-position="labelPosition">
                    <el-input  v-model="user.password"
                              placeholder="请输入密码"
                              suffix-icon="fa fa-lock">
                    </el-input>
                </el-form-item>
                <div>
                <el-button type="text" @click="toLogin">去登录</el-button>
                </div>
                <div class="login-btn">
                    <el-button
                            type="primary"
                            @click="register('registerForm')"
                    >
                        注册
                    </el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {mapMutations, mapState} from "vuex";
    export default {
        name: "Register",
        data:function () {
            return{
                labelPosition: "left",
                user:{
                    name: '',
                    password: ''
                },
                rules: {
                    name: [{required: true, message: "请输入用户名", trigger: "blur"}],
                    password: [{required: true, message:"请输入密码", trigger: "blur"}],
                }
            }
        },
        computed: {
        },
        methods:{
            ...mapMutations([
                "login",
                "logout",
                "save",
                "remove",
                "updateAvatar",
                "cacheCodebooks",
                "captchaCutDown"
            ]),
            toLogin() {
                this.$router.push({path: "/"});
            },
            register(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        this.$api.user.register(this.user, data => {
                            this.$message.success("注册成功!");
                            this.$cookie.set("userName", this.user.name);
                            let user = data.user;
                            this.login(user.token);
                            this.save(user);
                            this.$router.push({path: "/img"});
                        });
                    } else {
                        return false;
                    }
                });
            }

        }
    }
</script>

<style scoped>
    .login-img {
        width: 29%;
    }

    .login-wrap {
        position: relative;
        width: 100%;
        height: 100%;
        background-color: #f5f7f9;
        -moz-background-size: 100% 100%;
        background-image: url(../assets/login-logo.png);
        background-size: 100% 100%;
    }

    .append-img {
        height: 32px;
        cursor: pointer;
        margin-left: -20px;
        margin-right: -21px;
        margin-bottom: -5px;
    }

    .ms-title {
        font-size: 30px;
        font-weight: 900;
        width: 100%;
        text-align: center;
        color: #666666;
        font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
        "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    }

    .ms-login {
        position: absolute;
        left: 80%;
        top: 45%;
        width: 300px;
        height: 350px;
        margin: -150px 0 0 -190px;
        padding: 10px 40px;
        border-radius: 5px;
        background: #fff;
    }

    .login-btn {
        text-align: center;
    }

    .login-btn button {
        width: 100%;
        height: 36px;
    }
    .el-button--text {
        border-color: transparent;
        color: #409EFF;
        background: transparent;
        padding-left: 200px;
        padding-right: 0;
    }
</style>