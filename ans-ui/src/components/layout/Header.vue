<template>
  <el-header class="main-header">
    <!-- <div
      class="tools"
      @click.prevent="isClossTabFun">
      <i
        :class="isCollapse?'el-icon-d-arrow-right':'el-icon-d-arrow-left'" />
    </div>-->
    <el-row>
      <el-col>{{ loginUser.extInfos&&loginUser.extInfos.companyInfo && loginUser.extInfos.companyInfo.name }} {{ loginUser.extInfos&&loginUser.extInfos.empInfo&&loginUser.extInfos.empInfo.name }} {{ loginUser.userName }}</el-col>
    </el-row>
    <el-dropdown
      size="small"
      @command="hanldCommand"
    >
      <span class="el-dropdown-link">
        <img
          :title="loginUser.userName"
          :src="logo"
        >
      </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="change">
          修改密码
        </el-dropdown-item>
        <el-dropdown-item
          divided
          command="logout"
        >
          退出登录
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <!--<el-badge -->
    <!--v-if="showDot" -->
    <!--style="float:right;line-height:30px;margin-top:15px;margin-right:30px" -->
    <!--:value="orderCount" -->
    <!--class="item">-->
    <!--<i -->
    <!--@click="toSchedule" -->
    <!--class="fa fa-envelope" -->
    <!--style="font-size:36px"-->
    <!--title="有新的订单需要调度"/>-->
    <!--</el-badge>-->
    <el-dialog
      title="修改密码"
      :visible.sync="changeShow"
      width="500px"
    >
      <el-form
        :model="user"
        :rules="$rules.user"
        ref="resetForm"
      >
        <el-form-item
          label="用户密码"
          :label-width="labelWidth"
          prop="pwd"
        >
          <el-input
            v-model="user.oldPwd"
            auto-complete="off"
            type="password"
            suffix-icon="fa fa-eye"
            placeholder="请输入旧密码"
          />
        </el-form-item>
        <el-form-item
          label="确认密码"
          :label-width="labelWidth"
          prop="rePwd"
        >
          <el-input
            v-model="user.newPwd"
            auto-complete="off"
            type="password"
            suffix-icon="fa fa-eye"
            placeholder="请输入新密码"
          />
        </el-form-item>
        <el-form-item
          label="确认密码"
          :label-width="labelWidth"
          prop="rePwd"
        >
          <el-input
            v-model="user.reNew"
            auto-complete="off"
            type="password"
            suffix-icon="fa fa-eye"
            placeholder="请再次输入新密码"
          />
        </el-form-item>
      </el-form>
      <span
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="cancle">取 消</el-button>
        <el-button
          type="primary"
          @click="submit(loginUser.id)"
        >确 定</el-button>
      </span>
    </el-dialog>
  </el-header>
</template>
<style scoped lang="scss">
@import "@/element-variables.scss";
.main-header {
  background-color: $background-color;
  color: $color;
  .tools {
    padding: 0px;
    width: 14px;
    height: 60px;
    line-height: 60px;
    cursor: pointer;
    float: left;
  }
  .el-row {
    float: right;
    line-height: 60px;
    margin-left: 10px;
    font-size: 12px;
  }
  .el-dropdown {
    cursor: pointer;
    float: right;
  }
  .el-dropdown-link {
    img {
      $imgMargin: (($header-height - 50)/2);
      display: inline-block;
      width: 50px;
      height: 50px;
      border-radius: 25px;
      background-color: #fff;
      margin-top: $imgMargin;
    }
  }
}
</style>
<script>
import { mapState, mapGetters, mapMutations } from "vuex";
import settings from "@/config/defaultSettings.js";
export default {
  name: "Header",
  created() {
    this.loadCount();
    setInterval(() => {
      this.loadCount();
    }, 30000);
  },
  data() {
    return {
      changeShow: false,
      labelWidth: "120px",
      orderCount: 0,
      user: {
        id: 0,
        oldPwd: "",
        newPwd: "",
        reNew: ""
      }
    };
  },
  computed: {
    ...mapState({
      loginUser: state => state.user.loginUser
    }),
    logo: function() {
      return "." + settings.http.prefix + "/user/avatar/" + this.loginUser.userName;
    },
    ...mapGetters(["hasRole", "hasPermission"])
  },
  methods: {
    ...mapMutations(["save", "remove", "logout", "updateAvatar"]),
    cancle() {
      this.changeShow = false;
    },
    loadCount() {
      // this.$api.job.orderCount(result => {
      //   this.orderCount = result.count;
      // });
    },
    toSchedule() {
      this.$router.push({ path: "/tech/schedule" });
    },
    submit(id) {
      this.user.id = id;
      this.$api.user.changePwd(this.user, () => {
        this.$message({
          message: "修改密码成功,请使用新密码重新登录",
          type: "success",
          onClose: () => {
            this.logout();
            this.remove();
            this.$router.push({ path: "/" });
          }
        });
      });
    },
    isClossTabFun: function() {
      this.$emit("collapse");
    },
    hanldCommand(cmd) {
      switch (cmd) {
        case "change":
          this.changeShow = true;
          break;
        case "logout":
          return this.logOut();
        default:
      }
    },
    logOut() {
      this.$message({
        message: "成功退出",
        type: "success",
        duration: 1000,
        onClose: () => {
          this.logout();
          this.remove();
          this.$router.push({ path: "/" });
        }
      });
    }
  },
  props: {
    isCollapse: {
      type: Boolean,
      default: true
    },
    systemName: {
      type: String,
      default: "VUE-THUNDER"
    }
  }
};
</script>