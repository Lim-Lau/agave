<template>
    <section>

        <el-row :gutter="20">
            <!--<span style="font-size: 25px; font-weight: bolder">相册列表</span>-->
            <el-col
                    :span="4"
                    :offset="20"
            >
                <el-button
                        type="primary"
                        size="small"
                        icon="el-icon-plus"
                        @click="add"
                >
                    添加相册
                </el-button>
            </el-col>
        </el-row>
        <div style="margin-top: 50px">
            <el-divider><i class="el-icon-picture-outline"></i></el-divider>
            <el-row>
                <el-col :span="8" v-for="(album, index) in albums" :key="index" >
                    <div class="album">
                        <el-card :body-style="{ padding: '20px' }">
                            <img :src= album.headerKey class="image">
                            <div style="padding: 14px;">
                                <span>相册名:{{album.name}}</span>
                                <div class="bottom clearfix">
                                    <span>描述:{{album.description}}</span>
                                        <el-button type="text" class="button" @click="detail(album)">编辑</el-button>
                                        <el-button type="text" class="button" @click="photos(album)">详情</el-button>
                                </div>
                            </div>
                        </el-card>
                    </div>
                </el-col>
            </el-row>
        </div>
        <!-- 添加和编辑弹出框 -->
        <AddAndEdit
                v-model="addAndEditValue"
                @on-show-change="addAndEditDone"
        />
    </section>
</template>

<script>
    import AddAndEdit from "./AddAndEdit";

    export default {
        name: "album",
        components:{
            AddAndEdit: AddAndEdit,
        },
        data() {
            return {
                albums: [],
                addAndEditValue: {
                    show: false,
                    album: {
                        id:0,
                        name: "",
                        description: "",
                        type: {
                            name: "",
                            code: "",
                            value: ""
                        },
                        headerKey: ""
                    }
                },
            }
        },
        created() {
            this.loadData();
        },
        methods:{
            loadData() {
                this.$api.album.list(result=>{
                    this.albums = result.albums ? result.albums : [];
                });
            },
            detail(album) {
                this.addAndEditValue = {show:true , album: album};

            },
            add() {
                this.addAndEditValue.show = true;
            },
            photos() {
                console.log("详情");
            },
            addAndEditDone() {
                this.addAndEditValue = {
                    show: false,
                        album: {
                        id:0,
                            name: "",
                            description: "",
                            type: {
                            name: "",
                                code: "",
                                value: ""
                        },
                        headerKey: ""
                    }
                };
                this.loadData();
            }
        }
    }
</script>

<style scoped>
    .time {
        font-size: 13px;
        color: #999;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }

    .button {
        padding: 0;
        float: right;
        margin-bottom: 10px;
        margin-left: 5px;
        margin-right: 2px;
        float: right;
    }

    .image {
        width: 250px;
        height: 200px;
        display: block;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }
    .el-card.is-always-shadow {
        width: 300px;
        height: 290px;
    }
    .el-col-8 {
        width: 25%;
        margin-bottom: 60px;
    }
    .album{
        margin-left: 65px;
        margin-right: 5px;
    }
</style>