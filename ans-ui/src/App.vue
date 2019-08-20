<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
  import {mapMutations, mapState} from "vuex";

  export default {
    name: "App",
    methods: {
      ...mapMutations(["cacheCodebooks"])
    },

    computed: {
      ...mapState({
        codebooks: state => state.codebooks.codebooks,
        user: state => state.user.loginUser
      })
    },
    created() {
      // this.$api.codebook.all(result => {
      //   this.cacheCodebooks(result.data);
      // });
    }
  };
</script>

<style>
  html,
  body {
    width: 100%;
    height: 100%;
    overflow: hidden;
    margin: 0;
    padding: 0;
  }

  #app {
    width: 100%;
    height: 100%;
  }

  .bm-view {
    height: 100%;
  }

  .header {
    background-color: #242f42;
  }

  .login-wrap {
    background: #324157;
  }

  .plugins-tips {
    background: #eef1f6;
  }

  .plugins-tips a {
    color: #20a0ff;
  }

  .el-upload--text em {
    color: #20a0ff;
  }

  .pure-button {
    background: #20a0ff;
  }

  .fa-right,
  .el-button [class*="fa"] + span {
    margin-left: 5px;
  }

  .el-table {
    margin-top: 20px;
  }

  .el-date-editor.el-input,
  .el-date-editor.el-input__inner,
  .el-select,
  .el-cascader {
    width: 100% !important;
  }

  .el-dialog__header {
    border-bottom: 1px solid #cccccc;
  }

  .anchorBL {
    display: none;
  }
</style>