<template>
    <section>
        <section>
            <el-row>
                <el-col >
                    <div class="album">
                        <div>
                            <img :src= album.headerUrl class="headerImage">
                        </div>
                        <div class="leftStyle" >
                            <el-input
                                    class="fontStyle"
                                    prefix-icon="el-icon-edit"
                                    size="mini"
                                    :readonly="flag"
                                    maxlength="9"
                                    minlength="1"
                                    @focus="changFlag"
                                    @blur="changFlag"
                                    v-model="album.name">
                            </el-input>
                            <div class="uploadButton">
                                <el-button type="primary" @click="addPhoto">上传照片</el-button>
                            </div>
                        </div>
                    </div>
                </el-col>
            </el-row>
        </section>
        <section>

            <div style="margin-top: 50px">
                <el-divider><i class="el-icon-picture-outline"/></el-divider>
                <el-row>
                    <el-col :span="3" v-for="(photo, index) in pager.dataList" :key="index" >
                            <el-card :body-style="{ padding: '32px' }">
                                <img :src= photo.fileUrl class="image">
                                <div class="desc" style="padding: 14px;">
                                        <el-input
                                                class="fontStyle"
                                                size="mini"
                                                maxlength="9"
                                                minlength="1"
                                                @blur="updatePhoto(photo)"
                                                v-model="photo.description">
                                        </el-input>
                                </div>
                            </el-card>
                    </el-col>
                </el-row>
            </div>
            <el-row>
                <el-col
                        :span="6"
                        :offset="18"
                >
                    <el-pagination
                            style="float:right"
                            layout="prev, pager, next"
                            :total="pager.pager.recordCount"
                            :page-size="pager.pager.pageSize"
                            :current-page.sync="pager.pager.pageNumber"
                            v-show="pager.pager.pageCount != 0"
                            @current-change="changePage"
                    />
                </el-col>
            </el-row>
        </section>
    </section>
</template>

<script>
    export default {
        name: "photos",
        data(){
            return {
                addAndEditValue: {
                    show: false,
                    album: {
                        id:0,
                        name: ""
                    }
                },
                searchParams: {
                    key: "",
                    status: ""
                },
                flag: true,
                album: {
                    id:0,
                    name: "",
                    description: "",
                    type: {
                        name: "",
                        code: "",
                        value: ""
                    },
                    headerKey: "",
                    headerUrl: ""

                },
                pager: {
                    dataList: [],
                    pager: {
                        pageCount: 0,
                        pageNumber: 1,
                        pageSize: 15,
                        recordCount: 0
                    },
                }

            }
        },
        created() {
            this.loadData();
        },

        methods:{
            doSearch() {

            },
            changePage() {
                if (this.searchParams.key) {
                    this.doSearch();
                } else {
                    this.loadData();
                }
            },
            loadData() {
                this.album.id = this.$route.query.id;
                this.$api.album.detail(this.album.id , result=>
                {
                    this.album = result.album;
                });
                this.$api.album.photos(this.pager.pager.pageNumber , this.album.id , result=>{
                    if (result && result.photos) {
                        this.pager = {
                            dataList:result.photos.records,
                            pager:{
                                pageCount: result.photos.pages,
                                pageNumber: result.photos.current,
                                pageSize: result.photos.size,
                                recordCount: result.photos.total
                            }
                        };
                    }

                })

            },
            changFlag() {
                if (this.flag) {
                    this.flag = false;
                    return;
                }
                this.flag = true;
                // 更新相册名称
                this.album = Object.assign(this.album, this.album, {
                    type: this.album.type.code
                });
                this.$delete(this.album, "status");
                // this.$api.album.update(this.album);
            },
            updatePhoto(photo) {
                photo = Object.assign(photo, photo, {
                    tableType: photo.tableType.code,
                    fileType: photo.fileType.code
                });
                this.$api.album.updatePhoto(photo);

            },
            addPhoto() {
                this.addAndEditValue = {show:true,album:{id:this.album.id , name: this.album.name}}
            }
        }
    }
</script>

<style scoped>
    .album{
        margin-right: 5px;
    }
    .uploadButton {
        margin-left: 10px;
        margin-top: 5px;
    }
    .el-button--small {
        width: 150px;
    }
    .fontStyle {
        margin-left: 10px;
        font-weight: bolder;
        font-size: larger;
        width: 150px;
        border: 0px;
        outline: none;
        cursor: pointer;
    }
    .leftStyle{
        float: left;
        margin-top: -91px;
        margin-left: 105px;
    }
    .image {
        width: 100px;
        height: 100px;
        display: block;
    }
    .headerImage {
        width: 100px;
        height: 100px;
        display: block;
    }
    .el-card {
        width: 180px;
        height: 180px;
    }
    .el-col-8 {
        width: 16.66666666666667%;
        margin-top: 10px;
        margin-bottom: 20px;
    }
    .desc{
        margin-right: -10px;
        /* padding: 14px; */
        margin-top: -2px;
        margin-left: -32px;
    }
</style>