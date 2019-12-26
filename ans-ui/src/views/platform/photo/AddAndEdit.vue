<template>
    <section>
        <el-dialog title="添加照片 "
                   :visible.sync="visible"
                   @close="close"
                   width="500px">
            <el-form
                    :model="album"
                    :inline="true"
                    :rules="rules"
                    ref="albumForm"
            >
                <el-form-item
                        label="相册名称"
                        :label-width="labelWidth"
                        prop="name"
                >
                    <el-input
                            v-model="album.name"
                            auto-complete="off"
                            disabled="true"
                    />
                </el-form-item>
                <el-form-item
                        label="上传照片"
                        :label-width="labelWidth"
                        prop="downLoadUrl"
                >
                    <el-upload
                            :action="uploadUrl"
                            :limit="1"
                            v-model="album.headerKey"
                            ref="upload"
                            multiple
                            :file-list="files"
                            :auto-upload="true"
                            :before-upload="uploadBefore"
                            :on-remove="handleRemove"
                            :on-success="uploadSuccess"
                            list-type="picture"
                    >
                        <el-button
                                slot="trigger"
                                size="small"
                                type="primary"
                                :disabled="files.length>0"
                        >
                            选取文件
                        </el-button>
                        <div
                                slot="tip"
                                class="el-upload__tip"
                        >
                            请上传封面照片,且文件大小不能超过5M(如果文件已存在，请先删除再上传)
                        </div>
                    </el-upload>
                </el-form-item>
            </el-form>
            <span
                    slot="footer"
                    class="dialog-footer"
            >
        <el-button
                size="small"
                @click="cancel('albumForm')"
        >取 消</el-button>
        <el-button
                size="small"
                type="primary"
                @click="submit('albumForm')"
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
                files: [],
                uploadUrl:this.$config.http.prefix + "/common/upload?foreignKey="+this.album.id+"&tableType='ALBUM'",
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
                }
            },
            visible(val) {
                if (!val) this.$emit("on-show-change", this.$data);
            }
        },
        methods:{
        }
    }
</script>

<style scoped>

</style>