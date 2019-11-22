<template>
    <div>
        <div style="display: flex; margin-top: 50px; margin-left: 100px; height: 100px;">
            <div>
                <transition name="el-zoom-in-center">
                    <div v-show="show" class="transition-box" @click="toBlog" >博客</div>
                </transition>

                <transition name="el-zoom-in-top">
                    <div v-show="show" class="transition-box" @click="toBlog" style="background-color: cadetblue">说说</div>
                </transition>
            </div>

            <div>
                <transition name="el-zoom-in-bottom">
                    <div v-show="show" class="transition-box" @click="toBlog" style="background-color: bisque">相册</div>
                </transition>
                <transition name="el-zoom-in-bottom">
                    <div v-show="show" class="transition-box" @click="toBlog" style="background-color: blueviolet">快递000</div>
                </transition>
            </div>
            <div>
                <transition name="el-zoom-in-bottom">
                    <div v-show="show" class="transition-box" @click="toBlog" style="background-color: brown">个人中心</div>
                </transition>
                <transition name="el-zoom-in-bottom">
                    <div v-show="show" class="transition-box" @click="toAbout" style="background-color: chocolate">关于ANS</div>
                </transition>
            </div>

        </div>
    </div>
</template>

<script>
    export default {
        name: "ans",
        data:function () {
            return {
                show: false
            }
        },
        methods: {
            toBlog() {
                //TODO
                console.log("我准备去博客列表页面了!");
                this.changeShow();
            },
            toAbout() {
                //TODO
                console.log("我准备去关于页面了!");
                this.changeShow();
            },
            changeShow() {
                this.show = !this.show;
            }
        },
        mounted() {
            this.show = true;
        }
    }
</script>

<style scoped>
    .transition-box {
        margin-bottom: 10px;
        margin-top: 50px;
        width: 400px;
        height: 250px;
        border-radius: 6px;
        background-color: #409EFF;
        text-align: center;
        color: #fff;
        padding: 40px 20px;
        box-sizing: border-box;
        margin-right: 100px;
    }
</style>