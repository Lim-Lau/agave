<template>
    <div>
        <div style="display: flex; margin-top: 50px; margin-left: 100px; height: 100px;">
            <div>
                <transition name="el-zoom-in-center">
                    <div v-show="show" class="transition-box" @click="toBlog" ><b style="font-size: 40px">日志</b></div>
                </transition>

                <transition name="el-zoom-in-top">
                    <div v-show="show" class="transition-box" @click="toVerses" style="background-color: cadetblue"><b style="font-size: 40px">说说</b></div>
                </transition>
            </div>

            <div>
                <transition name="el-zoom-in-bottom">
                    <div v-show="show" class="transition-box" @click="toAlbum" style="background-color: bisque"><b style="font-size: 40px">相册</b></div>
                </transition>
                <transition name="el-zoom-in-bottom">
                    <div v-show="show" class="transition-box" @click="toNote" style="background-color: blueviolet"><b style="font-size: 40px">留言板</b></div>
                </transition>
            </div>
            <div>
                <transition name="el-zoom-in-bottom">
                    <div v-show="show" class="transition-box" @click="toMe" style="background-color: brown"><b style="font-size: 40px">个人中心</b></div>
                </transition>
                <transition name="el-zoom-in-bottom">
                    <div v-show="show" class="transition-box" @click="toAbout" style="background-color: chocolate"><b style="font-size: 40px">关于ANS</b></div>
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
                this.changeShow();
                this.$router.push("/blog");
            },
            toVerses() {
                this.changeShow();
                this.$router.push("/verses");
            },
            toAlbum() {
                this.changeShow();
                this.$router.push("/album");
            },
            toMe() {
                this.changeShow();
                this.$router.push("/me");
            },
            toNote() {
                this.changeShow();
                this.$router.push("/note");
            },
            toAbout() {
                this.changeShow();
                this.$router.push("/");
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