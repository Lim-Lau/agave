<template>
    <section>
        <el-dialog :title="album.id == 0 ? '添加相册' : '编辑相册' "
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
                            maxlength="32"
                            suffix-icon="fa fa-vcard"
                            placeholder="请填写相册姓名"
                    />
                </el-form-item>
                <el-form-item
                        label="相册摘要"
                        :label-width="labelWidth"
                        prop="description"
                >
                    <el-input
                            v-model="album.description"
                            auto-complete="off"
                            maxlength="32"
                            suffix-icon="fa fa-vcard"
                            placeholder="请填写相册摘要"
                    />
                </el-form-item>
                <el-form-item
                        label="相册类型"
                        :label-width="labelWidth"
                        prop="type"
                >
                    <el-select
                            v-model="album.type.code"
                            placeholder="请选择"
                    >
                        <el-option
                                v-for="(item,index) in types"
                                :key="index"
                                :label="item.name"
                                :value="item.code"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item
                        label="上传封面照片"
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
    import {mapState} from "vuex";
    export default {
        name: "AddAndEdit",
        components: {
        },
        computed: {
            ...mapState({
                codebooks: state => state.codebooks.codebooks
            })
        },
        mounted() {
            this.types = this.codebooks ? this.codebooks.contentTypes : [];
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
                }
            }
        },
        data() {
            return {
                visible: false,
                labelWidth: "",
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
                },
                types: [],
                files: [],
                tempUrl: [],
                uploadUrl:this.$config.http.prefix + "/common/upload?foreignKey=1" + "&tableType=" +this.type,
                rules: {
                    name: [
                        {required: true, message: "请输入相册名称", trigger: "blur"}
                    ],
                    description: [
                        {required: true, message: "请输入相册描述", trigger: "blur"}
                    ],
                    type: [
                        {required: true, message: "请输入相册类型", trigger: "blur"}
                    ],
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
                    this.album.id = val.album.id;
                    this.loadData();
                }
            },
            visible(val) {
                if (!val) this.$emit("on-show-change", this.$data);
            }
        },
        methods:{
            loadData() {
                if (this.album.id <= 0) {
                    return;
                }
                this.$api.album.detail(this.album.id , result=>{
                    this.album = result.album;
                    this.files.push({
                        name: "",
                        url: result.album.headerUrl
                    });
                });
            },
            close() {
                this.cancel('albumForm');
            },
            cancel(form) {
                this.files = [];
                this.tempUrl = [];
                this.$refs[form].clearValidate();
                this.visible = false;
                this.album = {
                    id:0,
                    name: "",
                    description: "",
                    type: {
                        name: "",
                        code: "",
                        value: ""
                    }
                };
            },
            submit(form) {
                this.$refs[form].validate(valid => {
                    if (valid) {
                        const callback = () => {
                            this.cancel(form);
                        };
                        this.album = Object.assign(this.album, this.album, {
                            type: this.album.type.code
                        });
                        this.$delete(this.album, "status");
                        if (this.album.id > 0) {
                            this.$api.album.update(this.album, callback)
                        } else {
                            this.$api.album.save(this.album, callback);
                        }
                    } else {
                        return false;
                    }
                });
            },
            uploadBefore(val) {
                let limitSize = val.size / 1024 / 1024;
                if (limitSize > 5) {
                    this.$message.error("文件大小不能超过5M");
                    return false;
                }
                return true;
            },
            handleRemove(val) {
                this.files = this.files.filter(item => {
                    return item.key != val.key;
                })
                if (this.files.length == 0) {
                    this.album.headerKey = '';
                }
            },
            uploadSuccess(result) {
                this.$notify({
                    title: result.operationState === 'FAIL' ? '失败' : '成功',
                    message: `${result.operationState === 'FAIL' ? result.errors[0] : '上传成功'}`,
                    type: result.operationState === 'FAIL' ? 'error' : 'success'
                });
                this.album.headerKey = result.data.fileInfo.key;
                this.tempUrl.push({
                    name: "",
                    url: result.data.fileInfo.url
                });
            },
    }
    }
</script>

<style scoped>

</style>