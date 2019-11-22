<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">
                <img class="login-img" src="@/assets/timg.jpg">
            </div>
            <el-form
                    ref="loginForm"
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
                    <el-input type="password" v-model="user.password"
                              show-password
                              placeholder="请输入密码"
                              suffix-icon="fa fa-lock">
                    </el-input>
                </el-form-item>
                <el-form-item  prop="rememberMe">

                    <el-checkbox class="remember" v-model="user.rememberMe">
                        记住我
                    </el-checkbox>

                    <el-button type="text" @click="toRegister">注册</el-button>
                </el-form-item>
                <div class="login-btn">
                    <el-button
                            type="primary"
                            @click="submitForm('loginForm')"
                    >
                        登录
                    </el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
    import {mapMutations, mapState} from "vuex";
    export default {
        name: "Login",
        data:function () {
            return{
                labelPosition: "left",
                user:{
                    name: '',
                    password: '',
                    rememberMe: true
                },
                rules: {
                    name: [{required: true, message: "请输入用户名", trigger: "blur"}],
                    password: [{required: true, message: "请输入密码", trigger: "blur"}]
                }
            }
        },
        computed: {
            ...mapState({
                token: state => state.token.token || "",
                // cachedTime: state => state.token.time || 0
            })
        },
        created() {
            this.user.name = this.$cookie.get("userName");
        },
        mounted() {
            console.log(this.token);
            if (this.token) {
                this.$api.user.check(
                    (res) => {
                        console.log("res:", res);
                        this.$router.push("/ans");
                    },
                    () => {
                    }
                );
            }
        },
        methods: {
            ...mapMutations([
                "login",
                "logout",
                "save",
                "remove",
                "updateAvatar",
                "cacheCodebooks",
                "captchaCutDown"
            ]),
            toRegister() {
                this.$cookie.remove("userName");
                this.$router.push({path: "/register"});
            },

            submitForm(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        if (this.user.rememberMe) {
                            this.$cookie.set("userName", this.user.name);
                        } else {
                            this.$cookie.remove("userName");
                        }
                        this.$api.user.login(this.user, data => {
                            let user = data.user;
                            this.login(user.token);
                            this.save(user);
                            this.$router.push({path: "/ans"});
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
    .remember {
        left: 10%;
    }
    .el-button--text {
        border-color: transparent;
        color: #409EFF;
        background: transparent;
        padding-left: 203px;
        padding-right: 0;
    }

</style>