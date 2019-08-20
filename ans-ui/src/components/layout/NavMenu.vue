<template>
  <div class="navMenu">
    <template v-for="(navMenu,index) in navMenus">
      <!-- 最后一级菜单 -->
      <el-menu-item
        v-if=" (!navMenu.children || !navMenu.children.length) &&!navMenu.entity.branch"
        :key="index"
        :data="navMenu"
        :index="navMenu.entity.name"
        :route="navMenu.entity.value"
      >
        <i :class="navMenu.entity.icon" />
        <span slot="title">{{ navMenu.entity.alias }}</span>
      </el-menu-item>
      <!-- 子级只有一个而且没有孙级 直接渲染成链接-->
      <el-menu-item
        v-else-if="navMenu.children.length == 1 && !navMenu.children[0].children &&navMenu.entity"
        :key="index"
        :data="navMenu"
        :index="navMenu.children[0].entity.name"
        :route="navMenu.children[0].entity.value"
      >
        <i :class="navMenu.children[0].entity.icon" />
        <span slot="title">{{ navMenu.children[0].entity.alias }}</span>
      </el-menu-item>
      <!-- 子级只有一个而且有孙级 将子级上提一级-->
      <el-submenu
        v-else-if="navMenu.children.length == 1 &&navMenu.entity"
        :key="index"
        :data="navMenu"
        :index="navMenu.children[0].entity.name"
      >
        <template slot="title">
          <i :class="navMenu.children[0].entity.icon" />
          <span>{{ navMenu.children[0].entity.alias }}</span>
        </template>
        <!-- 递归 -->
        <NavMenu :nav-menus="navMenu.children[0].children" />
      </el-submenu>
      <!-- 子级不止一个,递归渲染子菜单 -->
      <el-submenu
        v-else-if="navMenu.children.length > 1"
        :key="index"
        :data="navMenu"
        :index="navMenu.entity.name"
      >
        <template slot="title">
          <i :class="navMenu.entity.icon" />
          <span>{{ navMenu.entity.alias }}</span>
        </template>
        <!-- 递归 -->
        <NavMenu :nav-menus="navMenu.children" />
      </el-submenu>
    </template>
  </div>
</template>
<script>
export default {
  name: "NavMenu",
  props: {
    navMenus: {
      type: Array,
      default: function() {
        return [];
      }
    }
  },
  data() {
    return {};
  },
  methods: {}
};
</script>
 <style>
.el-menu--collapse .el-icon-arrow-right:before {
  content: "" !important;
}
</style>
 
<style scoped>
.el-menu--collapse span {
  height: 0;
  width: 0;
  overflow: hidden;
  visibility: hidden;
  display: none;
}
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
</style>