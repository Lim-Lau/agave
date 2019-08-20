<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">
                <img src="@/assets/timg.jpg">
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data:function () {
            return{
                message: "我是登录页面!"
            }
        }
    }
</script>

<style scoped>
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
</style>