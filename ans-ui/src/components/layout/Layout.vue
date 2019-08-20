<template>
  <div>
    <el-container class="main">
      <el-aside :width="tabWidth+'px'">
        <div class="isClossTab">
          <img
            src="@/assets/login-logo.jpg"
            height="58"
          >
        </div>
        <el-menu
          :class="'menu'"
          class="el-menu-vertical-demo"
          :default-active="activeIndex"
          :collapse="isCollapse"
          :collapse-transition="false"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#409eff"
          router
        >
          <NavMenu :nav-menus="menus" />
        </el-menu>
      </el-aside>
      <el-container>
        <Header
          :is-collapse="isCollapse"
          :system-name="systemName"
          @collapse="isClossTabFun"
        />
        <el-main>
          <el-breadcrumb
            separator="/"
            class="crumbs"
          >
            <el-breadcrumb-item :to="{ path: '/' }">
              首页
            </el-breadcrumb-item>
            <el-breadcrumb-item
              v-for="(item,index) in breadcrumbs"
              :key="index"
            >
              {{ item.meta.name }}
            </el-breadcrumb-item>
          </el-breadcrumb>
          <hr>
          <div>
            <router-view />
          </div>
        </el-main>
        <el-footer
          class="main-footer"
          height="60px"
        >
          <p v-html="copyright" />
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>
<style scoped lang="scss">
@import "@/element-variables.scss";

.el-menu {
  overflow-y: auto;
  height: calc(100% - 50px);
}

.el-menu::-webkit-scrollbar {
  display: none;
}

.el-submenu [class^="fa"],
.el-menu-item [class^="fa"] {
  margin-right: 5px;
  width: 24px;
  text-align: center;
  font-size: 18px;
  vertical-align: middle;
}

.main {
  height: 100vh;
  min-width: 800px;
  min-height: 600px;
  overflow: hidden;

  aside {
    overflow: visible;
    height: 100%;
    background-color: $background-color;
    color: $color;

    .isClossTab {
      width: 100%;
      height: $header-height;
      cursor: pointer;
      font-size: 20px;
      text-align: center;
      line-height: $header-height;
      font-weight: bold;
      border-right: 1px solid #807c7c;
      border-bottom: 1px solid #807c7c;
      box-sizing: border-box;
      background-color: #fff;

      img {
        background-color: #fff;
      }
    }

    .menu {
      width: 100%;
      border-right: 0;
    }
  }

  .crumbs {
    margin-bottom: 20px;
  }

  .main-footer {
    text-align: center;
    background-color: $background-color;
    color: $color;
    font-size: 12px;
    line-height: 30px;
    border-left: 1px solid #807c7c;

    p {
      margin: 0;
    }
  }
}
</style>

<script>
    import NavMenu from "@/components/layout/NavMenu";
    import Header from "@/components/layout/Header";
    import {mapGetters, mapMutations} from "vuex";

    export default {
        components: {
            NavMenu: NavMenu,
            Header: Header
        },
        data() {
            return {
                isCollapse: false,
                copyright: this.$config.app.copyright,
            };
        },
        computed: {
            ...mapGetters(["hasRole", "hasPermission"]),
            breadcrumbs: function () {
                return this.$route.matched.filter(item => item.meta.name);
            },
            systemName: function () {
                return this.isCollapse
                    ? this.$config.systemName.substring(0, 1)
                    : this.$config.systemName;
            },
            tabWidth: function () {
                return this.isCollapse ? 64 : 200;
            },
            menus: function () {
                return this.$router.options.routes
                    .filter(this.filterFun)
                    .map(this.mappingFun);
            },
            opend() {
                return this.menus.map(item => item.entity.name)
            },
            activeIndex(){
                return this.$route.name;
            }
        },
        methods: {
            ...mapMutations(["save", "remove", "updateAvatar"]),
            filterFun(item) {
                return !item.hidden && this.hasPermission(item.meta ? item.meta.pn : "");
            },
            mappingFun(item) {
                return {
                    entity: {
                        name: item.name,
                        icon: item.meta ? item.meta.icon : "",
                        alias: item.meta ? item.meta.name : "",
                        branch: item.meta ? item.meta.branch : false,
                        value: {path: item.path}
                    },
                    children: item.children
                        ? item.children.filter(this.filterFun).map(this.mappingFun)
                        : null
                };
            },
            isClossTabFun() {
                setTimeout(() => {
                    this.isCollapse = !this.isCollapse;
                }, 100);
            }
        }
    };
</script>