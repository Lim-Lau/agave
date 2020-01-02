<template>
    <section>
        <el-dialog title="添加照片 "
                   :visible.sync="visible"
                   @close="close"
                   width="500px">
            <el-form
                    :model="album"
                    :inline="true"
                    ref="photoForm"
            >
                <el-form-item
                        label="相册名称"
                        :label-width="labelWidth"
                        prop="name"
                >
                    <el-input
                            v-model="album.name"
                            auto-complete="off"
                            disabled
                    />
                </el-form-item>
                <el-form-item
                        label="上传照片"
                        :label-width="labelWidth"
                        prop="downLoadUrl"
                >
                    <el-upload
                            class="upload-demo"
                            :action="uploadUrl"
                            multiple
                            :limit="9"
                            :file-list="files"
                            v-model="files"
                            :on-success="uploadSuccess"
                            :before-upload="checkFile"
                            :on-remove="handleRemove">
                        <el-button size="small" type="primary">点击上传</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传图片文件，且不超过5M</div>
                    </el-upload>
                </el-form-item>
            </el-form>
            <span
                    slot="footer"
                    class="dialog-footer"
            >
        <el-button
                size="small"
                @click="cancel('photoForm')"
        >取 消</el-button>
        <el-button
                size="small"
                type="primary"
                @click="submit('photoForm')"
        >确 定</el-button>
      </span>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        name: "AddAndEdit",
        data() {
            return{
                visible: false,
                labelWidth: "",
                album: {
                    id:0,
                    name: ""
                },
                photos: [],
                files: []
            }
        },
        computed: {
            uploadUrl() {
                return this.$config.http.prefix + "/common/upload?foreignKey="+this.album.id + "&tableType=ALBUM"
            }
        },
        props: {
            value: {
                type: Object,
                default: function () {
                    return {
                        show: false,
                        formLabelWidth: "120px",
                        album: {
                            id:0,
                            name: ""
                        }
                    };
                }
            }
        },
        watch: {
            value: {
                deep: true,
                immediate: true,
                handler: function (val) {
                    this.visible = val.show;
                    this.labelWidth = val.formLabelWidth || "120px";
                    this.album= val.album;
                    // this.loadData();
                }
            },
            visible(val) {
                if (!val) this.$emit("on-show-change", this.$data);
            }
        },
        methods:{
            close() {
                this.cancel('photoForm');
            },
            cancel(form) {
                this.files = [];
                this.$refs[form].clearValidate();
                this.visible = false;
                this.album = {
                    id:0,
                    name: ""
                };
            },
            submit(form) {
                this.cancel(form);
                // console.log(this.photos);
                // let photosDTO = {
                //     id: this.album.id,
                //     name: this.album.name,
                //     files: this.photos
                // };
                // const callback = () => {
                //     this.cancel(form);
                // };
                // this.$api.album.addPhoto(photosDTO, callback());
            },
            uploadSuccess(result) {
                this.photos.push({
                    relatedId: this.album.id,
                    fileKey:result.data.fileInfo.key,
                    fileType:result.data.fileInfo.type.code,
                    tableType: "ALBUM"
                })
            },
            checkFile(file) {
                let suffix = file.name.substr(file.name.lastIndexOf("."));
                const isImg = ".jpg" == suffix || ".png" == suffix|| ".gif" == suffix|| ".jpeg" == suffix|| ".psd" == suffix|| ".pdd" == suffix;
                const isLt5M = file.size / 1024 / 1024 < 5;

                if (!isImg) {
                    this.$message.error("只能是图片文件!");
                }
                if (!isLt5M) {
                    this.$message.error("上传文件大小不能超过 5MB!");
                }
                return isImg && isLt5M;
            },
            handleRemove(val) {
                this.photos = this.photos.filter(item => {
                    return item.fileKey != val.response.data.fileInfo.key;
                })

            },
        }
    }
</script>

<style scoped>

</style>