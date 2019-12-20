<template>
    <el-upload
            :action="uploadUrl"
            :on-remove="handleRemove"
            :on-preview="handlePreview"
            :file-list="pictures"
            multiple
            :on-success="uploadSuccess"
            limit="9"
            list-type="picture">
        <el-button size="small" type="primary">点击上传</el-button>
    </el-upload>
</template>

<script>
    export default {
        name: "upload",
        props: {
            type: {
                type: String,
                default: "ALBUM"
            },
            foreignKey:{
                type: Number,
                default: 0
            }
        },
        data() {
            return {
                uploadUrl: this.$config.http.prefix + "/common/upload?foreignKey="
                    + this.foreignKey + "&tableType=" +this.type,
                pictures: [],
                tempFiles: [],
                fileType:"IMG"
            };
        },
        created() {
            this.loadData();
        },
        methods: {
            handleRemove(file, fileList) {
                console.log(file, fileList);
                this.tempFiles = this.tempFiles.filter(item => {
                    return item.key != val.key;
                })
            },
            handlePreview(file) {
                console.log(file);
            },
            loadData() {
                this.$api.common.files(this.type,this.fileType,result=>{
                    if (result.files && result.files.length > 0) {
                        this.pictures =result.files.map(img=>{
                            return {
                                name: img.originalName,
                                url: img.fileKey
                            };
                        })
                        result.imgs.map(img =>{
                            this.tempFiles.push(img.fileKey);
                        })
                    }
                })
            },
            cancelUpload() {
                this.tempFiles = [];
            },
            uploadSuccess(result) {
                this.$notify({
                    title: result.operationState === 'FAIL' ? '失败' : '成功',
                    message: `${result.operationState === 'FAIL' ? result.errors[0] : '上传成功'}`,
                    type: result.operationState === 'FAIL' ? 'error' : 'success'
                });
                if (result.operationState === 'SUCCESS') {
                    console.log(JSON.stringify(result));
                    this.tempFiles.push( result.data.fileInfo.key);
                }
                this.count++;
                if (this.count === this.imgs.length) {
                    this.cancelUpload();
                }
            },
        }
    }
</script>

<style scoped>

</style>